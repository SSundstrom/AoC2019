package Day18;

import Utils.Input;

public class Day18 {

    static Map map;

    public static Path run(String input) {
        map = new Map(input);
        Explorer explorer = new Explorer(map);

        while (explorer.exploring()) {
            explorer.goToNextKey();
            for (Path path : explorer.getPaths()) {
                System.out.println(path);
            }
            System.out.println("========================");
        }


        return explorer.getPath();

    }

    public static void main(String[] args) {
        String filename = "input/day18.txt";
        String input = Input.getInputAsString(filename);
        Path path = run(input);
        System.out.println("Part1: " + path.distance);
    }

}
