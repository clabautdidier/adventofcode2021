package day0;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SolverTest {

    @Test
    void part1() throws IOException {
        Solver solver = new Solver();
        assertEquals(99999, solver.part1("day0/sample.txt", false));
//        assertEquals(99999, solver.part1("day0/input1.txt", false));
    }

    @Test
    void part2() throws IOException {
        Solver solver = new Solver();
        assertEquals(99999, solver.part2("day0/sample.txt", false));
//        assertEquals(99999, solver.part2("day0/input1.txt", false));
    }
}