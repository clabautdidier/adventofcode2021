package day5;

import common.Input;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solver {

    public int part1(String filepath) throws IOException {
        return processInput(filepath, false);
    }

    public int part2(String filepath) throws IOException {
        return processInput(filepath, true);
    }

    private int processInput(String filepath, boolean allowDiagonal) {
        List<String> lines = Input.readLines(filepath);

        Set<Point> points = new HashSet<>();
        Set<Point> overlappingPoints = new HashSet<>();

        lines.forEach(line -> {
            List<Point> pointsOnLine = parsePoints(line, allowDiagonal);
            pointsOnLine.forEach(point -> {
                if (points.contains(point))
                    overlappingPoints.add(point);
                else
                    points.add(point);
            });
        });

//        printBoard(overlappingPoints, points);
        return overlappingPoints.size();
    }

    private static void printBoard(Set<Point> overlappingPoints, Set<Point> points) {
        for (int y=0; y<10; y++) {
            for (int x = 0; x < 10; x++) {
                Point thisPoint = new Point(x, y);
                if (overlappingPoints.contains(thisPoint))
                    System.out.print("2");
                else if (points.contains(thisPoint))
                    System.out.print("1");
                else
                    System.out.print(".");
            }
            System.out.println();
        }
    }

    private List<Point> parsePoints(String line, boolean allowDiagonal) {
        List<Point> points = new ArrayList<>();
        String[] pointsText = line.split(" -> ");
        Point start = parsePoint(pointsText[0]);
        Point end = parsePoint(pointsText[1]);

        if (start.x == end.x || start.y == end.y || allowDiagonal) {
            int y = start.y;
            int x = start.x;
            int dirY = Integer.compare(end.y, start.y);
            int dirX = Integer.compare(end.x, start.x);

            while (x!=end.x || y!=end.y) {
                points.add(new Point(x, y));
                x+=dirX;
                y+=dirY;
            }
            points.add(new Point(x, y));
        }
//        if (start.x == end.x) {
//            int y = Math.min(start.y, end.y);
//            int endY = Math.max(start.y, end.y);
//            while (y<=endY) {
//                points.add(new Point(start.x, y));
//                y++;
//            }
//        }
//        else if (start.y == end.y) {
//            int x = Math.min(start.x, end.x);
//            int endX = Math.max(start.x, end.x);
//            while (x<=endX) {
//                points.add(new Point(x, start.y));
//                x++;
//            }
//        }

        return points;
    }

    private Point parsePoint(String pointText) {
        String[] p = pointText.split(",");

        return new Point(Integer.parseInt(p[0]), Integer.parseInt(p[1]));
    }
}
