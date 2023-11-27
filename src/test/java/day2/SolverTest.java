package day2;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolverTest {
    @Test
    public void part1() throws IOException {
        Solver solver = new Solver();
        assertEquals(150, solver.part1("src/test/resources/day2/sample.txt"));
        assertEquals(1947824, solver.part1("src/test/resources/day2/input1.txt"));
    }

    @Test
    public void part2() throws IOException {
        Solver solver = new Solver();
        assertEquals(900, solver.part2("src/test/resources/day2/sample.txt"));
        assertEquals(1813062561, solver.part2("src/test/resources/day2/input1.txt"));
    }
}
