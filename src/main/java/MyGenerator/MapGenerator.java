package MyGenerator;

import Characters.NPCs.NPC;
import Characters.NPCs.TraderNPC;
import GameMap.MyMap;
import Interact.Node;
import Items.Item;
import Places.Location;
import Places.Obstacle;
import Quest.Quest;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MapGenerator {

    private static final Random random = new Random();
    private static final List<String> npcNames = new ArrayList<>();
    private static final List<Quest> questTemplates = new ArrayList<>();
    private static final List<Item> itemTemplates = new ArrayList<>();
    private static final List<Node> dialogueTemplates = new ArrayList<>();
    private static final List<Obstacle> obstacleTemplates = new ArrayList<>();
    
    // New list of atmospheric location names
    private static final List<String> locationNames = Arrays.asList(
        "Dark Thicket", "Crumbling Ruins", "Misty Clearing", "Ancient Road", 
        "Overgrown Path", "Rocky Outcrop", "Silent Grove", "Shadowy Valley",
        "Sunlit Meadow", "Forgotten Graveyard", "Whispering Woods", "Muddy Bog"
    );

    private static void loadAllTemplates() {
        try {
            npcNames.clear();
            questTemplates.clear();
            itemTemplates.clear();
            dialogueTemplates.clear();
            obstacleTemplates.clear();
            ObjectMapper objectMapper = new ObjectMapper();
            npcNames.addAll(Files.readAllLines(Paths.get("Source/names.txt")));
            questTemplates.addAll(Arrays.asList(objectMapper.readValue(new File("Source/quests.json"), Quest[].class)));
            itemTemplates.addAll(Arrays.asList(objectMapper.readValue(new File("Source/items.json"), Item[].class)));
            dialogueTemplates.addAll(Arrays.asList(objectMapper.readValue(new File("Source/interacts.json"), Node[].class)));
            obstacleTemplates.addAll(Arrays.asList(objectMapper.readValue(new File("Source/obstacles.json"), Obstacle[].class)));
        } catch (Exception e) {
            System.out.println("CRITICAL ERROR: Could not load generation templates: " + e.getMessage());
        }
    }

    public static MyMap generateMap() {
        loadAllTemplates();
        MyMap map = new MyMap(8, 8);
        int width = 8;
        int height = 8;

        // 1. Create the grid with random location names.
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                String randomName = locationNames.get(random.nextInt(locationNames.size()));
                Location loc = new Location(randomName, null);
                loc.setX(x);
                loc.setY(y);
                map.setLocation(x, y, loc);
            }
        }

        // 2. Place special locations.
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
        map.getLocation(exitX, exitY).setName("The Exit");
        
        Location townSquare = map.getLocation(0, 0);
        townSquare.setName("Town Square");
        TraderNPC trader = new TraderNPC("Barnaby", "generic_greeting");
        trader.addItemForSale(new Item("Health Potion", "potion", 0.5, 1, "Restores 25 health."), 50);
        townSquare.addNpc(trader);

        // 3. Populate the world.
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Location loc = map.getLocation(x, y);
                if ("The Prison".equals(loc.getName()) || "The Exit".equals(loc.getName()) || "Town Square".equals(loc.getName())) {
                    continue;
                }
                if (random.nextInt(5) == 0) loc.addNpc(createRandomNPC());
                if (random.nextInt(8) == 0) loc.addNpc(createGoblin());
                
                // THE FIX: Ensure at least one exit is free.
                List<String> validDirections = new ArrayList<>();
                if (y > 0) validDirections.add("north");
                if (y < height - 1) validDirections.add("south");
                if (x < width - 1) validDirections.add("east");
                if (x > 0) validDirections.add("west");
                
                // Shuffle directions to randomize which one is kept free
                Collections.shuffle(validDirections);
                
                // We will try to add obstacles to all BUT the first direction in the shuffled list.
                // This guarantees validDirections.get(0) is always free.
                for (int i = 1; i < validDirections.size(); i++) {
                    addRandomObstacle(loc, validDirections.get(i));
                }
            }
        }
        return map;
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

    private static NPC createGoblin() {
        NPC goblin = new NPC("Goblin", "Goblin", 50, 40, 7, 50, 12, 8, 3, null, true);
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
