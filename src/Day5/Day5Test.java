package Day5;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class Day5Test {

    IntProgram prg;

    @BeforeEach
    void setUp() {
        prg = new IntProgram();
    }

    void setInstructions(Stream<Integer> ints) {
        ArrayList<Integer> instructions = ints.collect(Collectors.toCollection(ArrayList::new));
        prg.setInstructions(instructions);
    }

    @RepeatedTest(10)
    void inputOutput() {
        int input = (int)(Math.random() * 10);
        setInstructions(Stream.of(3, 0, 4, 0, 99));
        prg.addInput(input);
        prg.run();
        assertEquals(input, prg.readOutput());
    }

    @Test
    void modes() {
        setInstructions(Stream.of(1002,4,3,4,33));
        prg.run();
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 1, 3, 5, 8, 9, 11})
    void equalPositional(int input) {
        setInstructions(Stream.of(3,9,8,9,10,9,4,9,99,-1,8));
        int expected = input == 8 ? 1 : 0;
        prg.addInput(input);
        prg.run();
        assertEquals(expected, prg.readOutput());
    }

    @ParameterizedTest
    @ValueSource(ints = { -1, 0, 1, 3, 5, 8, 9, 11 })
    void equalImmediate(int input) {
        setInstructions(Stream.of(3,3,1108,-1,8,3,4,3,99));
        int expected = input == 8 ? 1 : 0;
        prg.addInput(input);
        prg.run();
        assertEquals(expected, prg.readOutput());
    }

    @ParameterizedTest
    @ValueSource(ints = { -1, 0, 1, 3, 5, 8, 9, 11 })
    void lessPositional(int input) {
        setInstructions(Stream.of(3,9,7,9,10,9,4,9,99,-1,8));
        int expected = input < 8 ? 1 : 0;
        prg.addInput(input);
        prg.run();
        assertEquals(expected, prg.readOutput());
    }

    @ParameterizedTest
    @ValueSource(ints = { -1, 0, 1, 3, 5, 8, 9, 11 })
    void lessImmediate(int input) {
        setInstructions(Stream.of(3,3,1107,-1,8,3,4,3,99));
        int expected = input < 8 ? 1 : 0;
        prg.addInput(input);
        prg.run();
        assertEquals(expected, prg.readOutput());
    }

    @ParameterizedTest
    @ValueSource(ints = { -1, 0, 1, 3, 5, 8, 9, 11 })
    void jumpPositional(int input) {
        setInstructions(Stream.of(3,12,6,12,15,1,13,14,13,4,13,99,-1,0,1,9));
        int expected = input == 0 ? 0 : 1;
        prg.addInput(input);
        prg.run();
        assertEquals(expected, prg.readOutput());
    }


    @ParameterizedTest
    @ValueSource(ints = { -1, 0, 1, 3, 5, 8, 9, 11 })
    void jumpImmediate(int input) {
        setInstructions(Stream.of(3,3,1105,-1,9,1101,0,0,12,4,12,99,1));
        int expected = input == 0 ? 0 : 1;
        prg.addInput(input);
        prg.run();
        assertEquals(expected, prg.readOutput());
    }



    @ParameterizedTest
    @ValueSource(ints = { -1, 0, 1, 3, 5, 8, 9, 11 })
    void largerExample(int input) {
        setInstructions(Stream.of(3,21,1008,21,8,20,1005,20,22,107,8,21,20,1006,20,31,
                1106,0,36,98,0,0,1002,21,125,20,4,20,1105,1,46,104,
                999,1105,1,46,1101,1000,1,20,4,20,1105,1,46,98,99));
        int expected = input == 8 ? 1000 : input < 8 ? 999 : 1001;
        prg.addInput(input);
        prg.run();
        assertEquals(expected, prg.readOutput());
    }

}