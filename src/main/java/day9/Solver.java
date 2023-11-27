package day9;

import common.Input;

import java.awt.*;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class Solver {

    public int part1(String filepath) throws IOException {
        int[][] map = Input.readIntMap(filepath);

        int total = 0;
        for (int y=0; y<map.length; y++)
            for (int x=0; x<map[0].length; x++) {
                if (isLowPoint(map, x, y)) {
                    total += map[y][x] + 1;
                }
            }
        return total;
    }

    private boolean isLowPoint(int[][] map, int x, int y) {
        int ref = map[y][x];
        if (y>0 && map[y-1][x] <= ref) return false;
        if (y<map.length-1 && map[y+1][x] <= ref) return false;
        if (x>0 && map[y][x-1] <= ref) return false;
        if (x<map[0].length-1 && map[y][x+1] <= ref) return false;

        return true;
    }


    public int part2(String filepath) throws IOException {
        int[][] map = Input.readIntMap(filepath);

        List<List<Point>> basins = new ArrayList<>();
        for (int y=0; y<map.length; y++)
            for (int x=0; x<map[0].length; x++) {
                if (map[y][x] < 9) {
                    basins.add(fetchBasin(map, x, y));
                }
            }

        return basins.stream()
                .map(List::size)
                .sorted(Comparator.reverseOrder())
                .limit(3)
                .mapToInt(Integer::intValue)
                .reduce((left, right) -> left * right).orElse(0);
    }

    private List<Point> fetchBasin(int[][] map, int x, int y) {
        ArrayList<Point> points = new ArrayList<>();

        points.add(new Point(x, y));

        int idx = 0;
        while (idx < points.size()) {
            int px = points.get(idx).x;
            int py = points.get(idx).y;
            map[py][px] = 10;

            if (py > 0 && map[py - 1][px] < 9) addPointIfNew(points, px, py - 1);
            if (py < map.length - 1 && map[py + 1][px] < 9) addPointIfNew(points, px, py + 1);
            if (px > 0 && map[py][px - 1] < 9) addPointIfNew(points, px - 1, py);
            if (px < map[0].length - 1 && map[py][px + 1] < 9) addPointIfNew(points, px + 1, py);

            idx++;
        }

        return points;
    }

    private void addPointIfNew(ArrayList<Point> points, int x, int y) {
        Point point = new Point(x, y);
        if (!points.contains(point)) points.add(point);
    }

}
