package Day9;

import Utils.BigIntProgram;
import Utils.Input;

import java.math.BigInteger;
import java.util.ArrayList;

public class Day9 {

    static BigInteger part1(ArrayList<BigInteger> instructions) {
        return BigIntProgram.runAndReturnWithInput(1, 5000, instructions);
    }

    static BigInteger part2(ArrayList<BigInteger> instructions) {
        return BigIntProgram.runAndReturnWithInput(2, 5000, instructions);
    }


    public static void main(String[] args) {
        String file = "input/day9.txt";

        ArrayList<BigInteger> instructions = Input.getInputListBigInt(file);

        System.out.println("Part1: " + part1(instructions));
        System.out.println("Part2: "  + part2(instructions));

    }
}