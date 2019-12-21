package Day18;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day18Test {

    @Test
    @Disabled
    void pt1ex1() {
        String input =  "#########\n"+
                        "#b.A.@.a#\n"+
                        "#########\n";
        assertEquals(8, Day18.run(input).distance);
    }

    @Test
    void pt1ex2() {
        String input =
                "########################\n" +
                "#f.D.E.e.C.b.A.@.a.B.c.#\n" +
                "######################.#\n" +
                "#d.....................#\n" +
                "########################\n";

        assertEquals(86, Day18.run(input).distance);
    }
}