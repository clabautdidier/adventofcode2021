package day13;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SolverTest {

    @Test
    void part1() throws IOException {
        Solver solver = new Solver();
        assertEquals(17, solver.part1("day13/sample.txt"));
        assertEquals(720, solver.part1("day13/input1.txt"));
    }

    @Test
    void part2() throws IOException {
        Solver solver = new Solver();
        assertEquals(16, solver.part2("day13/sample.txt"));
        assertEquals(104, solver.part2("day13/input1.txt"));
        // CODE = AHPRPAUZ
    }
}
