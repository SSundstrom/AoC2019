package Day7;

import Utils.Input;
import Utils.IntProgram;
import Utils.General;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
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
    void day7ex1() {
        ArrayList<Integer> instruction = Stream.of(3,15,3,16,1002,16,10,16,1,16,15,15,4,15,99,0,0).collect(Collectors.toCollection(ArrayList::new));

        Stream<Integer> integerStream = General.generateCombinations(0, 4).parallelStream().map(input -> {
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

        Stream<Integer> integerStream = General.generateCombinations(0, 4).parallelStream().map(input -> {
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

        Stream<Integer> integerStream = General.generateCombinations(0, 4).parallelStream().map(input -> {
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

    @Test
    void part1() {
        String filename = "input/Day7.txt";
        ArrayList<Integer> instructions = Input.getInputListInt(filename);
        assertEquals( 359142 ,Day7.part1(instructions));
    }

    @Test
    void part2() {
        String filename = "input/Day7.txt";
        ArrayList<Integer> instructions = Input.getInputListInt(filename);
        assertEquals( 4374895 ,Day7.part2(instructions));
    }
}