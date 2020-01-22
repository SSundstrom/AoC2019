package Day18;

import Utils.BiMap;
import Utils.Pos;

import java.util.*;

public class Map {

    HashMap<Pos, Tile> map;
    BiMap<Pos, Key> keys;
    BiMap<Pos, Door> doors;

    Pos entryPoint;

    public Map(String input) {
        map = new HashMap<>();
        keys = new BiMap<>();
        doors = new BiMap<>();
        parseMap(input);
    }

    public Map(Map old) {
        map = new HashMap<>(old.map);
        keys = new BiMap<Pos, Key>(old.keys);
        doors = new BiMap<>(old.doors);
    }

    void parseMap(String input) {
        int y = 0;
        int x = 0;
        for (char c : input.toCharArray()) {
            if (c == '\n') {
                y++;
                x = 0;
                continue;
            }
            Pos pos = new Pos(x, y);
            Tile t = Tile.parse(c);
            map.put(pos, t);
            if (t.equals(Tile.KEY)) keys.put(pos, new Key(c));
            if (t.equals(Tile.DOOR)) doors.put(pos, new Door(c));
            x++;
        }
    }

    public boolean isWall(Pos pos) {
        int c = finalMap.get(pos);
        return c == '#';
    }

}
