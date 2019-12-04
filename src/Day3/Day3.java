package Day3;

import Utils.Direction;
import Utils.Input;

import java.text.ParseException;
import java.util.stream.Stream;

public class Day3 {

    static Wire parse(String line) {
        String[] instructions = line.split(",");
        Wire wire = new Wire();
        for (String instruction : instructions ) {
            Direction d = null;
            try {
                d = Direction.parseChar(instruction.charAt(0));
            } catch (ParseException e) {
                System.out.println(e.getMessage());
                System.exit(1);
            }
            int distance = Integer.parseInt(instruction.substring(1));
            wire.addStep(d, distance);
        }
        return wire;
    }

    public static void run(String filename){
        String fileName = "input/" + filename + ".txt";

        // This will reference one line at a time
        Stream<String> stream = Input.getInputAsStream(fileName);
        WireMap map = new WireMap();
        Stream<Wire> wires = stream.map(Day3::parse);

        wires.forEach(map::placeWire);

        System.out.println("Part1: " + map.getClosestCollision());
        System.out.println("Part2: " + map.getShortestCollision());

    }

    public static void main(String[] args) {
        for (String file  : args) {
            System.out.println(" === " + file + " === " );
            run(args[0].toLowerCase());
        }
    }
}

