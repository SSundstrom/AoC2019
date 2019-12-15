package Day14;

import Utils.Input;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.stream.Stream;

public class Day14 {

    public static long part1(Stream<String> input) {
        NanoFactory nanoFactory = parseInput(input);
        nanoFactory.addOre(Integer.MAX_VALUE);
        nanoFactory.make("FUEL", 1);
        return nanoFactory.getItem("ORE").getCreated() - nanoFactory.getItem("ORE").getAmountAvailable();
    }

    public static long part2(Stream<String> input) {
        NanoFactory nanoFactory = parseInput(input);
        long ores = Long.parseLong("1000000000000");
        nanoFactory.addOre(ores);
        int size = 1;
        while (nanoFactory.getItem("ORE").getAmountAvailable() > 0) {
            nanoFactory.make("FUEL", 1);
            nanoFactory.getItem("FUEL").useItem(1);
        }
        return nanoFactory.getItem("FUEL").getCreated();
    }

    public static NanoFactory parseInput(Stream<String> input) {
        NanoFactory factory = new NanoFactory();
        input.forEach(factory::addRecipe);
        return factory;
    }

    public static void main(String[] args) {
        String filename = "input/day14.txt";
        Stream<String> input = Input.getInputAsStream(filename);
        System.out.println("Part1: " + part1(input));
        input = Input.getInputAsStream(filename);
        System.out.println("Part2: " + part2(input));
    }
}
