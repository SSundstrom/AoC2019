package Day15;

import Utils.BigIntProgram;
import Utils.Code;
import Utils.Direction;
import Utils.Pos;

import java.util.*;
import java.util.stream.Stream;

public class Robot {

    private static Map<Direction, Integer> dirToCode;
    private Pos pos;
    private Direction facing;
    private BigIntProgram prg;
    private HashMap<Pos, Boolean> walls;
    private HashMap<Pos, Queue<Direction>> untested;
    private Pos oxygenPos;

    public Robot(BigIntProgram prg) {
        this.prg = prg;
        dirToCode = Map.of(
                Direction.N, 1,
                Direction.S, 2,
                Direction.W, 3,
                Direction.E, 4);
        untested = new HashMap<>();
        pos = Pos.ORIGO;
        facing = Direction.N;
        walls = new HashMap<>();
    }

    public Pos getOxygenPos() {
        return oxygenPos;
    }

    public void run() {
        Code exitCode = Code.PAUS;
        boolean oxygenFound = false;
        while (exitCode.equals(Code.PAUS) && !oxygenFound) {
            ai();
            prg.addInput(dirToCode.get(facing));
            exitCode = prg.run();
            int status = prg.readOutput().intValue();
            oxygenFound = handleStatus(status);

        }
        print();
    }

    public int fillFrom(Pos from) {
        Set<Pos> positions = walls.keySet();
        positions.removeIf(pos -> walls.get(pos));
        int steps = positions.parallelStream()
                .map(p -> findPath(from, p, Direction.getAllDirections()))
                .max(Integer::compareTo).get();
        return steps;
    }

    public int findPath(Pos from, Pos to, Stream<Direction> forward) {
        if (from.equals(to)) {
            return 0;
        }
        Stream<Integer> paths = forward
                .filter(dir -> !walls.getOrDefault(from.move(dir), true))
                .map(dir -> 1 + findPath(from.move(dir), to, notBack(dir)));
        Optional<Integer> res = paths.min(Integer::compareTo);
        return res.orElse(Integer.MAX_VALUE/2);
    }

    private void ai(){
        if (!untested.containsKey(pos)) {
            untested.put(pos, getDirections(facing));
        }
        if (untested.get(pos).isEmpty()) {
            untested.put(pos, getDirections(facing));
        }
        facing = untested.get(pos).poll();
    }

    private Stream<Direction> notBack(Direction forward) {
        return Stream.of(forward, forward.turn(1), forward.turn(3));
    }

    private Queue<Direction> getDirections(Direction start) {
        Queue<Direction> res = new LinkedList<>();
        res.add(start);
        res.add(start.turn(3));
        res.add(start.turn(1));
        res.add(start.turn(2));
        return res;
    }



    private boolean handleStatus(int statusCode) {
        switch (statusCode) {
            case 0:
                walls.put(pos.move(facing), true);
                return false;
            case 1:
                pos = pos.move(facing);
                walls.put(pos, false);
                return false;
            case 2:
                pos = pos.move(facing);
                walls.put(pos, false);
                oxygenPos = pos;
                return true;
            default:
                throw new RuntimeException("Illegal status code. " + statusCode);
        }
    }

    public void print() {
        int minx = walls.keySet().stream().min(new Pos.xComparator()).get().getX();
        int miny = walls.keySet().stream().min(new Pos.yComparator()).get().getY();
        int maxx = walls.keySet().stream().max(new Pos.xComparator()).get().getX();
        int maxy = walls.keySet().stream().max(new Pos.yComparator()).get().getY();
        StringBuilder sb = new StringBuilder();
        for (int y = miny-1; y <= maxy+1; y++) {
            for (int x = minx-1; x <= maxx+1; x++) {
                Pos p = new Pos(x,y);
                if (p.equals(Pos.ORIGO)) {
                    sb.append("X");
                } else
                if (p.equals(pos)) {
                    sb.append("O");
                } else
                if (walls.containsKey(p)) {
                    if (walls.get(p)) {
                        sb.append("#");
                    } else {
                        sb.append(" ");
                    }
                } else {
                    sb.append(".");
                }
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }


    public Pos getPos() {
        return pos;
    }

}
