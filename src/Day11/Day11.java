package Day11;

import Utils.BigIntProgram;
import Utils.Input;
import Utils.Pos;

import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.ArrayList;

public class Day11 {

    static int part1(ArrayList<BigInteger> instructions) {
        BigIntProgram prg = new BigIntProgram();
        prg.setInstructions(instructions, 100000);
        Hull hull = new Hull();
        EPHRobot robot = new EPHRobot(prg, hull);
        robot.run();
        return hull.getPainted().size();
    }

    static void part2(ArrayList<BigInteger> instructions) {
        BigIntProgram prg = new BigIntProgram();
        prg.setInstructions(instructions, 100000);
        Hull hull = new Hull();
        hull.paint(1, Pos.ORIGO);
        EPHRobot robot = new EPHRobot(prg, hull);
        robot.run();
        hull.print();
    }


    public static void main(String[] args) {
        String file = "input/day11.txt";

        ArrayList<BigInteger> instructions = Input.getInputListBigInt(file);

        System.out.println("Part1: " + part1(new ArrayList<>(instructions)));
        System.out.println("Part2: ");
        part2(instructions);

    }
}