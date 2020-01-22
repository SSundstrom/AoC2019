package Day19;

import Utils.BigIntProgram;
import Utils.Direction;
import Utils.Input;
import Utils.Pos;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;

public class Day19 {

    public static void printBeam(Pos topLeft, Pos bottomRight, HashSet<Pos> beam) {
        StringBuilder sb = new StringBuilder();
        StringBuilder[] lines = new StringBuilder[]{ new StringBuilder("   "), new StringBuilder("   "), new StringBuilder("   ")};
        for (int x = topLeft.getX(); x < bottomRight.getX(); x++) {
            lines[0].append((x/100) % 10);
            lines[1].append((x/10) % 10);
            lines[2].append(x % 10);
        }
        sb.append(lines[0].toString());
        sb.append('\n');
        sb.append(lines[1].toString());
        sb.append('\n');
        sb.append(lines[2].toString());
        sb.append('\n');
        for (int y = topLeft.getY(); y < bottomRight.getY(); y++) {
            sb.append(String.format("%3d", y));
            for (int x = topLeft.getX(); x < bottomRight.getX(); x++) {
                char val = beam.contains(new Pos(x, y)) ? '#' : ' ';
                sb.append(val);
            }
            sb.append('\n');
        }
        System.out.println(sb.toString());
    }

    public static void mapBeam(Pos topLeft, Pos bottomRight, HashSet<Pos> beam, ArrayList<BigInteger> input) {
        for (int y = topLeft.getY(); y < bottomRight.getY(); y++) {
            for (int x = topLeft.getX(); x < bottomRight.getX(); x++) {
                Pos pos = new Pos(x, y);
                if (!beam.contains(pos)) {
                    int val = BigIntProgram.runAndReturnWithInput(new int[] {x, y}, 1000, input).intValue();
                    if (val == 1) beam.add(pos);
                }
            }
        }
    }

    public static boolean mapBeam(Pos pos, HashSet<Pos> beam, ArrayList<BigInteger> input) {
        if (!beam.contains(pos)) {
            if (pos.getY() < 0 || pos.getX() < 0) return false;
            int val = BigIntProgram.runAndReturnWithInput(new int[] {pos.getX(), pos.getY()}, 10000, input).intValue();
            if (val == 1) {
                beam.add(pos);
                return true;
            }
            return false;
        }
        return true;
    }

    static int part1(HashSet<Pos> beam) {
        return beam.size();
    }

    static Pos getBottomLeft(Pos topRight, int size) {
        return topRight.invMove(Direction.S, size-1).invMove(Direction.W, size-1);
    }

    static int part2(HashSet<Pos> beam, ArrayList<BigInteger> instructions) {
        Pos topRight = new Pos(4, 3);
        int shipSize = 100;

        while (!mapBeam(getBottomLeft(topRight, shipSize), beam, instructions)) {
            topRight = topRight.invMove(Direction.E);
            while (!mapBeam(topRight, beam, instructions)) {
                topRight = topRight.invMove(Direction.S);
            }
        }


        Pos topLeft = topRight.invMove(Direction.W, shipSize-1);
        return topLeft.getX() * 10000 + topLeft.getY();
    }

    public static void main(String[] args) {
        String filename = "input/day19.txt";
        ArrayList<BigInteger> input = Input.getInputListBigInt(filename);
        BigIntProgram prg = new BigIntProgram();
        prg.setInstructions(input);
        HashSet<Pos> map = new HashSet<>();
        mapBeam(Pos.ORIGO, new Pos(50, 50), map, input);

        System.out.println("Part1: " + part1(map));
        System.out.println("Part2: " + part2(map, input));

    }
}
