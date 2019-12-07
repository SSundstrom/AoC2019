package Day7;

import Utils.Input;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.stream.Stream;

public class Day7 {

    static void part1(ArrayList<Integer> instructions) {
        LinkedList<ArrayList<Integer>> inputs = generateCombinations(0, 4);

        Stream<Integer> integerStream = inputs.parallelStream().map(input -> {
            ArrayList<IntProgram> prgs = resetPrograms(instructions);
            for (int index = 0; index < prgs.size(); index++) {
                prgs.get(index).addInput(input.get(index));
            }
            run(prgs);
            return prgs.get(4).readOutput();
        });

        System.out.println("Part1: " + integerStream.max(Integer::compareTo).get());
    }

    static void part2(ArrayList<Integer> instructions) {
        LinkedList<ArrayList<Integer>> inputs = generateCombinations(5, 9);

        Stream<Integer> integerStream = inputs.parallelStream().map(input -> {
            ArrayList<IntProgram> prgs = resetPrograms(instructions);
            for (int index = 0; index < prgs.size(); index++) {
                prgs.get(index).addInput(input.get(index));
            }
            run(prgs);
            return prgs.get(4).readOutput();
        });

        System.out.println(integerStream.max(Integer::compareTo).get());

    }

    public static void run(ArrayList<IntProgram> prgs) {
        try {
            prgs.get(0).addInput(0);
            boolean running = true;
            IntProgram active;
            while (running) {
                active = prgs.get(0);
                for (int i = 1; i < prgs.size(); i++) {
                    active.run();
                    int output = active.readOutput();
                    active = prgs.get(i);
                    active.addInput(output);
                }
                running = active.run() == IntProgram.Code.PAUS;
                if (running) {
                    prgs.get(0).addInput(active.readOutput());
                }
            }
        } catch (IntProgram.ExecutionException | IntProgram.MissingInput | IntProgram.InvalidMode e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

    }

    public static ArrayList<IntProgram> resetPrograms(ArrayList<Integer> instructions) {
        ArrayList<IntProgram> prgs = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            IntProgram prg =new IntProgram();
            prg.setInstructions(new ArrayList<>(instructions));
            prgs.add(prg);
        }
        return prgs;
    }
    
    public static LinkedList<ArrayList<Integer>> generateCombinations(int from, int to) {
        LinkedList<ArrayList<Integer>> combinations = new LinkedList<>();

        if (to == from) {
            ArrayList<Integer> tmp = new ArrayList<>();
            tmp.add(from);
            combinations.add(tmp);
            return combinations;
        }

        for (ArrayList<Integer> combination : generateCombinations(from, to-1) ) {
            for (int index = 0; index <= to-from; index++) {
                ArrayList<Integer> tmp = new ArrayList<>(combination);
                tmp.add(index, to);
                combinations.add(tmp);
            }
        }

        return combinations;
    }

    public static void main(String[] args) {
        String file = "input/day7.txt";

        ArrayList<Integer> instructions = Input.getInputListInt(file);

        part1(instructions);
        part2(instructions);


    }
}