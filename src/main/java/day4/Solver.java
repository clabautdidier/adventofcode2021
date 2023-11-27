package day4;

import common.Input;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Solver {

    public int part1(String filepath) throws IOException {
        List<String> lines = Input.readLines(filepath);
        String[] numbers = lines.get(0).split(",");

        List<String[]> bingoLines = parseCards(lines);

        for (String number : numbers) {
            for (int row = 0; row < bingoLines.size(); row++) {
                int cardSize = bingoLines.get(row).length;
                for (int col = 0; col < cardSize; col++) {
                    if (bingoLines.get(row)[col].equals(number)) {
                        bingoLines.get(row)[col] = "X";
                    }

                    if (allFound(bingoLines, row)) {
                        int cardIdx = (row+1) / (cardSize * 2);
                        int sumOfUnmarkedCard = sumOfUnmarkedCard(bingoLines, cardIdx);

                        return Integer.parseInt(number) * sumOfUnmarkedCard;
                    }
                }
            }
        }
        return 0;
    }


    public int part2(String filepath) throws IOException {
        List<String> lines = Input.readLines(filepath);
        String[] numbers = lines.get(0).split(",");

        List<String[]> bingoLines = parseCards(lines);
        int lastResult = 0;
        int cardSize = bingoLines.get(0).length;

        for (String number : numbers) {
            int marked = markAll(bingoLines, number);

            if (marked > 0) {
                for (int row = 0; row < bingoLines.size(); row++) {
                    if (allFound(bingoLines, row)) {
                        int cardIdx = row / (cardSize * 2);
                        int sumOfUnmarkedCard = sumOfUnmarkedCard(bingoLines, cardIdx);

                        lastResult = Integer.parseInt(number) * sumOfUnmarkedCard;
                        clearCard(bingoLines, cardIdx);

                        int activeCards = countActiveCards(bingoLines);
//                        System.out.println(number + ": " + ANSI_BLUE + activeCards + ANSI_RESET + " cards left");
                    }
                }
            }
        }

        return lastResult;
    }

    private int markAll(List<String[]> bingoLines, String number) {
        AtomicInteger marked = new AtomicInteger(0);
        bingoLines.forEach(rowOrCol -> {
            for (int i=0; i<rowOrCol.length; i++) {
                if (rowOrCol[i].equals(number)) {
                    rowOrCol[i] = "X";
                    marked.incrementAndGet();
                }
            }
        });
        return marked.get();
    }

    private int countActiveCards(List<String[]> bingoLines) {
        int cardSize = bingoLines.get(0).length;
        int activeCards = 0;
        int totalCards = bingoLines.size() / 2 / cardSize;

        for (int card=0; card<totalCards; card++) {
            int startRow = card * (cardSize * 2);
            boolean numberFound = false;

            for (int row = 0; row < cardSize*2 && (!numberFound); row++) {
                for (int col = 0; col<cardSize; col++) {
                    if (isANumber(bingoLines.get(startRow + row)[col])) {
                        numberFound = true;
                        break;
                    }
                }
            }

            if (numberFound) activeCards++;
        }

        return activeCards;
    }

    private boolean isANumber(String value) {
        return !(value.equals("X") || value.equals("-"));
    }

    private void clearCard(List<String[]> bingoLines, int cardIdx) {
        int cardSize = bingoLines.get(0).length;
        int startRow = cardIdx * (cardSize*2);

        for (int row = startRow; row<startRow+(cardSize*2); row++) {
            for (int col=0; col<cardSize; col++) {
                bingoLines.get(row)[col] = "-";
            }
        }
    }

    private static List<String[]> parseCards(List<String> lines) {
        List<String[]> bingoLines = new ArrayList<>();
        for (int lineIdx = 2; lineIdx< lines.size(); lineIdx++) {
            String line = lines.get(lineIdx).trim();

            if (line.isEmpty()) {
                extractColumns(bingoLines);
            }
            else {
                bingoLines.add(line.replace("  ", " ").split(" "));
            }
        }

        extractColumns(bingoLines);
        return bingoLines;
    }

    private int sumOfUnmarkedCard(List<String[]> bingoLines, int cardIdx) {
        int cardSize = bingoLines.get(0).length;
        int startRow = cardIdx * (cardSize*2);
        int sum=0;

        for (int row = startRow; row<startRow+cardSize; row++) {
            for (int col=0; col<cardSize; col++) {
                if (isANumber(bingoLines.get(row)[col]))
                    sum += Integer.parseInt(bingoLines.get(row)[col]);
            }
        }
        return sum;
    }

    private boolean allFound(List<String[]> bingoLines, int row) {
        for (int col=0; col<bingoLines.get(row).length; col++)
            if (!bingoLines.get(row)[col].equals("X")) return false;

        return true;
    }

    private static void extractColumns(List<String[]> bingoLines) {
        int cardSize = bingoLines.get(bingoLines.size()-1).length;
        String[] columns = new String[cardSize];

        for (int row = 0; row<cardSize; row++) {
            for (int col=0; col<cardSize; col++) {
                String number = bingoLines.get(bingoLines.size() - cardSize + row)[col];
                if (columns[col] != null) {
                    columns[col] = columns[col] + " " + number;
                }
                else {
                    columns[col] = number;
                }
            }
        }

        for (String columnText: columns) {
            bingoLines.add(columnText.split(" "));
        }
    }

}
