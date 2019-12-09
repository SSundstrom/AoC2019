package Utils;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.zip.CheckedOutputStream;

public class BigIntProgram {

    ArrayList<BigInteger> instructions;
    Queue<BigInteger> input;
    Queue<BigInteger> output;
    private int index;
    private int relativeBase;

    public BigIntProgram() {
        this.instructions = new ArrayList<>();
        input = new LinkedList<>();
        output = new LinkedList<>();
        index = 0;
        relativeBase = 0;
    }

    public static BigInteger runAndReturnWithInput(int input, int maxProgramSize, ArrayList<BigInteger> instructions){
        BigIntProgram prg = new BigIntProgram();
        prg.setInstructions(new ArrayList<>(instructions), maxProgramSize);
        prg.addInput(input);
        prg.run();
        return prg.readOutput();
    }

    public int getRelativeBase() {
        return relativeBase;
    }

    public void setRelativeBase(int relativeBase) {
        this.relativeBase = relativeBase;
    }

    public void setInstructions(ArrayList<BigInteger> instructions, int programSize) {
        this.instructions = new ArrayList<>(programSize);
        this.instructions.addAll(instructions);
        this.instructions.addAll(Stream.iterate(BigInteger.ZERO, p -> p).limit(programSize - instructions.size()).collect(Collectors.toList()));
    }

    public void addInput(int in) {
        input.add(BigInteger.valueOf(in));
    }

    public void setNoun(int noun) {
        this.instructions.set(1, BigInteger.valueOf(noun));
    }

    public void setVerb(int verb) {
        this.instructions.set(2, BigInteger.valueOf(verb));
    }

    public Queue<BigInteger> readAllOutput() {
        return output;
    }

    public BigInteger readOutput() {
        return output.poll();
    }

    public Code run() {
        boolean running = true;
        Code exitCode = null;

        while (running) {
            int instruction = getInstruction().intValue();
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
                        opt3(modes);
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
                case 9:
                    opt9(modes);
                    break;
                case 98:
                    exitCode = Code.PAUS;
                    running = false;
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

    BigInteger getInstruction() {
        return instructions.get(readOp());
    }

    BigInteger readFrom(int mode, int paramIndex) {
        switch (mode) {
            case 0:
                return instructions.get(instructions.get(paramIndex).intValue());
            case 1:
                return instructions.get(paramIndex);
            case 2:
                return instructions.get(instructions.get(paramIndex).intValue() + relativeBase);
            default:
                throw new InvalidMode();
        }
    }

    void putAt(int mode, BigInteger value, int paramIndex) {
        switch (mode) {
            case 0:
                instructions.set(instructions.get(paramIndex).intValue(), value);;
                break;
            case 2:
                instructions.set(instructions.get(paramIndex).intValue() + relativeBase, value);
                break;
            default:
                throw new InvalidMode();
        }
    }

    int[] getModes(int modes) {
        return new int[]{getDigit(2, modes), getDigit(3, modes), getDigit(4, modes)};
    }

    private int getDigit(int index, int number) {
        while (index > 0) {
            number = number / 10;
            index--;
        }
        return number % 10;
    }

    void opt1(int[] modes) {
        BigInteger newVal = readFrom(modes[0], readOp()).add(readFrom(modes[1], readOp()));
        putAt(modes[2], newVal, readOp());
    }

    void opt2(int[] modes) {
        BigInteger val1 = readFrom(modes[0], readOp());
        BigInteger val2 = readFrom(modes[1], readOp());
        BigInteger newVal = val1.multiply(val2);
        putAt(modes[2], newVal, readOp());
    }

    void opt3(int[] modes) throws MissingInput {
        BigInteger in;
        try {
            in = input.poll();
        } catch (NullPointerException exception) {
            throw new MissingInput();
        }
        putAt(modes[0], in, readOp());
    }

    void opt4(int[] modes) {
        BigInteger val = readFrom(modes[0], readOp());
        output.add(val);
    }

    void opt5(int[] modes) {
        int val = readFrom(modes[0], readOp()).intValue();
        int jmpIndex = readFrom(modes[1], readOp()).intValue();
        if (val != 0) {
            index = jmpIndex;
        }
    }

    void opt6(int[] modes) {
        int val = readFrom(modes[0], readOp()).intValue();
        int jmpIndex = readFrom(modes[1], readOp()).intValue();
        if (val == 0) {
            index = jmpIndex;
        }
    }

    void opt7(int[] modes) {
        BigInteger v1 = readFrom(modes[0], readOp());
        BigInteger v2 = readFrom(modes[1], readOp());
        int res = v1.compareTo(v2) < 0 ? 1 : 0;
        putAt(modes[2], BigInteger.valueOf(res), readOp());
    }

    void opt8(int[] modes) {
        BigInteger v1 = readFrom(modes[0], readOp());
        BigInteger v2 = readFrom(modes[1], readOp());
        BigInteger res = v1.equals(v2) ? BigInteger.ONE : BigInteger.ZERO;
        putAt(modes[2], res, readOp());
    }

    void opt9(int[] modes) {
        BigInteger val = readFrom(modes[0], readOp());
        relativeBase += val.intValue();
    }

    Code opt99() {
        return Code.DONE;
    }

    public static class ExecutionException extends RuntimeException {
        public ExecutionException(int code) {
            super("Optcode " + code + " faulty!");
        }
    }

    public static class MissingInput extends RuntimeException {
    }

    public static class InvalidMode extends RuntimeException {

    }

}

