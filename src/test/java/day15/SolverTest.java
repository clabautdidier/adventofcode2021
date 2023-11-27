package day15;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SolverTest {

    @Test
    void part1() throws IOException {
        Solver solver = new Solver();
        assertEquals(40, solver.part1("day15/sample.txt"));
        assertEquals(714, solver.part1("day15/input1.txt"));
    }

    @Test
    void part2() throws IOException {
        Solver solver = new Solver();
        assertEquals(315, solver.part2("day15/sample.txt"));
        assertEquals(2948, solver.part2("day15/input1.txt"));
    }
}
