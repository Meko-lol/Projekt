package Game;

public class GameSettings {
    private int mapWidth = 8;
    private int mapHeight = 8;
    private int npcSpawnChance = 50; // Percentage (0-100)
    private int enemySpawnChance = 33; // Percentage (0-100)
    private int itemSpawnChance = 50; // Percentage (0-100)
    private String difficulty = "Normal"; // Easy, Normal, Hard

    // Getters and Setters
    public int getMapWidth() { return mapWidth; }
    public void setMapWidth(int mapWidth) { this.mapWidth = mapWidth; }

    public int getMapHeight() { return mapHeight; }
    public void setMapHeight(int mapHeight) { this.mapHeight = mapHeight; }

    public int getNpcSpawnChance() { return npcSpawnChance; }
    public void setNpcSpawnChance(int npcSpawnChance) { this.npcSpawnChance = npcSpawnChance; }

    public int getEnemySpawnChance() { return enemySpawnChance; }
    public void setEnemySpawnChance(int enemySpawnChance) { this.enemySpawnChance = enemySpawnChance; }

    public int getItemSpawnChance() { return itemSpawnChance; }
    public void setItemSpawnChance(int itemSpawnChance) { this.itemSpawnChance = itemSpawnChance; }

    public String getDifficulty() { return difficulty; }
    public void setDifficulty(String difficulty) { this.difficulty = difficulty; }
}
