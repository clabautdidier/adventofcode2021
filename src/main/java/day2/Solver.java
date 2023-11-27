package day2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Solver {
    public int part1(String filepath) throws IOException {
        int x = 0;
        int y = 0;

        for (String line: Files.readAllLines(Path.of(filepath))) {
            String[] parts = line.split(" ");
            int positionsToMove = Integer.parseInt(parts[1]);
            switch (parts[0]) {
                case "forward": x+=positionsToMove; break;
                case "down": y+=positionsToMove; break;
                case "up": y-=positionsToMove; break;
            }
//            System.out.printf("%d:%d\n", x, y);
        }
        return x * y;
    }

    public int part2(String filepath) throws IOException {
        int x = 0;
        int y = 0;
        int aim = 0;

        for (String line: Files.readAllLines(Path.of(filepath))) {
            String[] parts = line.split(" ");
            int positionsToMove = Integer.parseInt(parts[1]);
            switch (parts[0]) {
                case "forward": {
                    x += positionsToMove;
                    y += (aim * positionsToMove);
                    break;
                }
                case "down": aim+=positionsToMove; break;
                case "up": aim-=positionsToMove; break;
            }
//            System.out.printf("%d:%d\n", x, y);
        }
        return x * y;
    }
}
