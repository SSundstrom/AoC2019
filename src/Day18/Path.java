package Day18;

import Utils.Pos;

import java.util.HashSet;
import java.util.Objects;

public class Path {
    Pos position;
    HashSet<PoI> visited;
    int distance;

    public Path(Pos position, HashSet<PoI> visited, int distance) {
        this.position = position;
        this.visited = visited;
        this.distance = distance;
    }

    public Path(Path path){
        this(path.position, new HashSet<>(path.visited), path.distance);
    }

    public int getNumberOfVisited() {
        return visited.size();
    }

    public boolean locked(PoI poi) {
        if (poi instanceof Door) {
            return !visited.contains(new Key((char)(poi.getChar() + 32)));
        }
        return false;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(position);
        sb.append("[").append(String.format("%3d", distance));
        sb.append("] ").append(visited);
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Path path = (Path) o;
        return Objects.equals(visited, path.visited);
    }

    @Override
    public int hashCode() {
        return Objects.hash(visited);
    }
}
