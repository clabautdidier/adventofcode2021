package day7;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SolverTest {

    @Test
    void part1() throws IOException {
        Solver solver = new Solver();
        assertEquals(37, solver.part1("day7/sample.txt"));
        assertEquals(344138, solver.part1("day7/input1.txt"));
    }

    @Test
    void part2() throws IOException {
        Solver solver = new Solver();
        assertEquals(168, solver.part2("day7/sample.txt"));
        assertEquals(94862124, solver.part2("day7/input1.txt"));
    }
}
