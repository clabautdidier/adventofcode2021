package day1;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class SolverTest {

    @Test
    void part1() throws IOException {
        Solver solver = new Solver();
        assertEquals(7, solver.part1("day1/sample.txt"));
        assertEquals(1688, solver.part1("day1/input1.txt"));
    }

    @Test
    void part2() throws IOException {
        Solver solver = new Solver();
        assertEquals(5, solver.part2("day1/sample.txt"));
        assertEquals(1728, solver.part2("day1/input1.txt"));
    }
}