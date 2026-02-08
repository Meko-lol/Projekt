package MyGenerator;

import Characters.NPCs.NPC;
import Characters.NPCs.TraderNPC;
import Game.FileManager;
import Game.GameSettings; // Import GameSettings
import GameMap.MyMap;
import Interact.Node;
import Items.Item;
import Places.Location;
import Places.Obstacle;
import Quest.Quest;
import ending.boulder.Boulder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MapGenerator {

    private static final Random random = new Random();
    private static List<String> npcNames;
    private static List<Quest> questTemplates;
    private static List<Item> itemTemplates;
    private static List<Node> dialogueTemplates;
    private static List<Obstacle> obstacleTemplates;
    
    private static final List<String> locationNames = Arrays.asList(
        "Dark Thicket", "Crumbling Ruins", "Misty Clearing", "Ancient Road", 
        "Overgrown Path", "Rocky Outcrop", "Silent Grove", "Shadowy Valley",
        "Sunlit Meadow", "Forgotten Graveyard", "Whispering Woods", "Muddy Bog"
    );

    // THE FIX: Accept GameSettings
    public static MyMap generateMap(GameSettings settings) {
        npcNames = FileManager.loadNames();
        questTemplates = FileManager.loadQuests();
        itemTemplates = FileManager.loadItems();
        dialogueTemplates = FileManager.loadDialogues();
        obstacleTemplates = FileManager.loadObstacles();

        int width = settings.getMapWidth();
        int height = settings.getMapHeight();
        MyMap map = new MyMap(width, height);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                String randomName = locationNames.get(random.nextInt(locationNames.size()));
                Location loc = new Location(randomName, null);
                loc.setX(x);
                loc.setY(y);
                map.setLocation(x, y, loc);
            }
        }

        placeSpecialLocations(map, width, height);
        populateWorld(map, width, height, settings); // Pass settings

        return map;
    }

    private static void placeSpecialLocations(MyMap map, int width, int height) {
        int prisonX, prisonY;
        do {
            prisonX = random.nextInt(width);
            prisonY = random.nextInt(height);
        } while (prisonX == 0 && prisonY == 0);
        map.getLocation(prisonX, prisonY).setName("The Prison");

        int exitX, exitY;
        do {
            exitX = random.nextInt(width);
            exitY = random.nextInt(height);
        } while ((exitX == 0 && exitY == 0) || (exitX == prisonX && exitY == prisonY));
        Location exitLocation = map.getLocation(exitX, exitY);
        exitLocation.setName("The Exit");
        
        NPC guardian1 = new NPC("Guardian", "Stone Sentinel", 150, 500, 2, 200, 20, 5, 5, null, true);
        NPC guardian2 = new NPC("Guardian", "Stone Sentinel", 150, 500, 2, 200, 20, 5, 5, null, true);
        exitLocation.addNpc(guardian1);
        exitLocation.addNpc(guardian2);
        exitLocation.setBoulder(new Boulder(100));
        
        Location townSquare = map.getLocation(0, 0);
        townSquare.setName("Town Square");
        TraderNPC trader = new TraderNPC("Barnaby", "generic_greeting");
        trader.addItemForSale(new Item("Health Potion", "potion", 0.5, 1, "Restores 25 health."), 50);
        townSquare.addNpc(trader);
    }

    private static void populateWorld(MyMap map, int width, int height, GameSettings settings) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Location loc = map.getLocation(x, y);
                if ("The Prison".equals(loc.getName()) || "The Exit".equals(loc.getName()) || "Town Square".equals(loc.getName())) {
                    continue;
                }
                
                // Use settings for spawn chances
                if (random.nextInt(100) < settings.getNpcSpawnChance()) {
                    if (random.nextInt(10) == 0) {
                        if (random.nextBoolean()) loc.addNpc(createWeaponsmith());
                        else loc.addNpc(createAlchemist());
                    } else {
                        loc.addNpc(createRandomNPC());
                    }
                }
                
                if (random.nextInt(100) < settings.getEnemySpawnChance()) {
                    loc.addNpc(createGoblin(settings.getDifficulty()));
                }
                
                if (random.nextInt(100) < settings.getItemSpawnChance() && !itemTemplates.isEmpty()) {
                    loc.addItem(itemTemplates.get(random.nextInt(itemTemplates.size())));
                    if (random.nextBoolean()) {
                        loc.addItem(itemTemplates.get(random.nextInt(itemTemplates.size())));
                    }
                }
                
                List<String> validDirections = new ArrayList<>();
                if (y > 0) validDirections.add("north");
                if (y < height - 1) validDirections.add("south");
                if (x < width - 1) validDirections.add("east");
                if (x > 0) validDirections.add("west");
                Collections.shuffle(validDirections);
                for (int i = 1; i < validDirections.size(); i++) {
                    addRandomObstacle(loc, validDirections.get(i));
                }
            }
        }
    }

    private static TraderNPC createWeaponsmith() {
        TraderNPC smith = new TraderNPC("Grom", "generic_greeting");
        smith.addItemForSale(findItemByName("Steel Sword"), 300);
        smith.addItemForSale(findItemByName("Warhammer"), 500);
        smith.addItemForSale(findItemByName("Plate Armor"), 600);
        return smith;
    }

    private static TraderNPC createAlchemist() {
        TraderNPC alchemist = new TraderNPC("Elara", "generic_greeting");
        alchemist.addItemForSale(findItemByName("Health Potion"), 50);
        alchemist.addItemForSale(findItemByName("Stamina Potion"), 75);
        alchemist.addItemForSale(findItemByName("Strength Elixir"), 200);
        alchemist.addItemForSale(findItemByName("Magic Wand"), 1000);
        return alchemist;
    }

    private static Item findItemByName(String name) {
        for (Item item : itemTemplates) {
            if (item.getName().equalsIgnoreCase(name)) {
                return item;
            }
        }
        return new Item(name, "misc", 1, 1, "A generic item.");
    }

    private static NPC createRandomNPC() {
        if (npcNames.isEmpty() || dialogueTemplates.isEmpty() || questTemplates.isEmpty() || itemTemplates.isEmpty()) {
            return new NPC("Default NPC", "Human", 100, 70, 5, 100, 5, 5, 10, "generic_greeting", false);
        }
        String name = npcNames.get(random.nextInt(npcNames.size()));
        Node dialogue = dialogueTemplates.get(random.nextInt(dialogueTemplates.size()));
        NPC npc = new NPC(name, "Human", 100, 70, 5, 100, 5, 5, 10, dialogue.getName(), false);
        
        Quest questTemplate = questTemplates.get(random.nextInt(questTemplates.size()));
        Quest newQuest = questTemplate.clone();
        newQuest.reward = itemTemplates.get(random.nextInt(itemTemplates.size()));
        npc.setQuestToGive(newQuest);
        return npc;
    }

    private static NPC createGoblin(String difficulty) {
        int hp = 50;
        int strength = 12;
        
        if (difficulty.equalsIgnoreCase("Hard")) {
            hp = 80;
            strength = 18;
        } else if (difficulty.equalsIgnoreCase("Easy")) {
            hp = 30;
            strength = 8;
        }

        NPC goblin = new NPC("Goblin", "Goblin", hp, 40, 7, 50, strength, 8, 3, null, true);
        if (!itemTemplates.isEmpty()) {
            goblin.addLoot(itemTemplates.get(random.nextInt(itemTemplates.size())));
        }
        return goblin;
    }

    private static void addRandomObstacle(Location location, String direction) {
        if (obstacleTemplates.isEmpty()) return;
        if (random.nextInt(3) == 0) {
            Obstacle template;
            do {
                template = obstacleTemplates.get(random.nextInt(obstacleTemplates.size()));
            } while (template.getName() == null);
            location.setObstacle(direction, new Obstacle(template.getName(), template.getDescription(), template.getRequiredItemName(), template.getDurability()));
        }
    }
}
