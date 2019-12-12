package Day12;

import Utils.Pos3;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class Day12Test {

    Stream<String> ex1;
    Stream<String> ex2;

    @BeforeEach
    void setUp() {
        ex1 =  Stream.of(
            "<x=-1, y=0, z=2>",
            "<x=2, y=-10, z=-7>",
            "<x=4, y=-8, z=8>",
            "<x=3, y=5, z=-1>"
        );
        ex2 = Stream.of(
                "<x=-8, y=-10, z=0>",
                "<x=5, y=5, z=10>",
                "<x=2, y=-7, z=3>",
                "<x=9, y=-8, z=-3>"
        );
    }

    @Test
    void parseInput() {
        String input = "<x=2, y=-10, z=-7>";
        assertEquals(new Pos3(2, -10, -7), Day12.parseInput(input));
    }

    @Test
    void ex1() {
        assertEquals(179, Day12.part1(ex1, 10));
    }

    @Test
    void ex2(){
        assertEquals(1940, Day12.part1(ex2, 100));
    }

    @Test
    void p2ex1(){
        assertEquals(BigInteger.valueOf(2772), Day12.part2(ex1));
    }

    @Test
    void p2ex2(){
        assertEquals(new BigInteger("4686774924"), Day12.part2(ex2));
    }
}