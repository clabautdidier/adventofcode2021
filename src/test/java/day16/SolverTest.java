package day16;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SolverTest {

    @Test
    void part1() throws IOException {
        Solver solver = new Solver();
        assertEquals(16, solver.part1("day16/sample.txt"));
        assertEquals(12, solver.part1("day16/sample2.txt"));
        assertEquals(23, solver.part1("day16/sample3.txt"));
        assertEquals(31, solver.part1("day16/sample4.txt"));
        assertEquals(920, solver.part1("day16/input1.txt"));
    }

    @Test
    void part2() throws IOException {
        Solver solver = new Solver();
        assertEquals(3, solver.part2("C200B40A82"));
        assertEquals(54, solver.part2("04005AC33890"));
        assertEquals(7, solver.part2("880086C3E88112"));
        assertEquals(9, solver.part2("CE00C43D881120"));
        assertEquals(1, solver.part2("D8005AC2A8F0"));
        assertEquals(0, solver.part2("F600BC2D8F"));
        assertEquals(0, solver.part2("9C005AC2F8F0"));
        assertEquals(1, solver.part2("9C0141080250320F1802104A08"));
        assertEquals(10185143721112L, solver.part2("day16/input1.txt"));
    }
}
