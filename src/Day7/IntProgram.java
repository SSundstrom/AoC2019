package Day7;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;

public class IntProgram {

    enum Code {
        PAUS, DONE
    }

    ArrayList<Integer> instructions;
    private int index;
    Queue<Integer> input;
    Queue<Integer> output;

    public IntProgram() {
        this.instructions = new ArrayList<>();
        input = new LinkedList<>();
        output = new LinkedList<>();
        index = 0;
    }

    public void setInstructions(ArrayList<Integer> instructions) {
        this.instructions = instructions;
    }

    public void addInput(int in) {
        input.add(in);
    }

    public void setNoun(int noun) {
        this.instructions.set(1, noun);
    }

    public void setVerb(int verb) {
        this.instructions.set(2, verb);
    }

    public Queue<Integer> readAllOutput() {
        return output;
    }

    public Integer readOutput() {
        return output.poll();
    }

    Code run() {
        boolean running = true;
        Code exitCode = null;

        while (running) {
            int instruction = getInstruction();
            int optCode = instruction % 100;

            int[] modes = getModes(instruction);

            switch (optCode) {
                case 1:
                    opt1(modes);
                    break;
                case 2:
                    opt2(modes);
                    break;
                case 3:
                    try {
                        opt3();
                    } catch (MissingInput ex) {
                        running = false;
                        exitCode = Code.PAUS;
                        index--;
                    }
                    break;
                case 4:
                    opt4(modes);
                    break;
                case 5:
                    opt5(modes);
                    break;
                case 6:
                    opt6(modes);
                    break;
                case 7:
                    opt7(modes);
                    break;
                case 8:
                    opt8(modes);
                    break;
                case 99:
                    exitCode = Code.DONE;
                    running = false;
                    break;
                default:
                    throw new ExecutionException(optCode);
            }
        }
        return exitCode;
    }

    int readOp() {
        return index++;
    }

    int getInstruction() {
        return instructions.get(readOp());
    }

    int readFrom(int mode, int paramIndex) {
         switch (mode) {
             case 0: return instructions.get(instructions.get(paramIndex));
             case 1: return instructions.get(paramIndex);
             default:
                 throw new InvalidMode();
        }
    }

    void putAt(int value, int paramIndex) {
        instructions.set(instructions.get(paramIndex), value);
    }

    int[] getModes(int modes) {
        return new int[] {getDigit(2, modes), getDigit(3, modes), getDigit(4, modes)};
    }

    private int getDigit(int index, int number) {
        while (index > 0) {
            number = number / 10;
            index--;
        }
        return number % 10;
    }

    void opt1(int[] modes) {
        int newVal = readFrom(modes[0],readOp()) + readFrom(modes[1], readOp());
        putAt(newVal, readOp());
    }

    void opt2(int[] modes) {
        int val1 = readFrom(modes[0], readOp());
        int val2 = readFrom(modes[1], readOp());
        int newVal = val1 *  val2;
        putAt(newVal, readOp());
    }

    void opt3() throws MissingInput {
        int in;
        try {
            in = input.poll();
        } catch (NullPointerException exception) {
            throw new MissingInput();
        }
        putAt(in, readOp());
    }

    void opt4(int[] modes) {
        int val = readFrom(modes[0], readOp());
        output.add(val);
    }

    void opt5(int[] modes) {
        int val = readFrom(modes[0], readOp());
        int jmpIndex = readFrom(modes[1], readOp());
        if (val != 0) {
            index = jmpIndex;
        }
    }

    void opt6(int[] modes) {
        int val = readFrom(modes[0], readOp());
        int jmpIndex = readFrom(modes[1], readOp());
        if (val == 0) {
            index = jmpIndex;
        }
    }

    void opt7(int[] modes) {
        int v1 = readFrom(modes[0], readOp());
        int v2 = readFrom(modes[1], readOp());
        int res = v1 < v2 ? 1 : 0;
        putAt(res, readOp());
    }

    void opt8(int[] modes) {
        int v1 = readFrom(modes[0], readOp());
        int v2 = readFrom(modes[1], readOp());
        int res = v1 == v2 ? 1 : 0;
        putAt(res, readOp());
    }

    Code opt99() {
        return Code.DONE;
    }

    static class ExecutionException extends RuntimeException {
        public ExecutionException(int code) {
            super("Optcode " + code  + " faulty!");
        }
    }

    static class MissingInput extends RuntimeException {
    }

    static class InvalidMode extends RuntimeException {

    }



}
