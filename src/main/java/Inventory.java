package Inventory;

import Items.Item;

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
        int rowsToCopy = Math.min(grid.length, newRows);
        int colsToCopy = Math.min(grid[0].length, newCols);

        for (int i = 0; i < rowsToCopy; i++) {
            for (int j = 0; j < colsToCopy; j++) {
                newGrid[i][j] = grid[i][j];
            }
        }
        this.grid = newGrid;
    }

    public boolean setItemAt(int row, int col, Item item) {
        if (isValidSlot(row, col) && grid[row][col] == null) {
            grid[row][col] = item;
            return true;
        }
        return false;
    }

    public Item removeItemAt(int row, int col) {
        if (isValidSlot(row, col)) {
            Item item = grid[row][col];
            grid[row][col] = null;
            return item;
        }
        return null;
    }

    public Item getItemAt(int row, int col) {
        if (isValidSlot(row, col)) {
            return grid[row][col];
        }
        return null;
    }

    public double getTotalWeight() {
        double totalWeight = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] != null) {
                    totalWeight += grid[i][j].getWeight();
                }
            }
        }
        return totalWeight;
    }

    private boolean isValidSlot(int row, int col) {
        return row >= 0 && row < grid.length && col >= 0 && col < grid[0].length;
    }

    public boolean hasBackpack() {
        return hasBackpack;
    }

    public void displayGrid() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == null) {
                } else {
                }
            }
        }
    }
}
