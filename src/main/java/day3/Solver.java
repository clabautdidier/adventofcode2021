package day3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Solver {
    public int part1(String filepath) throws IOException {
        int lineCount = 0;
        int lineWidth = 0;
        int[] oneCount = new int[20];

        for (String line: Files.readAllLines(Path.of(filepath))) {
            lineCount++;
            int bitCount = 0;
            for (char bit: line.toCharArray()) {
                if (bit == '1') oneCount[bitCount]++;
                bitCount++;
            }
        }

        int gammaRate = 0;
        int epsilonRate = 0;
        int multiplier = 1;
        int halfOfLines = lineCount / 2;

        for (int bitIdx = 19; bitIdx >=0; bitIdx--) {
            if (oneCount[bitIdx] > 0) {
                if (oneCount[bitIdx] > halfOfLines) {
                    gammaRate += multiplier;
                }
                else {
                    epsilonRate += multiplier;
                }
                multiplier*=2;
            }
        }
        return gammaRate * epsilonRate;
    }

    public int part2(String filepath) throws IOException {
        List<String> lines = Files.readAllLines(Path.of(filepath));

        int bitIdx=0;
        int lineWidth = lines.get(0).length();

        while (bitIdx <= lineWidth && lines.size() > 1) {
            lines = filterMostCommonBit(lines, bitIdx++);
        }
        int oxygenGeneratorRating = Integer.parseInt(lines.get(0), 2);

        lines = Files.readAllLines(Path.of(filepath));

        bitIdx=0;
        while (bitIdx <= lineWidth && lines.size() > 1) {
            lines = filterLeastCommonBit(lines, bitIdx++);
        }

        int co2ScrubberRating = Integer.parseInt(lines.get(0), 2);
        return oxygenGeneratorRating * co2ScrubberRating;
    }

    private List<String> filterMostCommonBit(List<String> lines, int bitIdx) {
        int oneCount=0;
        for (String line: lines) {
            if (line.charAt(bitIdx) == '1') oneCount++;
        }
        if ((float) oneCount >= (float) lines.size() /2) {
            return lines.stream().filter(s -> s.charAt(bitIdx) == '1').toList();
        }
        else {
            return lines.stream().filter(s -> s.charAt(bitIdx) == '0').toList();
        }
    }

    private List<String> filterLeastCommonBit(List<String> lines, int bitIdx) {
        int oneCount=0;
        for (String line: lines) {
            if (line.charAt(bitIdx) == '1') oneCount++;
        }
        if ((float) oneCount < (float) lines.size() /2) {
            return lines.stream().filter(s -> s.charAt(bitIdx) == '1').toList();
        }
        else {
            return lines.stream().filter(s -> s.charAt(bitIdx) == '0').toList();
        }
    }
}
