package day6;

import common.Input;

import java.io.IOException;

public class Solver {

    public long part1(String filepath) throws IOException {
        return processFile(filepath, 80);
    }

    private long processFile(String filepath, int days) {
        String line = Input.readLines(filepath).get(0);
        String[] allFish = line.split(",");
        long[] timers = new long[9];
        for (String fish : allFish) {
            timers[Integer.parseInt(fish)]++;
        }

        for (int day = 0; day< days; day++) {
            long newFish = timers[0];
            long resetFish = timers[0];
            for (int i=0; i<8; i++) {
                timers[i] = timers[i + 1];
            }
            timers[6] += resetFish;
            timers[8] = newFish;
        }

        return timers[0]
                + timers[1]
                + timers[2]
                + timers[3]
                + timers[4]
                + timers[5]
                + timers[6]
                + timers[7]
                + timers[8];
    }

    public long part2(String filepath) throws IOException {
        return processFile(filepath, 256);
    }
}
