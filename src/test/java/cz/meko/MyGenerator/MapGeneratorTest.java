package cz.meko.MyGenerator;

import cz.meko.Game.GameSettings;
import cz.meko.GameMap.MyMap;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MapGeneratorTest {

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
