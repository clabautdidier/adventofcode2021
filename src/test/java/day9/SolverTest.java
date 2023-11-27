package day9;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SolverTest {

    @Test
    void part1() throws IOException {
        Solver solver = new Solver();
        assertEquals(15, solver.part1("day9/sample.txt"));
        assertEquals(494, solver.part1("day9/input1.txt"));
    }

    @Test
    void part2() throws IOException {
        Solver solver = new Solver();
        assertEquals(1134, solver.part2("day9/sample.txt"));
        assertEquals(1048128, solver.part2("day9/input1.txt"));
    }
}
