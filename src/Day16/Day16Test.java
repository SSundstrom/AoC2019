package Day16;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class Day16Test {

    @Test
    void multiplyArray() {
        int[] l1 = {1, 2, 3, 4, 5};
        assertEquals(5 ,Day16.multiplyArray(l1, 1));
    }

    @Test
    void multiplyArray2() {
        int[] l1 = {1,2,3,4,3,2,1};
        assertEquals(0 ,Day16.multiplyArray(l1, 0), "0, 0");
        assertEquals(2 ,Day16.multiplyArray(l1, 1), "0, 1");
        assertEquals(0 ,Day16.multiplyArray(l1, 2), "0, 2");
        assertEquals(0 ,Day16.multiplyArray(l1, 3), "0, 3");
    }

    @Test
    void multiply2() {
        int[] l = {1,2,3,4,5,6,7,8};
        assertEquals(4, Day16.multiplyArray(l, 0));
    }

    @Test
    void generateLarge() {
        int[] in = {1, 2, 3, 4, 5};
        int[] largeIn = {1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5};
        assertArrayEquals(largeIn, Day16.getLargeInput(in, 3));
    }

    @Test
    void pt1ex1() {
        int[] input = "80871224585914546619083218645595".chars().map(i -> i - 48).toArray();
        String test = Day16.part1(input, 100);
        assertEquals("24176176", test);
    }

    @Test
    void pt1ex0() {
        int[] input = "12345678".chars().map(i -> i - 48).toArray();
        String test = Day16.part1(input, 1);
        assertEquals("48226158", test);
    }

    @Test
    void part1() {
        String number = "59708072843556858145230522180223745694544745622336045476506437914986923372260274801316091345126141549522285839402701823884690004497674132615520871839943084040979940198142892825326110513041581064388583488930891380942485307732666485384523705852790683809812073738758055115293090635233887206040961042759996972844810891420692117353333665907710709020698487019805669782598004799421226356372885464480818196786256472944761036204897548977647880837284232444863230958576095091824226426501119748518640709592225529707891969295441026284304137606735506294604060549102824977720776272463738349154440565501914642111802044575388635071779775767726626682303495430936326809";
        int[] input = number.chars().map(i -> i - 48).toArray();
        String test = Day16.part1(input, 100);
        assertEquals("40921727", test);
    }

    @Nested
    @Disabled
    class part2 {

        int[] input;
        int phases;

        @BeforeEach
        void setUp() {
            String number = "59708072843556858145230522180223745694544745622336045476506437914986923372260274801316091345126141549522285839402701823884690004497674132615520871839943084040979940198142892825326110513041581064388583488930891380942485307732666485384523705852790683809812073738758055115293090635233887206040961042759996972844810891420692117353333665907710709020698487019805669782598004799421226356372885464480818196786256472944761036204897548977647880837284232444863230958576095091824226426501119748518640709592225529707891969295441026284304137606735506294604060549102824977720776272463738349154440565501914642111802044575388635071779775767726626682303495430936326809";
            input = Day16.getLargeInput(number.chars().map(i -> i - 48).toArray(), 10);
            phases = 100;
        }

        @Test
        void pt2ex1() {
            int[] input = "03036732577212944063491565474664".chars().map(i -> i - 48).toArray();
            String test = Day16.part2(input,100, phases);
            assertEquals("84462026", test);
        }

        @Test
        void fori() {
            Day16.run3(input, phases);
        }

        @Test
        void parallel() {
            Day16.run(input, phases);
        }

        @Test
        void rangeSeq() {
            Day16.run2(input, phases);
        }

    }

}