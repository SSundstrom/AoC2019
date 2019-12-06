package Day6;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class Day6Test {

    @Test
    void ex1() {
        HashMap<String, Planet> planets = Day6.parsePlanets("src/Day6/ex1.txt");
        Planet youParent = planets.get("YOU").getParent();
        Planet sanParent = planets.get("SAN").getParent();
        int path = youParent.distanceTo(sanParent.getID());
        assertEquals(4, path);
    }


}