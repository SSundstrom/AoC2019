package Day8;

import Utils.Input;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class Day8Test {


    @Test
    void part1() {
        String fileName = "input/day8.txt";
        ArrayList<Integer> input = Input.getInputAsString(fileName)
                .chars()
                .map(i -> i - 48).boxed()
                .collect(Collectors.toCollection(ArrayList::new));
        int ans = Day8.part1(25, 6, input);
        assertEquals(2159, ans);
    }

    @Test
    void ex1() {
        int res = Day8.part1(3, 2, Stream.of(1,2,3,4,5,6,7,8,9,0,1,2).collect(Collectors.toCollection(ArrayList::new)));
        assertEquals(1, res);
    }

    @Test
    void countOf() {
        int[][] test = {{3, 1, 2, 3}, {0, 3, 2, 3}};
        assertEquals(2, Day8.countOf(test, 2));
        assertEquals(0, Day8.countOf(test, 6));
        assertEquals(4, Day8.countOf(test, 3));
        assertEquals(1, Day8.countOf(test, 0));
    }
}