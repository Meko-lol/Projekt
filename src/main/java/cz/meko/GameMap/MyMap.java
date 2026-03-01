package cz.meko.GameMap;

import cz.meko.Places.Location;
import com.fasterxml.jackson.annotation.JsonAlias;

/**
 * Represents the game map as a 2D grid of locations.
 * 
 * @author Jan Petrak
 */
public class MyMap {
    
    @JsonAlias("map") 
    private Location[][] grid;

    public MyMap() {}

    /**
     * Constructs a new map with the specified dimensions.
     * @param width The width of the map.
     * @param height The height of the map.
     */
    public MyMap(int width, int height) {
        this.grid = new Location[height][width];
    }

    public Location[][] getGrid() {
        return grid;
    }

    public void setGrid(Location[][] grid) {
        this.grid = grid;
    }

    /**
     * Gets the location at the specified coordinates.
     * @param x The x-coordinate.
     * @param y The y-coordinate.
     * @return The location, or null if coordinates are invalid.
     */
    public Location getLocation(int x, int y) {
        if (isValid(x, y)) {
            return grid[y][x];
        }
        return null;
    }

    /**
     * Sets the location at the specified coordinates.
     * @param x The x-coordinate.
     * @param y The y-coordinate.
     * @param location The location to set.
     */
    public void setLocation(int x, int y, Location location) {
        if (isValid(x, y)) {
            grid[y][x] = location;
        }
    }

    /**
     * Checks if the given coordinates are within the map bounds.
     * @param x The x-coordinate.
     * @param y The y-coordinate.
     * @return True if valid, false otherwise.
     */
    private boolean isValid(int x, int y) {
        if (grid == null || grid.length == 0) {
            return false;
        }
        return y >= 0 && y < grid.length && x >= 0 && x < grid[0].length;
    }
}
