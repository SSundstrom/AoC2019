package Day6;

import java.util.*;

public class Planet {

    private HashMap<String, Planet> planetsInOrbit;
    private final String ID;
    private Planet parent;

    public Planet(String ID) {
        this.ID = ID;
        planetsInOrbit = new HashMap<String, Planet>();
    }

    public String getID() {
        return ID;
    }

    public void addToOrbit(Planet planet) {
        planetsInOrbit.put(planet.ID, planet);
    }

    public Planet getParent() {
        return parent;
    }

    public void setParent(Planet parent) {
        this.parent = parent;
    }

    public int distanceTo(String ID) {
        HashMap<String, Integer> paths = new HashMap<>();
        LinkedList<Planet> layer = new LinkedList<Planet>();
        paths.put(this.ID, 0);
        layer.add(this);
        while (!paths.containsKey(ID)) {
            LinkedList<Planet> nextLayer = new LinkedList<Planet>();
            for (Planet p: layer) {
                p.getNeighbors().forEach(planet -> {
                    if (!paths.containsKey(planet.ID)) {
                        paths.put(planet.ID, paths.get(p.ID) + 1);
                        nextLayer.add(planet);
                    }
                });
            }
            layer = nextLayer;
        }
        return paths.get(ID);
    }

    public LinkedList<Planet> getNeighbors(){
        LinkedList<Planet> planets = new LinkedList<>(planetsInOrbit.values());
        if (parent != null) {
            planets.add(parent);
        }
        return planets;
    }

    public int getTotalOrbits(int depth) {
        int orbits = 0;
        for (Planet moon : planetsInOrbit.values()) {
            orbits += moon.getTotalOrbits(depth+1);
        }
        return orbits + depth;
    }

    @Override
    public String toString() {
        return ID;
    }
}
