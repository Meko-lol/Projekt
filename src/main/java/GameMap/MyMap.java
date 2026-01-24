package GameMap;

import Places.Location;
import java.util.ArrayList;
import java.util.List;

public class MyMap {
    private Location[][] map;

    /**
     * No-argument constructor for Jackson deserialization.
     */
    public MyMap() {
    }

    public MyMap(int width, int height) {
        this.map = new Location[width][height];
    }

    public Location[][] getMap() {
        return map;
    }

    public void setMap(Location[][] map) {
        this.map = map;
    }

    public Location getLocation(int x, int y) {
        if (isValid(x, y)) {
            return map[y][x];
        }
        return null;
    }

    public void setLocation(int x, int y, Location location) {
        if (isValid(x, y)) {
            map[y][x] = location;
        }
    }

    public List<Location> findNeighbouringLocations(int x, int y) {
        List<Location> neighbours = new ArrayList<>();

        if (isValid(x, y - 1)) {
            neighbours.add(map[y - 1][x]);
        }
        if (isValid(x, y + 1)) {
            neighbours.add(map[y + 1][x]);
        }
        if (isValid(x - 1, y)) {
            neighbours.add(map[y][x - 1]);
        }
        if (isValid(x + 1, y)) {
            neighbours.add(map[y][x + 1]);
        }

        return neighbours;
    }

    private boolean isValid(int x, int y) {
        if (map == null || map.length == 0) {
            return false;
        }
        return y >= 0 && y < map.length && x >= 0 && x < map[0].length;
    }
}
