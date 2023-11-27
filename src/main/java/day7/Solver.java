package day7;

import common.Input;

import java.io.IOException;
import java.util.Arrays;

public class Solver {

    public int part1(String filepath) throws IOException {
        int[] positions = Input.readIntLine(filepath);
        Arrays.sort(positions);

        int lowestTotalDistance = calculateTotalDistance(positions, positions[0]);

        for (int position : positions) {
            int currentTotalDistance = calculateTotalDistance(positions, position);

            if (currentTotalDistance < lowestTotalDistance) {
                lowestTotalDistance = currentTotalDistance;
            }
        }

        return lowestTotalDistance;
    }

    private int calculateTotalDistance(int[] positions, int referencePoint) {
        int total = 0;
        for (int position : positions) {
            total += Math.abs(position - referencePoint);
        }
        return total;
    }

    private int calculateTotalDistancePart2(int[] positions, int referencePoint) {
        int total = 0;
        for (int position : positions) {
            total += part2Modifier(Math.abs(position - referencePoint));
        }
        return total;
    }


    public int part2(String filepath) throws IOException {
        int[] positions = Input.readIntLine(filepath);
        Arrays.sort(positions);

        int lowestTotalDistance = calculateTotalDistancePart2(positions, positions[0]);

        for (int position = 0; position < positions[positions.length-1]; position++) {
            int currentTotalDistance = calculateTotalDistancePart2(positions, position);

            if (currentTotalDistance < lowestTotalDistance) {
                lowestTotalDistance = currentTotalDistance;
            }
        }

        return lowestTotalDistance;
    }

    private int part2Modifier(int distance) {
        return (distance * (distance+1))/2;
    }
}
