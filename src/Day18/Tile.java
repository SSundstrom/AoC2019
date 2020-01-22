package Day18;

public enum Tile {
    WALL, KEY, DOOR, OPEN;

    public static Tile parse(int i) {
        switch (i) {
            case '#': return WALL;
            case '.': return OPEN;
            default:
                if ('a' <= i && i <= 'z') return KEY;
                if ('A' <= i && i <= 'Z') return DOOR;
        }
        throw new RuntimeException("parse error in Tile");
    }
}
