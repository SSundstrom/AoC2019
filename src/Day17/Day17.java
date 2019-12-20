package Day17;

import Utils.*;

import java.math.BigInteger;
import java.util.*;

public class Day17 {


    public static int part1(ArrayList<BigInteger> input) {
        BigIntProgram prg = new BigIntProgram();
        prg.setInstructions(input, 10000);
        prg.run();
        HashMap<Pos, Integer> map = makeMap(prg.readAllOutput());
        int sum = 0;
        for (Pos pos : map.keySet()) {
            if (isIntersection(pos, map)) {
                sum += pos.getX() * pos.getY();
            }
        }
        return sum;
    }

    public static BigInteger part2(ArrayList<BigInteger> input) {
        BigIntProgram prg = new BigIntProgram();
        input.set(0, BigInteger.TWO);
        prg.setInstructions(input, 10000);
        int[] prgInput = {
                'A', ',', 'B', ',', 'A', ',', 'B', ',', 'C', ',', 'A', ',', 'B', ',', 'C', ',', 'A', ',', 'C', '\n',
                'R', ',', '6', ',', 'L', ',', '6', ',', 'L', ',', '1', '0', '\n',
                'L', ',', '8', ',', 'L', ',', '6', ',', 'L', ',', '1', '0', ',', 'L', ',', '6', '\n',
                'R', ',', '6', ',', 'L', ',', '8', ',', 'L', ',', '1', '0', ',', 'R', ',', '6', '\n',
                'n', '\n'
        };
        for (int i : prgInput) {
            prg.addInput(i);
        }
        prg.run();
        BigInteger finalOutput = null;
        while (prg.hasOutput()) finalOutput = prg.readOutput();
        return finalOutput;
    }

    public static boolean isIntersection(Pos pos, HashMap<Pos, Integer> board) {
        return board.containsKey(pos)
                && board.containsKey(pos.move(Direction.N))
                && board.containsKey(pos.move(Direction.S))
                && board.containsKey(pos.move(Direction.W))
                && board.containsKey(pos.move(Direction.E));
    }

    public static HashMap<Pos, Integer> makeMap(Queue<BigInteger> input) {
        HashMap<Pos, Integer> map = new HashMap<>();
        int y = 0;
        int x = 0;
        while (!input.isEmpty()) {
            int val = input.poll().intValue();
            if (val == '\n') {
                y++;
                x = 0;
            } else if (val == '#' || val == '^') {
                map.put(new Pos(x,y), val);
                x++;
            } else {
                x++;
            }
        }
        return map;
    }

    public static Pos findStart(HashMap<Pos, Integer> map) {
        for (Map.Entry<Pos, Integer> item : map.entrySet()) {
            if (item.getValue() == '^') return item.getKey();
        }
        throw new RuntimeException("No start position found");
    }

    public static void printStartToEnd(HashMap<Pos, Integer> map) {
        StringBuilder sb = new StringBuilder();
        Pos pos = findStart(map);
        Direction facing = Direction.S;
        boolean running = true;
        while (running) {
            facing = facing.turn(1);
            if (map.containsKey(pos.move(facing))) {
                sb.append("L");
            } else if (map.containsKey(pos.move(facing.opposite()))) {
                facing = facing.opposite();
                sb.append("R");
            } else {
                System.out.println(pos);
                running = false;
            }
            int counter = 0;
            while (map.containsKey(pos.move(facing))) {
                pos = pos.move(facing);
                counter++;
            }
            sb.append(counter);
            sb.append('\n');
        }

        System.out.println(sb.toString());

    }

    public static void main(String[] args) {
        String filename = "input/day17.txt";
        ArrayList<BigInteger> input = Input.getInputListBigInt(filename);

        System.out.println("Part1: " + part1(new ArrayList<>(input)));
        System.out.println("Part2: " + part2(input));

    }
}
