package MyGenerator;

import Characters.Character;
import Characters.NPCs.Enemy;
import Characters.NPCs.NPC;
import Items.Item;
import MyFileManager.MyFileManager;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MyGenerator {

    private MyFileManager fileManager;
    private List<String> characterNames;
    private Random random = new Random();

    public MyGenerator(MyFileManager fileManager) {
        this.fileManager = fileManager;
        loadCharacterNames();
    }

    private void loadCharacterNames() {
        try {
            this.characterNames = Files.readAllLines(Paths.get("Source/Names.txt"));
        } catch (Exception e) {
            this.characterNames = List.of("Alex", "Ben", "Charlie");
        }
    }

    public String generateName() {
        if (characterNames == null || characterNames.isEmpty()) {
            return "Nameless";
        }
        return characterNames.get(random.nextInt(characterNames.size()));
    }

    /**
     * Generates a new, non-hostile NPC with stats based on a level.
     * @param level The level (1-5) of the NPC to generate.
     * @return A new NPC object.
     */
    public NPC generateNPC(int level) {
        String name = generateName();
        // Base stats
        int baseStat = 5;
        // Apply level bonus
        int strength = baseStat + level;
        int agility = baseStat + level;
        int intelligence = baseStat + level;

        return new NPC(name, "Human", 100, 70, 5, 100, strength, agility, intelligence) {};
    }

    /**
     * Generates a new Enemy with stats based on a level.
     * @param level The level (1-5) of the Enemy to generate.
     * @return A new Enemy object.
     */
    public Enemy generateEnemy(int level) {
        String name = "Lvl " + level + " Goblin"; // Example enemy name
        double health = 80 + (level * 20); // Health scales with level
        int strength = 8 + (level * 2); // Strength scales with level
        int agility = 5 + level;

        return new Enemy(name, "Goblin", health, 50, 6, 100, strength, agility, 5);
    }

    /**
     * Generates a new Item with quality based on a level.
     * @param level The level (1-5) of the item to generate.
     * @return A new Item object.
     */
    public Item generateNewItem(int level) {
        String[] prefixes = {"Broken", "Rusty", "Common", "Sturdy", "Fine", "Masterwork"};
        String[] itemTypes = {"Sword", "Dagger", "Axe", "Shield", "Potion"};
        
        // Level determines the quality (prefix) of the item
        String prefix = prefixes[level > 0 && level <= prefixes.length ? level - 1 : 2]; // Default to "Common"
        
        String name = prefix + " " + itemTypes[random.nextInt(itemTypes.length)];
        double weight = (random.nextDouble() * 4 + 2); // Random weight between 2.0 and 6.0
        double durability = 80 + (level * 5); // Durability scales with level

        return new Item(name, weight, durability, "A " + name + ".");
    }
    
    /**
     * Generates a list of new items with quality based on a level.
     * @param level The level (1-5) of the loot to generate.
     * @param count The number of items to generate.
     * @return A List of new Item objects.
     */
    public List<Item> generateLoot(int level, int count) {
        List<Item> loot = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            loot.add(generateNewItem(level));
        }
        return loot;
    }
}
