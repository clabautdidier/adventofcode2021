package day13;

import common.Input;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solver {

    public int part1(String filepath) throws IOException {
        List<String> lines = Input.readLines(filepath);
        Set<Point> points = new HashSet<>();
        List<String[]> folds = new ArrayList<>();

        boolean pointMode = true;
        for (String line : lines) {
            if (line.isEmpty()) {
                pointMode = false;
            } else if (pointMode) {
                String[] pointText = line.split(",");
                points.add(new Point(Integer.parseInt(pointText[0]), Integer.parseInt(pointText[1])));
            } else {
                String[] parts = line.split(" ");
                folds.add(parts[2].split("="));
            }
        }

//        drawPoints(points);
//        for (String[] fold : folds) {
            points = performFold(points, folds.get(0));
//        }

//        drawPoints(points);
        return points.size();
    }

    private void drawPoints(Set<Point> points) {
        int maxX = points.stream().mapToInt(p -> p.x).max().getAsInt();
        int maxY = points.stream().mapToInt(p -> p.y).max().getAsInt();

        for (int y=0; y<=maxY; y++) {
            for (int x = 0; x <= maxX; x++) {
                if (points.contains(new Point(x, y)))
                    System.out.print("X");
                else
                    System.out.print(".");
            }
            System.out.println();
        }
        System.out.println();
    }

    private Set<Point> performFold(Set<Point> points, String[] fold) {
        Set<Point> newPoints = new HashSet<>();
        int foldLine = Integer.parseInt(fold[1]);

        for (Point point : points) {
            newPoints.add(performFold(point, fold[0], foldLine));
        }
        return newPoints;
    }

    private Point performFold(Point point, String xy, int foldLine) {
        if (xy.equals("x")) {
            if (point.x > foldLine) {
                Point p = new Point(foldLine - (point.x - foldLine), point.y);
                return p;
            } else
                return new Point(point);
        }
        else {
            if (point.y > foldLine) {
                Point p = new Point(point.x, foldLine - (point.y - foldLine));
                return p;
            } else
                return new Point(point);
        }
    }

    public int part2(String filepath) throws IOException {
        List<String> lines = Input.readLines(filepath);
        Set<Point> points = new HashSet<>();
        List<String[]> folds = new ArrayList<>();

        boolean pointMode = true;
        for (String line : lines) {
            if (line.isEmpty()) {
                pointMode = false;
            } else if (pointMode) {
                String[] pointText = line.split(",");
                points.add(new Point(Integer.parseInt(pointText[0]), Integer.parseInt(pointText[1])));
            } else {
                String[] parts = line.split(" ");
                folds.add(parts[2].split("="));
            }
        }

        //drawPoints(points);
        for (String[] fold : folds) {
            points = performFold(points, fold);
        }

        drawPoints(points);
        return points.size();
    }
}
