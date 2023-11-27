package day5;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolverTest {
    @Test
    public void part1() throws IOException {
        Solver solver = new Solver();
        assertEquals(5, solver.part1("day5/sample.txt"));
        assertEquals(4728, solver.part1("day5/input1.txt"));
    }

    @Test
    public void part2() throws IOException {
        Solver solver = new Solver();
        assertEquals(12, solver.part2("day5/sample.txt"));
        assertEquals(17717, solver.part2("day5/input1.txt"));
    }
}
