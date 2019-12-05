package Day5;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class IntProgram {

    ArrayList<Integer> instructions;
    private int index;
    Queue<Integer> input;

    public IntProgram(ArrayList<Integer> instructions) {
        this.instructions = new ArrayList<>(instructions);
        input = new LinkedList<>();
        index = 0;
    }

    public void addInput(int in) {
        input.add(in);
    }

    public void setNoun(int noun){
        this.instructions.set(1, noun);
    }

    public void setVerb(int verb){
        this.instructions.set(2, verb);
    }

    void run() {
        boolean running = true;
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
                    opt3(modes);
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
                    running = false;
                    break;
                default:
                    throw new ExecutionException(optCode);
            }
        }
    }

    int readOp() {
        return index++;
    }

    int getInstruction() {
        return instructions.get(readOp());
    }

    int getValueFrom(int mode, int paramIndex) {
         switch (mode) {
             case 0: return instructions.get(instructions.get(paramIndex));
             case 1: return instructions.get(paramIndex);
             default:
                 throw new InvalidMode();
        }
    }

    int[] getModes(int modes) throws InvalidMode {
        int[] res = new int[] {getDigit(2, modes), getDigit(3, modes), getDigit(4, modes)};
        return res;
    }

    private int getDigit(int index, int number) {
        while (index > 0) {
            number = number / 10;
            index--;
        }
        return number % 10;
    }

    void opt1(int[] modes) {
        int newVal = getValueFrom(modes[0],readOp()) + getValueFrom(modes[1], readOp());
        int insertPos = getValueFrom(1, readOp());
        instructions.set(insertPos, newVal);
    }

    void opt2(int[] modes) {
        int val1 = getValueFrom(modes[0], readOp());
        int val2 = getValueFrom(modes[1], readOp());
        int newVal = val1 *  val2;
        int insertPos = getValueFrom(1, readOp());
        instructions.set(insertPos, newVal);
    }

    void opt3(int[] modes) throws MissingInput {
        int pos = getValueFrom(1, readOp());
        int in;
        try {
            in = input.poll();
        } catch (NullPointerException exception) {
            throw new MissingInput();
        }
        instructions.set(pos, in);
    }

    void opt4(int[] modes) {
        int val = getValueFrom(modes[0], readOp());
        System.out.println(val);
    }

    void opt5(int[] modes) {
        int val = getValueFrom(modes[0], readOp());
        int jmpIndex = getValueFrom(modes[1], readOp());
        if (val != 0) {
            index = jmpIndex;
        }
    }

    void opt6(int[] modes) {
        int val = getValueFrom(modes[0], readOp());
        int jmpIndex = getValueFrom(modes[1], readOp());
        if (val == 0) {
            index = jmpIndex;
        }
    }

    void opt7(int[] modes) {
        int v1 = getValueFrom(modes[0], readOp());
        int v2 = getValueFrom(modes[1], readOp());
        int placeAt = getValueFrom(1, readOp());
        int res = v1 < v2 ? 1 : 0;
        instructions.set(placeAt, res);
    }

    void opt8(int[] modes) {
        int v1 = getValueFrom(modes[0], readOp());
        int v2 = getValueFrom(modes[1], readOp());
        int placeAt = getValueFrom(1, readOp());
        int res = v1 == v2 ? 1 : 0;
        instructions.set(placeAt, res);
    }

    int opt99() {
        return instructions.get(0);
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
