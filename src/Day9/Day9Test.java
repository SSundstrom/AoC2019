package Day9;

import Utils.BigIntProgram;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class Day9Test {

    BigIntProgram prg;

    void setInstructions(Stream<BigInteger> ints) {
        ArrayList<BigInteger> instructions = ints.collect(Collectors.toCollection(ArrayList::new));
        prg.setInstructions(new ArrayList<>(instructions), 5000);
    }

    @BeforeEach
    void setUp() {
        prg = new BigIntProgram();
    }

    @Test
    void relative() {
        int input = new Random().nextInt();
        prg.setRelativeBase(2000);
        setInstructions(Stream.of(3,1985,109,19,98,204,-34,99).map(BigInteger::valueOf));
        prg.addInput(input);
        prg.run();
        assertEquals(2019, prg.getRelativeBase(), "First check!");
        prg.run();
        assertEquals(-1236463913, BigInteger.valueOf(-1236463913).intValue(), "BigInt");
        assertEquals(input, prg.readOutput().intValue());
    }

    @Test
    void copy(){
        prg = new BigIntProgram();
        ArrayList<BigInteger> input = Stream.of(109,1,204,-1,1001,100,1,100,1008,100,16,101,1006,101,0,99)
                .map(BigInteger::valueOf)
                .collect(Collectors.toCollection(ArrayList::new));
        prg.setInstructions(new ArrayList<>(input), 5000);
        prg.run();
        input.forEach(facit -> assertEquals(facit, prg.readOutput()));
    }

    @Test
    void large1() {
        setInstructions(Stream.of(1102,34915192,34915192,7,4,7,99,0).map(BigInteger::valueOf));
        prg.run();
        assertEquals(16, prg.readOutput().toString().length());
    }

    @Test
    void large2() {
        setInstructions(Stream.of("104","1125899906842624","99").map(BigInteger::new));
        prg.run();
        assertEquals(new BigInteger("1125899906842624"), prg.readOutput());
    }

    @Nested
    class oldTests {
        @ParameterizedTest
        @ValueSource(ints = {-1, 0, 1, 3, 5, 8, 9, 11})
        void equalPositional(int input) {
            setInstructions(Stream.of(3,9,8,9,10,9,4,9,99,-1,8).map(BigInteger::valueOf));
            int expected = input == 8 ? 1 : 0;
            prg.addInput(input);
            prg.run();
            assertEquals(expected, prg.readOutput().intValue());
        }

        @ParameterizedTest
        @ValueSource(ints = { -1, 0, 1, 3, 5, 8, 9, 11 })
        void equalImmediate(int input) {
            setInstructions(Stream.of(3,3,1108,-1,8,3,4,3,99).map(BigInteger::valueOf));
            int expected = input == 8 ? 1 : 0;
            prg.addInput(input);
            prg.run();
            assertEquals(expected, prg.readOutput().intValue());
        }

        @ParameterizedTest
        @ValueSource(ints = { -1, 0, 1, 3, 5, 8, 9, 11 })
        void lessPositional(int input) {
            setInstructions(Stream.of(3,9,7,9,10,9,4,9,99,-1,8).map(BigInteger::valueOf));
            int expected = input < 8 ? 1 : 0;
            prg.addInput(input);
            prg.run();
            assertEquals(expected, prg.readOutput().intValue());
        }

        @ParameterizedTest
        @ValueSource(ints = { -1, 0, 1, 3, 5, 8, 9, 11 })
        void lessImmediate(int input) {
            setInstructions(Stream.of(3,3,1107,-1,8,3,4,3,99).map(BigInteger::valueOf));
            int expected = input < 8 ? 1 : 0;
            prg.addInput(input);
            prg.run();
            assertEquals(expected, prg.readOutput().intValue());
        }

        @ParameterizedTest
        @ValueSource(ints = { -1, 0, 1, 3, 5, 8, 9, 11 })
        void jumpPositional(int input) {
            setInstructions(Stream.of(3,12,6,12,15,1,13,14,13,4,13,99,-1,0,1,9).map(BigInteger::valueOf));
            int expected = input == 0 ? 0 : 1;
            prg.addInput(input);
            prg.run();
            assertEquals(expected, prg.readOutput().intValue());
        }


        @ParameterizedTest
        @ValueSource(ints = { -1, 0, 1, 3, 5, 8, 9, 11 })
        void jumpImmediate(int input) {
            setInstructions(Stream.of(3,3,1105,-1,9,1101,0,0,12,4,12,99,1).map(BigInteger::valueOf));
            int expected = input == 0 ? 0 : 1;
            prg.addInput(input);
            prg.run();
            assertEquals(expected, prg.readOutput().intValue());
        }



        @ParameterizedTest
        @ValueSource(ints = { -1, 0, 1, 3, 5, 8, 9, 11 })
        void largerExample(int input) {
            setInstructions(Stream.of(3,21,1008,21,8,20,1005,20,22,107,8,21,20,1006,20,31,
                    1106,0,36,98,0,0,1002,21,125,20,4,20,1105,1,46,104,
                    999,1105,1,46,1101,1000,1,20,4,20,1105,1,46,98,99).map(BigInteger::valueOf));
            int expected = input == 8 ? 1000 : input < 8 ? 999 : 1001;
            prg.addInput(input);
            prg.run();
            assertEquals(BigInteger.valueOf(expected), prg.readOutput());
        }
    }

}