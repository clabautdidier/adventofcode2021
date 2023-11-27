package day11;

import common.Input;

import java.io.IOException;
import java.util.function.Function;

public class Solver {

    public int part1(String filepath) throws IOException {
        int[][] map = Input.readIntMap(filepath);

        int flashes = 0;
        for (int i=0; i<100; i++) {
            increaseLevels(map);
            while (processFlashes(map) > 0);
            flashes += countAndResetFlashes(map);
        }
        return flashes;
    }

    public int part2(String filepath) throws IOException {
        int[][] map = Input.readIntMap(filepath);

        int flashes = 0;
        int counter = 0;
        while (flashes < 100) {
            counter++;
            increaseLevels(map);
            while (processFlashes(map) > 0);
            flashes = countAndResetFlashes(map);
        }
        return counter;
    }

    private int countAndResetFlashes(int[][] map) {
        int count = 0;

        for (int y=0; y<map.length; y++)
            for (int x=0; x<map.length; x++) {
                if (map[y][x] == 11) {
                    count++;
                    map[y][x] = 0;
                }
            }

        return count;
    }

    private int processFlashes(int[][] map) {
        int count = 0;

        for (int y=0; y<map.length; y++)
            for (int x=0; x<map.length; x++) {
                if (map[y][x] == 10) {
                    map[y][x] = 11;
                    count += doIfExists(map, x-1, y-1, value -> value < 10, value -> value+1);
                    count += doIfExists(map, x, y-1, value -> value < 10, value -> value+1);
                    count += doIfExists(map, x+1, y-1, value -> value < 10, value -> value+1);
                    count += doIfExists(map, x-1, y, value -> value < 10, value -> value+1);
                    count += doIfExists(map, x+1, y, value -> value < 10, value -> value+1);
                    count += doIfExists(map, x-1, y+1, value -> value < 10, value -> value+1);
                    count += doIfExists(map, x, y+1, value -> value < 10, value -> value+1);
                    count += doIfExists(map, x+1, y+1, value -> value < 10, value -> value+1);
                }
            }
        return count;
    }

    private int doIfExists(int[][] map, int x, int y, Function<Integer, Boolean> condition, Function<Integer, Integer> action) {
        if (x >= 0 && x < map[0].length && y >= 0 && y < map.length) {
            if (condition.apply(map[y][x])) {
                map[y][x] = action.apply(map[y][x]);
                return 1;
            }
        }
        return 0;
    }

    private void increaseLevels(int[][] map) {
        for (int y=0; y<map.length; y++)
            for (int x=0; x<map.length; x++) {
                map[y][x] = map[y][x] + 1;
            }
    }
}
