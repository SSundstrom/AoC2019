package Day12;

import Utils.Axis;
import Utils.Input;
import Utils.Pos3;
import Utils.Tuple;

import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day12 {

    public static Pos3 parseInput(String input) {
        String[] strs = input.split(",");
        int x = Integer.parseInt(strs[0].substring(3));
        int y = Integer.parseInt(strs[1].substring(3));
        int z = Integer.parseInt(strs[2].substring(3, strs[2].length() - 1));
        return new Pos3(x,y,z);
    }

    private static State<Tuple<Integer>> getState(Axis axis, ArrayList<Moon> moons) {
        return new State<>(
                moons.get(0).getThings(axis),
                moons.get(1).getThings(axis),
                moons.get(2).getThings(axis),
                moons.get(3).getThings(axis)
        );
    }

    public static BigInteger findLoop(ArrayList<Moon> moons) {
        BigInteger loopX = BigInteger.ZERO;
        BigInteger loopY = BigInteger.ZERO;
        BigInteger loopZ = BigInteger.ZERO;

        HashMap<State<Tuple<Integer>>, BigInteger> zState = new HashMap<>();
        HashMap<State<Tuple<Integer>>, BigInteger> yState = new HashMap<>();
        HashMap<State<Tuple<Integer>>, BigInteger> xState = new HashMap<>();

        BigInteger i = BigInteger.ZERO;
        while (loopX.multiply(loopY).multiply(loopZ).equals(BigInteger.ZERO)) {
            step(moons);

            State<Tuple<Integer>> z = getState(Axis.Z, moons);
            State<Tuple<Integer>> x = getState(Axis.X, moons);
            State<Tuple<Integer>> y = getState(Axis.Y, moons);

            if (zState.containsKey(z) && loopZ.equals(BigInteger.ZERO)) {
                loopZ = i.subtract(zState.get(z));
            }
            if (yState.containsKey(y) && loopY.equals(BigInteger.ZERO)) {
                loopY = i.subtract(yState.get(y));
            }
            if (xState.containsKey(x) && loopX.equals(BigInteger.ZERO)) {
                loopX = i.subtract(xState.get(x));
            }

            zState.put(z, i);
            xState.put(x, i);
            yState.put(y, i);

            i = i.add(BigInteger.ONE);
        }

        BigInteger gcdZX = loopZ.gcd(loopX);
        BigInteger loopXZ = loopX.multiply(loopZ).divide(gcdZX);
        BigInteger gcdXYZ = loopY.gcd(loopXZ);

        return loopY.multiply(loopXZ).divide(gcdXYZ);
    }

    public static void step(ArrayList<Moon> moons) {
        moons.forEach(moon -> moon.updateVelocity(moons));
        moons.forEach(Moon::move);
    }

    public static int part1(Stream<String> input, int steps) {

        ArrayList<Moon> moons = input
                .map(Day12::parseInput)
                .map(Moon::new)
                .collect(Collectors.toCollection(ArrayList::new));

        for (int i = 0; i < steps; i++) {
            step(moons);
        }

        int sum = 0;
        for (Moon moon : moons) {
            sum += moon.getKineticEnergy() * moon.getPotentialEnergy();
        }

        return sum;
    }

    public static BigInteger part2(Stream<String> input) {

        ArrayList<Moon> moons = input
                .map(Day12::parseInput)
                .map(Moon::new)
                .collect(Collectors.toCollection(ArrayList::new));

        return findLoop(moons);
    }

    public static void main(String[] args) {
        String file = "input/day12.txt";

        Stream<String> moons = Input.getInputAsStream(file);

        System.out.println("Part1: " + part1(moons, 1000));
        moons = Input.getInputAsStream(file);
        System.out.println("Part2: " + part2(moons));
    }
}

