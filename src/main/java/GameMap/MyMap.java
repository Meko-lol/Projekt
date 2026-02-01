package GameMap;

import Places.Location;
import com.fasterxml.jackson.annotation.JsonAlias; // Import the annotation

public class MyMap {
    
    // THE FIX: This tells the loader that the 'grid' field might also be called 'map' in old save files.
    @JsonAlias("map") 
    private Location[][] grid;

    public MyMap() {}

    public MyMap(int width, int height) {
        this.grid = new Location[height][width];
    }

    public Location[][] getGrid() {
        return grid;
    }

    public void setGrid(Location[][] grid) {
        this.grid = grid;
    }

    public Location getLocation(int x, int y) {
        if (isValid(x, y)) {
            return grid[y][x];
        }
        return null;
    }

    public void setLocation(int x, int y, Location location) {
        if (isValid(x, y)) {
            grid[y][x] = location;
        }
    }

    private boolean isValid(int x, int y) {
        if (grid == null || grid.length == 0) {
            return false;
        }
        return y >= 0 && y < grid.length && x >= 0 && x < grid[0].length;
    }
}
