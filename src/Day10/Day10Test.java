package Day10;

import Utils.Pos;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class Day10Test {

    @Test
    void direction() {
        Random rnd = new Random();
        int x1 = rnd.nextInt(100);
        int y1 = rnd.nextInt(100);
        Pos a = new Pos(0, 0);
        Pos b = new Pos(10, 3);
        Pos x = new Pos(x1, y1);
        Pos y = new Pos(x1 + 10, y1 + 3);

        assertEquals(a.directionTo(b), x.directionTo(y));
    }




    @Test
    @Disabled
    void ex1() {
        Stream<String> input = Stream.of(
                "......#.#.",
                "#..#.#....",
                "..#######.",
                ".#.#.###..",
                ".#..#.....",
                "..#....#.#",
                "#..#....#.",
                ".##.#..###",
                "##...#..#.",
                ".#....####"
        );
        assertEquals(33, Day10.part1(input));
    }

    @Test
    void angle() {
        Pos a = new Pos(1, -1);
        assertEquals(Math.toRadians(45) ,Pos.NORTH.angleTo(a), 0.0001);
    }

    @Test
    void negAngle() {
        Pos a = new Pos(-1, -1);
        assertEquals(Math.toRadians(315), Pos.NORTH.angleTo(a), 0.0001);
    }

    @Test
    void negAngleLarge() {
        Pos a = new Pos(-1*123, -1*123);
        assertEquals(Math.toRadians(315), Pos.NORTH.angleTo(a), 0.0001);
    }


//
//    @Test
//    void t2ex1() {
//        Stream<String> input = Stream.of(
//                        ".#....#####...#..",
//                        "##...##.#####..##",
//                        "##...#...#.#####.",
//                        "..#.....#...###..",
//                        "..#.#.....#....##");
//        assertEquals(802, Day10.part2(input));
//    }

    @Test
    void t2ex2() {
        Stream<String> input = Stream.of(
                ".#..##.###...#######",
                "##.############..##.",
                ".#.######.########.#",
                ".###.#######.####.#.",
                "#####.##.#.##.###.##",
                "..#####..#.#########",
                "####################",
                "#.####....###.#.#.##",
                "##.#################",
                "#####.##.###..####..",
                "..######..##.#######",
                "####.##.####...##..#",
                ".#####..#.######.###",
                "##...#.##########...",
                "#.##########.#######",
                ".####.#.###.###.#.##",
                "....##.##.###..#####",
                ".#.#.###########.###",
                "#.#.#.#####.####.###",
                "###.##.####.##.#..##"
        );
        assertEquals(802, Day10.part2(input));
    }

}