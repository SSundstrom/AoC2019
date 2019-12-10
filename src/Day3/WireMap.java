package Day3;

import Utils.Pos;

import java.util.HashMap;

public class WireMap {

    HashMap<Pos, Integer> map;
    int closestCollision;
    int shortestCollision;

    public WireMap() {
        this.map = new HashMap<>();
        this.closestCollision = Integer.MAX_VALUE;
        this.shortestCollision = Integer.MAX_VALUE;
    }

    public void placeWire(Wire wire) {
        wire.getPlacements().forEach(this::merge);
    }

    private void merge(Pos pos, Integer length) {
        if (map.containsKey(pos)) {
            int manhattanDistance = pos.manhattanDist(Pos.ORIGO);
            closestCollision = Math.min(closestCollision, manhattanDistance);
            int wireDistance = map.get(pos) + length;
            shortestCollision = Math.min(shortestCollision, wireDistance);
        } else {
            map.put(pos, length);
        }
    }

    public int getClosestCollision() {
        return closestCollision;
    }

    public int getShortestCollision() {
        return shortestCollision;
    }
}
