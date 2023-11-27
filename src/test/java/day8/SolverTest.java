package day8;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SolverTest {

    @Test
    void part1() throws IOException {
        Solver solver = new Solver();
        assertEquals(26, solver.part1("day8/sample.txt"));
        assertEquals(445, solver.part1("day8/input1.txt"));
    }

    @Test
    void part2() throws IOException {
        Solver solver = new Solver();
        assertEquals(61229, solver.part2("day8/sample.txt"));
        assertEquals(1043101, solver.part2("day8/input1.txt"));
    }
}
