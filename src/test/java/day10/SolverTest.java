package day10;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SolverTest {

    @Test
    void part1() throws IOException {
        Solver solver = new Solver();
        assertEquals(26397, solver.part1("day10/sample.txt"));
        assertEquals(369105, solver.part1("day10/input1.txt"));
    }

    @Test
    void part2() throws IOException {
        Solver solver = new Solver();
        assertEquals(288957, solver.part2("day10/sample.txt"));
        assertEquals(3_999_363_569L, solver.part2("day10/input1.txt"));
    }
}
