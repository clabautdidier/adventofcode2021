package day11;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SolverTest {

    @Test
    void part1() throws IOException {
        Solver solver = new Solver();
        assertEquals(1656, solver.part1("day11/sample.txt"));
        assertEquals(1694, solver.part1("day11/input1.txt"));
    }

    @Test
    void part2() throws IOException {
        Solver solver = new Solver();
        assertEquals(195, solver.part2("day11/sample.txt"));
        assertEquals(346, solver.part2("day11/input1.txt"));
    }
}
