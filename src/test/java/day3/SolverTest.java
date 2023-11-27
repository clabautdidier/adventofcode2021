package day3;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolverTest {
    @Test
    public void part1() throws IOException {
        Solver solver = new Solver();
        assertEquals(198, solver.part1("src/test/resources/day3/sample.txt"));
        assertEquals(3309596, solver.part1("src/test/resources/day3/input1.txt"));
    }

    @Test
    public void part2() throws IOException {
        Solver solver = new Solver();
        assertEquals(230, solver.part2("src/test/resources/day3/sample.txt"));
        assertEquals(2981085, solver.part2("src/test/resources/day3/input1.txt"));
    }
}
