package day10;

import common.Input;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Solver {

    public int part1(String filepath) throws IOException {
        List<String> lines = Input.readLines(filepath);

        int score = 0;
        for (String line : lines) {
            Stack<String> stack = new Stack<>();
            for (char c: line.toCharArray()) {
                if (isOpeningChar(c)) {
                    stack.push(String.valueOf(c));
                }
                else if (!isLegalClosingChar(c, stack.pop())) {
                    score += scoreForIllegalChar(c);
                }
            }
        }
        return score;
    }

    public long part2(String filepath) throws IOException {
        List<String> lines = Input.readLines(filepath);

        List<Long> scores = new ArrayList<>();
        for (String line : lines) {
            long score = 0;
            Stack<String> stack = new Stack<>();
            boolean discarded = false;
            for (char c: line.toCharArray()) {
                if (isOpeningChar(c)) {
                    stack.push(String.valueOf(c));
                }
                else {
                    String closingChar = stack.pop();
                    if (!isLegalClosingChar(c, closingChar)) {
                        discarded = true;
                        break;
                    }
                }
            }

            if (!discarded) {
                while (!stack.empty()) {
                    score = score * 5 + scoreForClosingChar(stack.pop());
                }

                scores.add(score);
            }

        }
        List<Long> scoreList = scores.stream().sorted().toList();
        return scoreList.get(((scoreList.size()+1)/2)-1);
    }

    private int scoreForClosingChar(String character) {
        return switch (character) {
            case "(" -> 1;
            case "[" -> 2;
            case "{" -> 3;
            case "<" -> 4;
            default -> 0;
        };
    }

    private int scoreForIllegalChar(char c) {
        return switch (c) {
            case ')' -> 3;
            case ']' -> 57;
            case '}' -> 1197;
            case '>' -> 25137;
            default -> 0;
        };
    }

    private boolean isLegalClosingChar(char closingChar, String openingChar) {
        return switch (openingChar) {
            case "<" -> closingChar == '>';
            case "{" -> closingChar == '}';
            case "[" -> closingChar == ']';
            case "(" -> closingChar == ')';
            default -> false;
        };
    }

    private boolean isOpeningChar(char c) {
        return "[<({".contains(String.valueOf(c));
    }
}
