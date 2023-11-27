package day12;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SolverTest {

    @Test
    void part1() throws IOException {
        Solver solver = new Solver();
        assertEquals(10, solver.part1("day12/sample.txt"));
        assertEquals(19, solver.part1("day12/sample2.txt"));
        assertEquals(226, solver.part1("day12/sample3.txt"));
        assertEquals(5457, solver.part1("day12/input1.txt"));
    }

    @Test
    void part2() throws IOException {
        Solver solver = new Solver();
        assertEquals(36, solver.part2("day12/sample.txt"));
        assertEquals(103, solver.part2("day12/sample2.txt"));
        assertEquals(3509, solver.part2("day12/sample3.txt"));
        assertEquals(128506, solver.part2("day12/input1.txt"));
    }
}
