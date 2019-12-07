package Day7;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day7Test {

    IntProgram prg;
    ArrayList<IntProgram> prgs;

    @BeforeEach
    void setUp() {
        prgs = new ArrayList<>();
        prg = new IntProgram();
        for (int i = 0; i < 5; i++) {
            prgs.add(new IntProgram());
        }
    }

    void setInstructions(Stream<Integer> ints) {
        ArrayList<Integer> instructions = ints.collect(Collectors.toCollection(ArrayList::new));
        prg.setInstructions(new ArrayList<>(instructions));
        prgs.forEach(intProgram -> intProgram.setInstructions(new ArrayList<>(instructions)));
    }

    @Test
    void combinations() {
        assertEquals( 120, Day7.generateCombinations(0, 4).size());
    }

    @Test
    void day7ex1() {
        ArrayList<Integer> instruction = Stream.of(3,15,3,16,1002,16,10,16,1,16,15,15,4,15,99,0,0).collect(Collectors.toCollection(ArrayList::new));

        Stream<Integer> integerStream = Day7.generateCombinations(0, 4).parallelStream().map(input -> {
            ArrayList<IntProgram> prgs = Day7.resetPrograms(new ArrayList<>(instruction));
            for (int index = 0; index < prgs.size(); index++) {
                prgs.get(index).addInput(input.get(index));
            }
            Day7.run(prgs);
            return prgs.get(4).readOutput();
        });

        int maxVal = integerStream.max(Integer::compareTo).get();

        assertEquals(43210, maxVal);
    }

    @Test
    void day7ex2() {
        ArrayList<Integer> instruction = Stream.of(3,23,3,24,1002,24,10,24,1002,23,-1,23,
                101,5,23,23,1,24,23,23,4,23,99,0,0).collect(Collectors.toCollection(ArrayList::new));

        Stream<Integer> integerStream = Day7.generateCombinations(0, 4).parallelStream().map(input -> {
            ArrayList<IntProgram> prgs = Day7.resetPrograms(new ArrayList<>(instruction));
            for (int index = 0; index < prgs.size(); index++) {
                prgs.get(index).addInput(input.get(index));
            }
            Day7.run(prgs);
            return prgs.get(4).readOutput();
        });

        int maxVal = integerStream.max(Integer::compareTo).get();

        assertEquals(54321, maxVal);
    }

    @Test
    void day7ex3() {
        ArrayList<Integer> instruction = Stream.of(3,31,3,32,1002,32,10,32,1001,31,-2,31,1007,31,0,33,
                1002,33,7,33,1,33,31,31,1,32,31,31,4,31,99,0,0,0).collect(Collectors.toCollection(ArrayList::new));

        Stream<Integer> integerStream = Day7.generateCombinations(0, 4).parallelStream().map(input -> {
            ArrayList<IntProgram> prgs = Day7.resetPrograms(new ArrayList<>(instruction));
            for (int index = 0; index < prgs.size(); index++) {
                prgs.get(index).addInput(input.get(index));
            }
            Day7.run(prgs);
            return prgs.get(4).readOutput();
        });

        int maxVal = integerStream.max(Integer::compareTo).get();

        assertEquals(65210, maxVal);
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