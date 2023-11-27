package common;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Input {
    public static List<String> readLines(String filepath) {
        try {
            return Files.readAllLines(Path.of("src/test/resources", filepath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static int[] readIntLine(String filepath) {
        String line = readLines(filepath).get(0);
        String[] intTexts = line.split(",");
        int[] intArray = new int[intTexts.length];

        for (int i = 0; i < intArray.length; i++) intArray[i] = Integer.parseInt(intTexts[i]);

        return intArray;
    }

    public static int[][] readIntMap(String filepath) {
        List<String> lines = readLines(filepath);
        int[][] map = new int[lines.size()][lines.get(0).length()];
        for (int y = 0; y < lines.size(); y++) {
            char[] chars = lines.get(y).toCharArray();

            for (int x = 0; x < chars.length; x++) {
                map[y][x] = Integer.parseInt(String.valueOf(chars[x]));
            }
        }
        return map;
    }
}
