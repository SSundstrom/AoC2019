package Day10;

import Utils.Input;
import Utils.Pos;

import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Day10 {

    static int part1(Stream<String> input) {
        HashSet<Pos> map = parseInput(input);
        HashMap<Pos, HashSet<Pos>> dirs = directions(map);
        Pos center = mostVisible(dirs);
        return dirs.get(center).size();
    }

    private static HashMap<Pos, HashSet<Pos>> directions(HashSet<Pos> map) {
        HashMap<Pos, HashSet<Pos>> res = new HashMap<>();
        map.forEach(pos -> {
            HashSet<Pos> visible = new HashSet<>();
            map.forEach(p2 -> visible.add(pos.directionTo(p2)));
            visible.remove(Pos.ORIGO);
            res.put(pos, visible);
        });
        return res;
    }

    private static Pos mostVisible(HashMap<Pos, HashSet<Pos>> input) {
        int visible = 0;
        Pos center = null;
        for (Map.Entry<Pos, HashSet<Pos>> posArrayListEntry : input.entrySet()) {
            int curr = posArrayListEntry.getValue().size();
            if (visible < curr) {
                visible = curr;
                center = posArrayListEntry.getKey();
            }
        }
        return center;
    }

    static int part2(Stream<String> input) {
        HashSet<Pos> map = parseInput(input);
        HashMap<Pos, HashSet<Pos>> dirs = directions(map);
        Pos home = mostVisible(dirs);
        ArrayList<Pos> directions = new ArrayList<>(dirs.get(home));
        directions.sort(Pos.getAngleComparator(Pos.NORTH));
        Pos direction = directions.get(199);
        Pos possiblePlanet = home.add(direction);
        while (!map.contains(possiblePlanet)) {
            possiblePlanet = possiblePlanet.add(direction);
        }
        return possiblePlanet.getAsAOCOutput();
    }

    public static HashSet<Pos> parseInput(Stream<String> input) {
        ArrayList<IntStream> rows = input
                .map(String::chars)
                .collect(Collectors.toCollection(ArrayList::new));
        HashSet<Pos> meteroits = new HashSet<>();
        rows.forEach(row -> {
            int y = rows.indexOf(row);
            Iterator<Integer> iter = row.iterator();
            int x = 0;
            while (iter.hasNext()) {
                if (iter.next() == 35) {
                    meteroits.add(new Pos(x, y));
                }
                x++;
            }
        });
        return meteroits;
    }

    public static void main(String[] args) {
        String file = "input/day10.txt";
        Stream<String> input = Input.getInputAsStream(file);
        System.out.println("Part1: " + part1(input));
        input = Input.getInputAsStream(file);
        System.out.println("Part2: "  + part2(input));
    }
}