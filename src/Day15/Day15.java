package Day15;

import Utils.BigIntProgram;

import Utils.Direction;
import Utils.Input;
import Utils.Pos;

import java.math.BigInteger;
import java.util.ArrayList;


public class Day15 {


    public static int part1(Robot robot) {
        return robot.findPath(Pos.ORIGO, robot.getOxygenPos(), Direction.getAllDirections());
    }

    public static int part2(Robot robot) {
        return robot.fillFrom(robot.getOxygenPos());
    }

    public static void main(String[] args) {
        String filename = "input/day15.txt";
        ArrayList<BigInteger> input = Input.getInputListBigInt(filename);

        BigIntProgram prg = new BigIntProgram();
        prg.setInstructions(input, 10000);
        Robot robot = new Robot(prg);
        robot.run();

        System.out.println("Part1: " + part1(robot));
        System.out.println("Part2: " + part2(robot));

    }
}
