import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Objects;

public class Day3 {

    static int part1(WireMap map) {
        return map.getClosestCollision();
    }

    static int part2(WireMap map){
        return map.getCombinedSteps();
    }


    public static void main(String[] args) {
        String fileName = "input/day3.txt";

        // This will reference one line at a time
        String line;

        LinkedList<Path> paths = new LinkedList<>();


        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader =
                    new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader =
                    new BufferedReader(fileReader);


            while((line = bufferedReader.readLine()) != null) {
                String[] dirs = line.split(",");

                Path path = new Path();

                for (String instruction : dirs ) {

                    int distance = Integer.parseInt(instruction.substring(1));
                    Dir direction = Dir.parseChar(instruction.charAt(0));

                    path.addStep(direction, distance);

                }

                paths.add(path);
            }

            // Always close files.
            bufferedReader.close();


        }
        catch(FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file '" +
                            fileName + "'");
        }
        catch(IOException ex) {
            System.out.println(
                    "Error reading file '"
                            + fileName + "'");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        WireMap map = new WireMap();
        for (Path path : paths) {
            for (Step s : path.getSteps()){
                map.move(s);
            }
            map.newWire();
        }

        System.out.println("Part1: " + part1(map));
        System.out.println("Part2: " + part2(map));

    }


}

class Path {

    LinkedList<Step> steps;
    public Path() {
        steps = new LinkedList<>();
    }

    public void addStep(Dir dir, int length) {
        steps.add(new Step(dir, length));
    }

    public LinkedList<Step> getSteps() {
        return steps;
    }
}

class Step {
    Dir dir;
    int length;

    public Step(Dir dir, int length) {
        this.dir = dir;
        this.length = length;
    }

    public Dir getDir() {
        return dir;
    }

    public int getLength() {
        return length;
    }

    @Override
    public String toString() {
        return "Step{" +
                "dir=" + dir +
                ", dist=" + length +
                '}';
    }
}

enum Dir {
    N, W, S, E;

    static Dir parseChar(char c) throws ParseException {
        switch (c) {
            case 'U' :
                return N;
            case 'R' :
                return E;
            case 'D' :
                return S;
            case 'L' :
                return W;
            default:
                throw new ParseException("" + c, 0);
        }

    }
}

class Pos {
    int x, y;

    public Pos(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int manhattanDist(Pos p2) {
        return Math.abs(x - p2.x) + Math.abs(y - p2.y);
    }

    Pos move(Dir dir) throws Exception {
        switch (dir) {
            case E:
                return new Pos(x+1, y);
            case N:
                return new Pos(x, y+1);
            case S:
                return new Pos(x, y-1);
            case W:
                return new Pos(x-1, y);
        }
        throw new Exception("This should not happen..");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pos pos = (Pos) o;
        return x == pos.x &&
                y == pos.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Pos{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}

class WireMap {

    HashMap<Pos, Integer> coords;
    HashMap<Pos, Integer> newCoords;
    HashMap<Pos, Integer> collisions;
    Pos pos;
    int length;
    int closestCollision;
    static final Pos origin = new Pos(0, 0);

    public WireMap() {
        coords = new HashMap<>();
        newCoords = new HashMap<>();
        pos = new Pos(0, 0);
        length = 0;
        collisions = new HashMap<>();
        closestCollision = Integer.MAX_VALUE;
    }

    void newWire() {
        newCoords.forEach(
                (k, v) -> coords.compute(k, (k2, v2) -> v2 == null ? v : v + v2)
        );
        newCoords = new HashMap<>();
        length = 0;
        pos = new Pos(0,0);
    }

    void move(Step step) {
        for (int i = 0; i < step.getLength(); i++) {
            length++;
            try {
                pos = pos.move(step.dir);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (coords.containsKey(pos)) {
                int dist = pos.manhattanDist(origin);
                closestCollision = Math.min(dist, closestCollision);
                collisions.putIfAbsent(pos, coords.get(pos) + length);
            }
            if (!newCoords.containsKey(pos)) {
                newCoords.put(pos, length);
            }
        }

    }

    public int getClosestCollision() {
        return closestCollision;
    }

    public int getCombinedSteps() {
        int min = Integer.MAX_VALUE;
        for (int v : collisions.values()) {
            min = Math.min(v, min);
        }
        return min;
    }
}