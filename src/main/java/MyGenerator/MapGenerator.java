package MyGenerator;

import Characters.NPCs.NPC;
import GameMap.MyMap;
import Interact.Node;
import Items.Item;
import Places.Location;
import Quest.Quest;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MapGenerator {

    private static final Random random = new Random();
    private static final List<String> npcNames = new ArrayList<>();
    private static final List<Quest> questTemplates = new ArrayList<>();
    private static final List<Item> itemTemplates = new ArrayList<>();
    private static final List<Node> dialogueTemplates = new ArrayList<>();
    private static final List<Obstacle> obstacleTemplates = new ArrayList<>();

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

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Location loc = new Location("An empty field", null);
                loc.setX(x);
                loc.setY(y);
                map.setLocation(x, y, loc);
            }
        }

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
        
        map.getLocation(0, 0).setName("Town Square");

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Location loc = map.getLocation(x, y);
                if ("The Prison".equals(loc.getName()) || "The Exit".equals(loc.getName()) || "Town Square".equals(loc.getName())) {
                    continue;
                }
                if (random.nextInt(5) == 0) loc.addNpc(createRandomNPC());
                
                // THE FIX: Add a chance for a hostile Goblin to spawn.
                if (random.nextInt(8) == 0) {
                    loc.addNpc(createGoblin());
                }
                
                addRandomObstacle(loc, "north");
                addRandomObstacle(loc, "south");
                addRandomObstacle(loc, "east");
                addRandomObstacle(loc, "west");
            }
        }
        return map;
    }

    private static NPC createRandomNPC() {
        if (npcNames.isEmpty() || dialogueTemplates.isEmpty() || questTemplates.isEmpty() || itemTemplates.isEmpty()) {
            return new NPC("Mysterious Stranger", "Human", 100, 70, 5, 100, 5, 5, 10, "generic_greeting", false);
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
        // Give the goblin a gold coin as loot.
        goblin.addLoot(new Item("Gold Coin", "currency", 0.1, 1, "A shiny gold coin."));
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
