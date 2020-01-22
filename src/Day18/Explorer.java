package Day18;

import Utils.Direction;
import Utils.Pos;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Explorer {

    private HashSet<Path> paths;
    private Path finalPath;


    public Explorer(Map map) {
        paths = new HashSet<>();
        paths.add(new Path(map.entryPoint, map, 0));
        finalPath = null;
    }

    public Path getBestPath() {
        Path bestPath = null;
        for (Path path : paths) {
            if (bestPath == null) bestPath = path;
            switch (Integer.compare(bestPath.map.keys.size(), path.map.keys.size())) {
                case -1:
                    break;
                case 0:
                    bestPath = bestPath.distance > path.distance ? bestPath : path;
                    break;
                case 1:
                    bestPath = path;
                    break;
                default:
                    throw new RuntimeException("Compare in evaluatePath bork");
            }
        }
        return bestPath;
    }

    public boolean exploring() {
        Path bestPath = getBestPath();
        if (bestPath.map.keys.isEmpty()) {
            finalPath = bestPath;
        }
        return finalPath == null;
    }

    public void goToNextKey() {
        HashMap<Collection<Key>, Path> newPaths = new HashMap<>();
        for (Path path : paths) {
            HashSet<Path> steps = moveToKey(path);
            for (Path step : steps) {
                if (!newPaths.containsKey(step.map.keys.values()) || newPaths.get(step.map.keys.values()).distance >= step.distance) {
                    newPaths.put(step.map.keys.values(), step);
                }
            }
        }
        paths = new HashSet<Path>(newPaths.values());
    }

    private HashSet<Path> moveToKey(Path path) {
        HashMap<Key, Integer> distanceToKey = getRouteOptions(path);
        HashSet<Path> newPaths = appendPath(path, distanceToKey);
        return newPaths;
    }

    private HashSet<Path> appendPath(Path path, HashMap<PoI, Integer> distanceToPoi) {
        HashSet<Path> newPaths = new HashSet<>();
        for (java.util.Map.Entry<PoI, Integer> routeOption : distanceToPoi.entrySet()) {
            Path newPath = new Path(path);
            if (!newPath.locked(routeOption.getKey())) {
                newPath.distance += routeOption.getValue();
                newPath.visited.add(routeOption.getKey());
                newPath.position = map.poiToPos(routeOption.getKey());
                newPaths.add(newPath);
            }
        }
        return newPaths;
    }

    private HashMap<Key, Integer> getRouteOptions(Path path) {
        HashMap<Key, Integer> routeOptions = new HashMap<>();
        for (Object o : Direction.getAllDirections().toArray()) {
            if (o instanceof Direction) {
                Direction d = (Direction) o;
                routeOptions.putAll(forwardUntilKey(path.position.move(d), d, 1, path.visited));
            }
        }

        path.visited.forEach(routeOptions::remove);

        return routeOptions;
    }

    private HashMap<PoI, Integer> forwardUntilKey(Pos position, Direction moved, int steps, Set<PoI> visited) {
        HashMap<PoI, Integer> routes = new HashMap<>();
        if (map.isPoI(position) && !visited.contains(map.posToPoI(position))) {
            routes.put(map.posToPoI(position), steps);
        } else if (map.isWall(position)) {
            return routes;
        } else {
            Set<Direction> directions = moved.opposite().getAllOtherDirections().collect(Collectors.toSet());
            if (directions.contains(moved.opposite())) throw new RuntimeException("Forward is fucked.");
            for (Direction direction : directions) {
                routes.putAll(forwardUntilKey(position.move(direction), direction, steps+1, visited));
            }
        }
        return routes;
    }

    private HashMap<PoI, Integer> findAllPoI(Pos fromPos, Direction cameFrom, int distance) {
        if (map.isPoI(fromPos)) {
            HashMap<PoI, Integer> set = new HashMap<>();
            set.put(map.posToPoiMap.get(fromPos), distance);
            return set;
        } else if (map.isWall(fromPos)) {
            return new HashMap<>();
        }
        return cameFrom.getAllOtherDirections()
                .map(p -> findAllPoI(fromPos.move(p), p.opposite(), distance + 1))
                .reduce((poIS, poIS2) -> {
                    poIS.putAll(poIS2);
                    return poIS;
                }).orElseThrow();
    }

    public Path getPath() {
        return finalPath;
    }


    public HashSet<Path> getPaths() {
        return paths;
    }

}
