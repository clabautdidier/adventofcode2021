package day14;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SolverTest {

    @Test
    void part1() throws IOException {
        Solver solver = new Solver();
        assertEquals(1588, solver.part1("day14/sample.txt"));
        assertEquals(3230, solver.part1("day14/input1.txt"));
    }

    @Test
    void part2() throws IOException {
        Solver solver = new Solver();
        assertEquals(2188189693529L, solver.part2("day14/sample.txt"));
        assertEquals(3542388214529L, solver.part2("day14/input1.txt"));
    }
}
