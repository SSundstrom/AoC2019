package Day18;

import Utils.Pos;

import java.util.*;
import java.util.stream.Collectors;

public class Map {

    HashMap<Pos, Integer> finalMap;
    HashMap<Pos, PoI> posToPoiMap;
    HashMap<PoI, Pos> poiToPosMap;
    Set<PoI> items;

    Pos entryPoint;

    public Map(String input) {
        finalMap = new HashMap<>();
        posToPoiMap = new HashMap<>();
        poiToPosMap = new HashMap<>();
        parseMap(input);
        items = poiToPosMap.keySet();
    }

    void parseMap(String input) {
        String[] lines = input.split("\n");
        for (int y = 0; y < lines.length; y++) {
            ArrayList<Integer> line = lines[y].chars().boxed().collect(Collectors.toCollection(ArrayList::new));
            for (int x = 0; x < line.size(); x++) {
                Pos p = new Pos(x, y);
                finalMap.put(p, line.get(x));
                char val = (char)line.get(x).intValue();
                if (val == '@') {
                    entryPoint = p;
                } else {
                    Optional<PoI> poi = parseTile(val);
                    poi.ifPresent(poI -> {
                        posToPoiMap.put(p, poI);
                        poiToPosMap.put(poI, p);
                    });
                }
            }
        }
    }

    public void removePoI(PoI poi) {
        items.remove(poi);
        poiToPosMap.remove(poi);
        for (java.util.Map.Entry<Pos, PoI> entry : posToPoiMap.entrySet()) {
            if (entry.getValue().equals(poi)) {
                posToPoiMap.remove(entry.getKey());
                break;
            }
        }
    }

    public Pos poiToPos(PoI poi) {
        return poiToPosMap.get(poi);
    }

    public PoI posToPoI(Pos pos) {
        return posToPoiMap.get(pos);
    }

    private Optional<PoI> parseTile(char c) {
        if (c >= 'A' && c <= 'Z') {
            return Optional.of(new Door(c));
        }
        if  (c >= 'a' && c <= 'z') {
            return Optional.of(new Key(c));
        }
        return Optional.empty();
    }

    public boolean isPoI(Pos pos) {
        int c = finalMap.get(pos);
        return (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z');
    }

    public boolean isWall(Pos pos) {
        int c = finalMap.get(pos);
        return c == '#';
    }

}
