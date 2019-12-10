package Utils;

import java.util.Objects;

public class Pos {
    final int x;
    final int y;

    public static final Pos ORIGO = new Pos(0, 0);
    public static final Pos NORTH = new Pos(0, -1);

    public Pos(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Pos move(Direction d){
        switch (d) {
            case N:
                return new Pos(x, y+1);
            case S:
                return new Pos(x, y-1);
            case E:
                return new Pos(x+1, y);
            case W:
                return new Pos(x-1, y);
        }
        System.out.println("SOMETHING WENT HORRIBLY WRONG!");
        System.exit(1);
        return this;
    }

    public int manhattanDist(Pos p2) {
        return Math.abs(p2.x - x) + Math.abs(p2.y - y);
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
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
}