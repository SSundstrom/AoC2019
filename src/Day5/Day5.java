package Day5;

import Utils.Input;

import java.text.ParseException;
import java.util.ArrayList;

public class Day5 {

    static int part1() {
        return -1;
    }

    static int part2(){
        return -1;
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

        IntProgram prg = new IntProgram();
        prg.addInput(1);
        run(file.toLowerCase(), prg);
        System.out.print("Part1: ");
        prg.readAllOutput().stream().filter(integer -> integer != 0).forEach(System.out::print);
        System.out.println();

        prg = new IntProgram();
        prg.addInput(5);
        run(file.toLowerCase(), prg);
        System.out.println("Part2: " + prg.readOutput());
    }
}