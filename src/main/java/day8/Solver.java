package day8;

import common.Input;

import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

public class Solver {

    public int part1(String filepath) throws IOException {
        List<String> lines = Input.readLines(filepath);
        List<String[]> segmentLines = lines.stream()
                .map(s -> s.split(" \\| "))
                .map(s -> s[1])
                .map(s -> s.split(" "))
                .toList();

        int counter = 0;
        for (String[] segmentLine : segmentLines) {
            for (String segment : segmentLine) {
                if (segment.length() != 5 && segment.length() != 6) counter++;
            }
        }
        return counter;
    }

    public int part2(String filepath) throws IOException {
        List<String> lines = Input.readLines(filepath);

        int total = 0;
        for (String line : lines) {
            int displayedNumber = parseLine(line);
//            System.out.println(line + " = " + displayedNumber);
            total += displayedNumber;
        }

        return total;
    }

    private int parseLine(String line) {
        String[] lineParts = line.split(" \\| ");
        String[] numbers = lineParts[0].split(" ");
        Arrays.sort(numbers, Comparator.comparingInt(String::length));

        char[] display = new char[7];
        display[2] = findIn(numbers[0], ltr -> !numbers[6].contains(ltr) || !numbers[7].contains(ltr) || !numbers[8].contains(ltr));
        display[5] = findIn(numbers[0], ltr -> ltr.charAt(0) != display[2]);
        display[0] = findIn(numbers[1], ltr -> ltr.charAt(0) != display[2] && ltr.charAt(0) != display[5]);
        String remainder = removeChar("abcdefg", display[0], display[2], display[5]);
        display[3] = findIn(remainder, ltr ->
                numbers[2].contains(ltr)
                        && (!numbers[6].contains(ltr) || !numbers[7].contains(ltr) || !numbers[8].contains(ltr)));
        remainder = removeChar(remainder, display[3]);
        display[4] = findIn(remainder, ltr -> !numbers[6].contains(ltr) || !numbers[7].contains(ltr) || !numbers[8].contains(ltr));
        remainder = removeChar(remainder, display[4]);
        display[1] = findIn(remainder, ltr -> numbers[2].contains(ltr));
        display[6] = removeChar(remainder, display[1]).charAt(0);

//        System.out.println(" " + display[0]);
//        System.out.println(display[1] + " " + display[2]);
//        System.out.println(" " + display[3]);
//        System.out.println(display[4] + " " + display[5]);
//        System.out.println(" " + display[6]);

        String[] displayed = lineParts[1].split(" ");
        StringBuilder numberOnDisplay = new StringBuilder();
        for (String digit: displayed) {
            if (digit.length() == 2) numberOnDisplay.append("1");
            else if (digit.length() == 3) numberOnDisplay.append("7");
            else if (digit.length() == 4) numberOnDisplay.append("4");
            else if (digit.length() == 7) numberOnDisplay.append("8");
            else if (digit.length() == 5) {
                if (!digit.contains(String.valueOf(display[5]))) numberOnDisplay.append("2");
                else if (!digit.contains(String.valueOf(display[2]))) numberOnDisplay.append("5");
                else numberOnDisplay.append("3");
            }
            else {
                if (!digit.contains(String.valueOf(display[3]))) numberOnDisplay.append("0");
                else if (!digit.contains(String.valueOf(display[2]))) numberOnDisplay.append("6");
                else numberOnDisplay.append("9");
            }
        }
        return Integer.parseInt(numberOnDisplay.toString());
    }

    private static String removeChar(String string, char... toremove) {
        String s = string;
        for (char c : toremove) {
            s = s.replaceAll(String.valueOf(c), "");
        }
        return s;
    }

    private char findIn(String letters, Function<String, Boolean> test) {
        for (char c : letters.toCharArray()) {
            if (test.apply(String.valueOf(c))) return c;
        }

        throw new RuntimeException("Char not found");
    }
}
