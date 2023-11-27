package day17;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SolverTest {

    @Test
    void part1() throws IOException {
        Solver solver = new Solver();
        assertEquals(45, solver.part1("day17/sample.txt", false));
        assertEquals(7750, solver.part1("day17/input1.txt", false));
    }

    @Test
    void part2() throws IOException {
        Solver solver = new Solver();
        assertEquals(112, solver.part2("day17/sample.txt", false));
        assertEquals(4120, solver.part2("day17/input1.txt", false));
    }
}
