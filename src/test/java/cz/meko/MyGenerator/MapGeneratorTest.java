package cz.meko.MyGenerator;

import cz.meko.Game.GameSettings;
import cz.meko.GameMap.MyMap;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests the MapGenerator class.
 * This class checks if the game correctly builds the world map grid
 * based on the sizes we give it in the settings.
 * * @author Petrák
 */
class MapGeneratorTest {

    /**
     * Tests the generateMap method.
     * Sets up dummy game settings asking for a 5x5 map, tells the MapGenerator
     * to build it, and then checks two things:
     * 1. The map actually got created (not null).
     * 2. The underlying grid array has a length of exactly 5.
     */
    @Test
    void generateMap() {
        GameSettings settings = new GameSettings();
        settings.setMapWidth(5);
        settings.setMapHeight(5);

        MyMap map = MapGenerator.generateMap(settings);
        assertNotNull(map);
        assertEquals(5, map.getGrid().length);
    }
}