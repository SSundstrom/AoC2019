package Day6;

import Utils.Input;

import java.util.HashMap;

public class Day6 {

    public static HashMap<String, Planet> parsePlanets(String filename) {
        HashMap<String, Planet> planets = new HashMap<String, Planet>();
        Input.getInputAsStream(filename).map(s -> s.split("\\)")).forEach(planetID -> {
            planets.putIfAbsent(planetID[0], new Planet(planetID[0]));
            planets.putIfAbsent(planetID[1], new Planet(planetID[1]));
            planets.get(planetID[0]).addToOrbit(planets.get(planetID[1]));
            planets.get(planetID[1]).setParent(planets.get(planetID[0]));
        });
        return planets;
    }

    public static void main(String[] args) {
        String filename = "input/Day6.txt";
        HashMap<String, Planet> planets = parsePlanets(filename);
        System.out.println("Part1: " + planets.get("COM").getTotalOrbits(0));

        Planet youParent = planets.get("YOU").getParent();
        Planet sanParent = planets.get("SAN").getParent();
        System.out.println("Part2: " + youParent.distanceTo(sanParent.getID()));
    }
}