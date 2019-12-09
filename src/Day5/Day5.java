package Day5;

import Utils.Input;
import Utils.IntProgram;

import java.text.ParseException;
import java.util.ArrayList;

public class Day5 {

    public static int part1(String filename){
        IntProgram prg = new IntProgram();
        prg.addInput(1);
        run(filename.toLowerCase(), prg);
        return prg.readAllOutput().stream().max(Integer::compareTo).get();
    }

    public static int part2(String filename) {
        IntProgram prg = new IntProgram();
        prg.addInput(5);
        run(filename.toLowerCase(), prg);
        return prg.readOutput();
    }

    public static void run(String filename, IntProgram prg) {
        String fileName = "input/" + filename + ".txt";

        // This will reference one line at a time
        ArrayList<Integer> instructions = null;

        instructions = Input.getInputListInt(fileName);

        prg.setInstructions(instructions);

        try {
            prg.run();
        } catch (IntProgram.ExecutionException | IntProgram.MissingInput | IntProgram.InvalidMode e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

    }

    public static void main(String[] args) {
        String file = "Day5";

        System.out.println("Part1: " + part1(file));
        System.out.println("Part2: " + part2(file));

    }
}