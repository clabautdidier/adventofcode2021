package day17;

import common.Input;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class Solver {
    Logger LOG = LoggerFactory.getLogger(Solver.class);

    public int part1(String filepath, boolean debug) throws IOException {
        Rectangle rectangle = getRectangle(filepath);
        List<List<Integer>> possibleXTrajectories = findPossibleXTrajectories(rectangle);
        Set<Integer> sizesX = possibleXTrajectories.stream().map(List::size).collect(Collectors.toSet());
        int maxSteps = possibleXTrajectories.stream().mapToInt(List::size).max().orElse(0);
        List<List<Integer>> possibleYTrajectories = findPossibleYTrajectories(rectangle, maxSteps);
        Set<List<Integer>> filteredYTrajectories = possibleYTrajectories.stream().filter(l -> l.size() > maxSteps || sizesX.contains(l.size())).collect(Collectors.toSet());

        return filteredYTrajectories.stream().mapToInt(l -> l.stream().mapToInt(Integer::intValue).max().orElse(0)).max().orElse(0);
    }

    public long part2(String filepath, boolean debug) throws IOException {
        Rectangle rectangle = getRectangle(filepath);
        List<List<Integer>> possibleXTrajectories = findPossibleXTrajectories(rectangle);
        Set<Integer> sizesX = possibleXTrajectories.stream().map(List::size).collect(Collectors.toSet());
        int maxSteps = possibleXTrajectories.stream().mapToInt(List::size).max().orElse(0);
        List<List<Integer>> possibleYTrajectories = findPossibleYTrajectories(rectangle, maxSteps);
        Set<List<Integer>> filteredYTrajectories = possibleYTrajectories.stream().filter(l -> l.size() > maxSteps || sizesX.contains(l.size())).collect(Collectors.toSet());

        Set<Point> velocities = new HashSet<>();
        int x1 = rectangle.x;
        int x2 = x1 + rectangle.width;
        int y1 = rectangle.y;
        int y2 = y1 - rectangle.height;

        for (List<Integer> xTrajectory: possibleXTrajectories) {
            for (List<Integer> yTrajectory: possibleYTrajectories) {
                Point pointStart = new Point(xTrajectory.get(0), yTrajectory.get(0));
                Point pointEnd = new Point(xTrajectory.get(xTrajectory.size()-1), yTrajectory.get(yTrajectory.size()-1));
                if (xTrajectory.size() == yTrajectory.size()) {
                    velocities.add(pointStart);
                }
                else if (xTrajectory.size() >= xTrajectory.get(0) && yTrajectory.size() > xTrajectory.size()) {
                    velocities.add(pointStart);
                }
            }
        }
        velocities.forEach(p -> System.out.printf("%d,%d  ", p.x, p.y));
        return velocities.size();
    }

    private List<List<Integer>> findPossibleYTrajectories(Rectangle rectangle, int maxSteps) {
        List<List<Integer>> trajectories = new ArrayList<>();
        int y = rectangle.y - rectangle.height;
        int maxY = -y;

        while (y < maxY) {
            List<Integer> trajectory = calculateYTrajectory(y, rectangle.y, rectangle.y - rectangle.height);
            if (trajectory != null) {
                trajectories.add(trajectory);
                while (trajectory.size() > 2 && trajectory.get(trajectory.size()-2) <= rectangle.y && trajectory.get(trajectory.size()-2) >= rectangle.y - rectangle.height) {
                    trajectory = new ArrayList<>(trajectory);
                    trajectory.remove(trajectory.size() - 1);
                    trajectories.add(trajectory);
                }
            }
            y++;
        }
        return trajectories;
    }

    private List<Integer> calculateYTrajectory(int startSpeed, int top, int bottom) {
        List<Integer> integers = new ArrayList<>();
        int speed = startSpeed;
        int y = speed;
        while (y >= bottom) {
            integers.add(y);
            speed--;
            y += speed;
        }
        if (integers.isEmpty()) return null;

        Integer lastY = integers.get(integers.size() - 1);
        if (lastY <= top && lastY >= bottom)
            return integers;
        else
            return null;
    }

    private List<List<Integer>> findPossibleXTrajectories(Rectangle rectangle) {
        List<List<Integer>> trajectories = new ArrayList<>();
        int x = rectangle.x + rectangle.width;

        while (x > 0) {
            List<Integer> trajectory = calculateXTrajectory(x, rectangle.x, rectangle.x + rectangle.width);
            if (trajectory != null) {
                trajectories.add(trajectory);
                List<Integer> shorterList = trajectory.stream().toList();
                while (shorterList.size() > 2 && shorterList.get(shorterList.size()-2) >= rectangle.x) {
                    shorterList = shorterList.subList(0, shorterList.size() - 1);
                    trajectories.add(shorterList);
                }
            }
            x--;
        }

        return trajectories;
    }

    private List<Integer> calculateXTrajectory(int startSpeed, int start, int end) {
        List<Integer> integers = new ArrayList<>();
        int speed = startSpeed;
        int x = speed;
        while (x <= end && speed > 0) {
            integers.add(x);
            speed--;
            x += speed;
        }
        if (integers.isEmpty()) return null;

        Integer lastX = integers.get(integers.size() - 1);
        if (lastX >= start && lastX <= end)
            return integers;
        else
            return null;
    }

    private Rectangle getRectangle(String filepath) {
        String line = Input.readLines(filepath).get(0);
        String[] parts = line.split(":\\s|,\\s");
        String[] xParts = parts[1].substring(2).split("\\.\\.");
        String[] yParts = parts[2].substring(2).split("\\.\\.");
        Rectangle rectangle = new Rectangle(
                Integer.parseInt(xParts[0]),
                Integer.parseInt(yParts[1]),
                Integer.parseInt(xParts[1]) - Integer.parseInt(xParts[0]),
                Math.abs(Integer.parseInt(yParts[0]) - Integer.parseInt(yParts[1]))
        );
        return rectangle;
    }
}
