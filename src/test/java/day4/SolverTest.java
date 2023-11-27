package day4;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolverTest {
    @Test
    public void part1() throws IOException {
        Solver solver = new Solver();
        assertEquals(4512, solver.part1("day4/sample.txt"));
        assertEquals(64084, solver.part1("day4/input1.txt"));
    }

    @Test
    public void part2() throws IOException {
        Solver solver = new Solver();
        assertEquals(1924, solver.part2("day4/sample.txt"));
        assertEquals(12833, solver.part2("day4/input1.txt"));
    }
}
