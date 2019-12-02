import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day2 {

    private static class Program {

        ArrayList<Integer> instructions;
        private int index;

        public Program (ArrayList<Integer> instructions, int noun, int verb) {
            this.instructions = new ArrayList<>(instructions);
            this.instructions.set(1, noun);
            this.instructions.set(2, verb);
            index = 0;
        }

        int getInstruction() {
            return instructions.get(index);
        }

        void nextInstruction() {
            index += 4;
        }

        int getValueFrom(int index){
            return instructions.get(instructions.get(index));
        }

        void opt1() {
            int newVal =  getValueFrom(index + 1) + getValueFrom(index + 2);
            int insertPos = instructions.get(index + 3);
            instructions.set(insertPos, newVal);
        }

        void opt2() {
            int newVal = getValueFrom(index + 1) * getValueFrom(index + 2);
            int insertPos = instructions.get(index + 3);
            instructions.set(insertPos, newVal);
        }

        int opt99() {
            return instructions.get(0);
        }

        int getNoun() {
            return instructions.get(1);
        }

        int getVerb() {
            return instructions.get(2);
        }

    }

    static int part2(ArrayList<Integer> codes) throws NoSolutionException {

        Program prg;
        for (int noun = 0; noun < 99; noun++) {
            for (int verb = 0; verb < 99; verb++) {
                prg = new Program(codes, noun, verb);
                try {
                    if (part1(prg) == 19690720) {
                        System.out.println("Noun: " + noun);
                        System.out.println("Verb: " + verb);
                        return 100 * noun + verb;
                    }
                } catch (NoSolutionException exp) {
                    System.out.println(exp.getMessage());
                }
            }
        }
        throw new NoSolutionException();
    }

    static int part1(Program prg) throws NoSolutionException {
        boolean running = true;
        while (running) {
            switch (prg.getInstruction()) {
                case 1:
                    prg.opt1();
                    break;
                case 2:
                    prg.opt2();
                    break;
                case 99:
                    running = false;
                    break;
                default:
                    throw new NoSolutionException();
            }
            prg.nextInstruction();
        }
        return prg.opt99();
    }

    public static void main(String[] args) {
        String fileName = "input/day2.txt";

        // This will reference one line at a time
        String line;
        ArrayList<Integer> codes = new ArrayList<>();

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader =
                    new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader =
                    new BufferedReader(fileReader);


            while((line = bufferedReader.readLine()) != null) {
                codes.add(Integer.parseInt(line));
            }

            // Always close files.
            bufferedReader.close();


        }
        catch(FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file '" +
                            fileName + "'");
        }
        catch(IOException ex) {
            System.out.println(
                    "Error reading file '"
                            + fileName + "'");
        }

        Program prg = new Program(codes, 12, 2);
        try {
            System.out.println("Part 1: " + part1(prg));
            System.out.println("Part 2: " + part2(codes));
        } catch (NoSolutionException e) {
            System.out.println(e.getMessage());
        }


    }

    static class NoSolutionException extends Exception {

    }
}
