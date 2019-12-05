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

    public static void run(String filename) {
        String fileName = "input/" + filename + ".txt";

        // This will reference one line at a time
        ArrayList<Integer> input = null;

        try {
            input = Input.getInputListInt(fileName);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.out.println(input);

        IntProgram prg = new IntProgram(input);

        prg.addInput(5);

        try {
            prg.run();
        } catch (IntProgram.ExecutionException | IntProgram.MissingInput | IntProgram.InvalidMode e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

    }

    public static void main(String[] args) {
        for (String file  : args) {
            System.out.println(" === " + file + " === " );
            run(file.toLowerCase());
        }
    }
}