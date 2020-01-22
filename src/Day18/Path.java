package Day18;

import Utils.Pos;

public class Path {
    Pos position;
    Map map;
    int distance;

    public Path(Pos position, Map map, int distance) {
        this.position = position;
        this.map = map;
        this.distance = distance;
    }

    public Path(Path path){
        this(path.position, new Map(path.map), path.distance);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(position);
        sb.append("[").append(String.format("%3d", distance));
        sb.append("] ").append(map.keys.get(position));
        return sb.toString();
    }

}
