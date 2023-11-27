package day15;

import common.Input;

import java.io.IOException;
import java.util.List;

public class Solver {

    public int part1(String filepath) throws IOException {
        List<String> lines = Input.readLines(filepath);
        int[][] matrix = new int[lines.size()][lines.get(0).length()];
        int[][] distances = new int[lines.size()][lines.get(0).length()];

        for (int y=0; y< matrix.length; y++)
            for (int x=0; x<matrix[0].length; x++) {
                matrix[y][x] = Integer.parseInt(lines.get(y).substring(x, x + 1));
                distances[y][x] = -1;
            }
        distances[0][0] = 0;

        int targetY = distances.length-1;
        int targetX = distances[0].length-1;
        while (hasUnmarkedCells(distances)) {
            processMatrix(matrix, distances);
        }

        processMatrix(matrix, distances);

        return distances[targetY][targetX];
    }

    public int part2(String filepath) throws IOException {
        List<String> lines = Input.readLines(filepath);
        int[][] matrix = new int[lines.size()*5][lines.get(0).length()*5];
        int[][] distances = new int[lines.size()*5][lines.get(0).length()*5];

        int originalSizeX = lines.get(0).length();
        int originalSizeY = lines.size();

        for (int y=0; y< matrix.length; y++)
            for (int x=0; x<matrix[0].length; x++) {
                int addX = x / originalSizeX;
                int addY = y / originalSizeY;
                int newValue = Integer.parseInt(lines.get(y % originalSizeY).substring(x % originalSizeX, (x % originalSizeX) + 1)) + addX + addY;
                matrix[y][x] = newValue > 9 ? (newValue % 10) + 1 : newValue;
                distances[y][x] = -1;
            }

//        for (int y=0; y< matrix.length; y++) {
//            for (int x = 0; x < matrix[0].length; x++)
//                System.out.print(matrix[y][x]);
//            System.out.println();
//        }

        distances[0][0] = 0;

        int targetY = distances.length-1;
        int targetX = distances[0].length-1;
        while (hasUnmarkedCells(distances)) {
            processMatrix(matrix, distances);
        }

        for (int i=0; i<5; i++) {
            processMatrix(matrix, distances);
        }
        return distances[targetY][targetX];
    }

    private void processMatrix(int[][] matrix, int[][] distances) {
        for (int y = 0; y< matrix.length; y++)
            for (int x = 0; x< matrix[0].length; x++) {
                int currentDistance = distances[y][x];
                if (currentDistance >= 0) {
                    tryPath(distances, matrix, y - 1, x, currentDistance);
                    tryPath(distances, matrix, y + 1, x, currentDistance);
                    tryPath(distances, matrix, y, x - 1, currentDistance);
                    tryPath(distances, matrix, y, x + 1, currentDistance);
                }

            }
    }

    private int tryPath(int[][] distances, int[][] matrix, int y, int x, int currentDistance) {
        if (y < 0 || y >= distances.length) return 0;
        if (x < 0 || x >= distances[0].length) return 0;

        if (distances[y][x] < 0 || currentDistance + matrix[y][x] < distances[y][x]) {
            distances[y][x] = currentDistance + matrix[y][x];
        }

        return distances[y][x];
    }

    private boolean hasUnmarkedCells(int[][] distances) {
        for (int y=0; y< distances.length; y++)
            for (int x=0; x<distances[0].length; x++) {
                if (distances[y][x] < 0) return true;
            }

        return false;
    }

}
