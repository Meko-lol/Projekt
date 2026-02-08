package Inventory;

import Items.Item;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private Item[][] grid;
    private boolean hasBackpack;

    private static final int BASE_ROWS = 2;
    private static final int BACKPACK_BONUS_ROWS = 5;
    private static final int COLUMNS = 5;

    public Inventory() {
        this.grid = new Item[BASE_ROWS][COLUMNS];
        this.hasBackpack = false;
    }

    public Item[][] getGrid() {
        return grid;
    }

    public boolean addItem(Item item) {
        // Simple nested for-loop
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == null) {
                    grid[i][j] = item;
                    return true;
                }
            }
        }
        return false;
    }

    public Item removeItemByName(String name) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] != null) {
                    if (grid[i][j].getName().equalsIgnoreCase(name)) {
                        Item item = grid[i][j];
                        grid[i][j] = null;
                        return item;
                    }
                }
            }
        }
        return null;
    }

    public Item getItemByName(String name) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] != null) {
                    if (grid[i][j].getName().equalsIgnoreCase(name)) {
                        return grid[i][j];
                    }
                }
            }
        }
        return null;
    }

    public List<Item> getAllItems() {
        List<Item> items = new ArrayList<>();
        if (grid == null) return items;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] != null) {
                    items.add(grid[i][j]);
                }
            }
        }
        return items;
    }

    public void equipBackpack() {
        if (!this.hasBackpack) {
            this.hasBackpack = true;
            resizeGrid(BASE_ROWS + BACKPACK_BONUS_ROWS, COLUMNS);
        }
    }

    public void unequipBackpack() {
        if (this.hasBackpack) {
            this.hasBackpack = false;
            resizeGrid(BASE_ROWS, COLUMNS);
        }
    }

    private void resizeGrid(int newRows, int newCols) {
        Item[][] newGrid = new Item[newRows][newCols];
        
        int rowsToCopy = grid.length;
        if (newRows < rowsToCopy) {
            rowsToCopy = newRows;
        }
        
        int colsToCopy = grid[0].length;
        if (newCols < colsToCopy) {
            colsToCopy = newCols;
        }

        for (int i = 0; i < rowsToCopy; i++) {
            for (int j = 0; j < colsToCopy; j++) {
                newGrid[i][j] = grid[i][j];
            }
        }
        this.grid = newGrid;
    }

    @JsonIgnore
    public double getTotalWeight() {
        double totalWeight = 0;
        List<Item> allItems = getAllItems();
        for (int i = 0; i < allItems.size(); i++) {
            Item item = allItems.get(i);
            totalWeight = totalWeight + item.getWeight();
        }
        return totalWeight;
    }

    public boolean hasBackpack() {
        return hasBackpack;
    }
}
