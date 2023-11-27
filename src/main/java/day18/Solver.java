package day18;

import common.Input;

import java.io.IOException;
import java.util.List;

import static java.lang.Long.max;

public class Solver {
    public long part1(String filepath) throws IOException {
        String additionResult = processAdditions(filepath);

        return calculateMagnitude(additionResult);
    }

    public long part2(String filepath, boolean debug) throws IOException {
        List<String> lines = Input.readLines(filepath);

        long maxMagnitude = 0;
        for (int i=0; i<lines.size(); i++) {
            for (int j=i+1; j<lines.size(); j++) {
                maxMagnitude = max(max(maxMagnitude,
                        calculateMagnitude(addAndReduce(lines.get(i), lines.get(j)))),
                        calculateMagnitude(addAndReduce(lines.get(j), lines.get(i))));
            }
        }
        return maxMagnitude;
    }

    private long calculateMagnitude(String line) {
        String result = line;

        while (result.indexOf('[') >= 0) {
            int pairPos = findNestedPair(result, 0);
            int pairSeparatorPos = result.indexOf(',', pairPos);
            int pairEndBracketPos = result.indexOf(']', pairSeparatorPos);
            long leftNumber = Long.parseLong(result.substring(pairPos + 1, pairSeparatorPos));
            long rightNumber = Long.parseLong(result.substring(pairSeparatorPos + 1, pairEndBracketPos));

            result = result.substring(0, pairPos) + (leftNumber * 3 + rightNumber * 2) + result.substring(pairEndBracketPos + 1);
        }

        return Long.parseLong(result);
    }

    public String processAdditions(String filepath) {
        List<String> lines = Input.readLines(filepath);

        String result = lines.get(0);
        for (int i=1; i<lines.size(); i++) {
            result = addAndReduce(result, lines.get(i));
        }
        return result;
    }

    public String addAndReduce(String line1, String line2) {
        String result = "[%s,%s]".formatted(line1, line2);
        String reduced = reduce(result);
        while (!reduced.equals(result)) {
            result = reduced;
            reduced = reduce(result);
        }
        return result;
    }

    private String reduce(String original) {
        String result = original;
        int pairPos = findNestedPair(original, 4);
        if (pairPos > 0) {
            int separatorPos = original.indexOf(',', pairPos);
            int endBracketPos = original.indexOf(']', separatorPos);
//            System.out.println("EXPLODE: " + original + " ==> " + pairPos + " ==> " + original.substring(pairPos, endBracketPos+1));

            int leftNumber = Integer.parseInt(original.substring(pairPos + 1, separatorPos));
            int rightNumber = Integer.parseInt(original.substring(separatorPos+1, endBracketPos));

            int leftOfPairNumberPos = findNumber(original, pairPos, -1);
            int rightOfPairNumberPos = findNumber(original, endBracketPos, 1);
            if (rightOfPairNumberPos > 0) {
                int lastDigitPos = rightOfPairNumberPos;
                while (lastDigitPos < original.length() && isANumber(original.charAt(lastDigitPos+1))) lastDigitPos++;
                int rightOfPairNumber = Integer.parseInt(original.substring(rightOfPairNumberPos, lastDigitPos + 1));
                result = result.substring(0, rightOfPairNumberPos) + (rightOfPairNumber + rightNumber) + result.substring(lastDigitPos + 1);
            }
            if (leftOfPairNumberPos > 0 || rightOfPairNumberPos > 0) {
                result = result.substring(0, pairPos) + "0" + result.substring(endBracketPos + 1);
            }
            if (leftOfPairNumberPos > 0) {
                int lastDigitPos = leftOfPairNumberPos;
                while (leftOfPairNumberPos > 0 && isANumber(original.charAt(leftOfPairNumberPos-1))) leftOfPairNumberPos--;
                int leftOfPairNumber = Integer.parseInt(original.substring(leftOfPairNumberPos, lastDigitPos + 1));
                result = result.substring(0, leftOfPairNumberPos) + (leftOfPairNumber + leftNumber) + result.substring(lastDigitPos+1);
            }
        }
        else {
            int above9Pos = findNumberAbove(original, 9);
            if (above9Pos > 0) {
                int lastDigitPos = above9Pos;
                while (lastDigitPos < original.length() && isANumber(original.charAt(lastDigitPos+1))) lastDigitPos++;
                int above9Number = Integer.parseInt(original.substring(above9Pos, lastDigitPos+1));
//                System.out.println("SPLIT  : " + original + " ==> " + above9Number);
                int leftNumber = above9Number / 2;
                int rightNumber = (above9Number % 2 == 0) ? leftNumber : leftNumber + 1;
                result = result.substring(0, above9Pos) + "[%d,%d]".formatted(leftNumber, rightNumber) + result.substring(lastDigitPos + 1);
            }
        }
        return result;
    }

    private int findNumberAbove(String line, int ref) {
        int pos = 0;
        int number = 0;
        int numberPos = 0;

        while (pos < line.length()) {
            if (isANumber(line.charAt(pos))) {
                if (numberPos == 0) numberPos = pos;
                number = number*10 + Integer.parseInt(line.substring(pos, pos+1));
            }
            else {
                if (number > ref)
                    return numberPos;
                else {
                    numberPos = 0;
                    number = 0;
                }
            }
            pos++;
        }
        return -1;
    }

    private boolean isANumber(char c) {
        return c >= '0' && c <= '9';
    }

    private int findNumber(String line, int startPos, int direction) {
        int pos = startPos;
        while (pos >= 0 && pos < line.length() && !isANumber(line.charAt(pos))) pos += direction;

        if (pos >= line.length()) return -1;

        return pos;
    }

    public int findNestedPair(String line, int findNestLevel) {
        int pos = 0;
        int nestLevel = 0;
        while (pos >= 0) {
            while (pos < line.length() && line.charAt(pos) != '[') {
                if (line.charAt(pos) == ']') nestLevel--;
                pos++;
            }

            if (pos >= line.length()) pos = -1;

            if (pos >= 0) {
                nestLevel++;
                int closeBracketPos = line.indexOf(']', pos);
                int otherOpeningBracketPos = line.indexOf('[', pos+1);
                if (otherOpeningBracketPos > closeBracketPos || otherOpeningBracketPos < 0) {
                    if (nestLevel > findNestLevel)
                        return pos;
                    else {
                        nestLevel--;
                        pos = closeBracketPos + 1;
                    }
                }
                else
                    pos = otherOpeningBracketPos;
            }
        }

        return pos;
    }
}
