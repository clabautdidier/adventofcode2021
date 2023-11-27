package day18;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SolverTest {

    @Test
    void part1() throws IOException {
        Solver solver = new Solver();
        assertEquals(35, solver.findNestedPair("[[[[4,0],[5,11]],[15,0]],[26,[9,[0,[11,8]]]]]", 4));
        assertEquals(-1, solver.findNestedPair("[[[[0,7],4],[15,[0,13]]],[1,1]]", 4));

        assertEquals("[[[[0,7],4],[[7,8],[6,0]]],[8,1]]", solver.addAndReduce("[[[[4,3],4],4],[7,[[8,4],9]]]", "[1,1]"));

        assertEquals("[[[[8,7],[7,7]],[[8,6],[7,7]]],[[[0,7],[6,6]],[8,7]]]", solver.processAdditions("day18/sample2.txt"));
        assertEquals(4140, solver.part1("day18/sample.txt"));
        assertEquals(3216, solver.part1("day18/input1.txt"));
    }

    @Test
    void part2() throws IOException {
        Solver solver = new Solver();
        assertEquals(3993, solver.part2("day18/sample.txt", false));
        assertEquals(4643, solver.part2("day18/input1.txt", false));
    }
}
