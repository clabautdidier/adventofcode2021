package day12;

import common.Input;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Solver {

    public int part1(String filepath) throws IOException {
        return processFile(filepath, false);
    }

    public int part2(String filepath) throws IOException {
        return processFile(filepath, true);
    }

    private int processFile(String filepath, boolean oneDoubleVisitAllowed) {
        List<String[]> lines = Input.readLines(filepath)
                .stream()
                .map(s -> s.split("-"))
                .toList();

        List<String> paths = new ArrayList<>();
        paths.add("start");

        while (!allPathsEnd(paths)) {
            addSteps(paths, lines, oneDoubleVisitAllowed);
        }

        paths.removeIf(s -> s.endsWith(",STOP"));
        return paths.size();
    }

    private void addSteps(List<String> paths, List<String[]> lines, boolean oneDoubleVisitAllowed) {
        int size = paths.size();

        for (int i = 0; i < size; i++) {
            String path = paths.get(i);

            if (!path.endsWith(",end") && !path.endsWith(",STOP")) {
                List<String> possibleNextSteps = findNextSteps(path, lines);

                boolean newPathsFound = false;
                for (String possibleNextStep : possibleNextSteps) {
                    if (!isSmallRoom(possibleNextStep) || roomWasNotVisitedBefore(possibleNextStep, path, oneDoubleVisitAllowed)) {
                        newPathsFound = true;
                        paths.add(path + "," + possibleNextStep);
                    }
                }
                if (newPathsFound) {
                    paths.set(i, path + "_");
                }
                else {
                    paths.set(i, path + ",STOP");
                }
            }
        }
        paths.removeIf(s -> s.endsWith("_"));
    }

    private boolean roomWasNotVisitedBefore(String possibleNextStep, String path, boolean oneDoubleVisitAllowed) {
        if (possibleNextStep.equals("start")) return false;

        if (path.contains(possibleNextStep)) {
            if (oneDoubleVisitAllowed) {
                return !pathHasDoubleLowercaseRoom(path);
            }
            else
                return false;
        }

        return true;
    }

    private boolean pathHasDoubleLowercaseRoom(String path) {
        String[] steps = path.split(",");
        for (int i=0; i<steps.length-1; i++) {
            if (isSmallRoom(steps[i]) ) {
                for (int j = i + 1; j < steps.length; j++) {
                    if (Objects.equals(steps[j], steps[i])) return true;
                }
            }
        }
        return false;
    }

    private boolean isSmallRoom(String possibleNextStep) {
        return possibleNextStep.equals(possibleNextStep.toLowerCase());
    }

    private List<String> findNextSteps(String path, List<String[]> lines) {
        int lastSeparator = path.lastIndexOf(',');
        String lastStep;
        if (lastSeparator > 0)
            lastStep = path.substring(lastSeparator+1);
        else
            lastStep = path;

        List<String> nextSteps = new ArrayList<>();
        for (String[] line : lines) {
            if (line[0].equals(lastStep))
                nextSteps.add(line[1]);
            else if (line[1].equals(lastStep))
                nextSteps.add(line[0]);
        }

        return nextSteps;
    }

    private boolean allPathsEnd(List<String> paths) {
        return paths.stream().allMatch(s -> s.endsWith(",end") || s.endsWith(",STOP"));
    }

}
