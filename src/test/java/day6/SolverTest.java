package day6;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SolverTest {

    @Test
    void part1() throws IOException {
        Solver solver = new Solver();
        assertEquals(5934, solver.part1("day6/sample.txt"));
        assertEquals(390011, solver.part1("day6/input1.txt"));
    }

    @Test
    void part2() throws IOException {
        Solver solver = new Solver();
        assertEquals(26984457539L, solver.part2("day6/sample.txt"));
        assertEquals(1746710169834L, solver.part2("day6/input1.txt"));
    }
}