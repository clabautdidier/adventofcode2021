package day1;

import common.Input;

import java.util.List;

public class Solver {
    public int part1(String filename) {
        int count = 0;
        int previous = Integer.MAX_VALUE;

        for (String line: Input.readLines(filename)) {
            int depth = Integer.parseInt(line);

            if (depth > previous) {
                count++;
            }

            previous = depth;
        }
        return count;
    }

    public int part2(String filepath) {
        int count = 0;
        int previous = Integer.MAX_VALUE;

        List<String> readLines = Input.readLines(filepath);
        for (int i = 1; i < readLines.size()-1; i++) {
            int depth =
                    Integer.parseInt(readLines.get(i-1))
                    + Integer.parseInt(readLines.get(i))
                    + Integer.parseInt(readLines.get(i+1));

            if (depth > previous) {
                count++;
            }

            previous = depth;
        }
        return count;
    }
}
