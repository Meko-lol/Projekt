package Game;

import Characters.Player;
import Commands.CommandProcessor;
import GameMap.MyMap;
import Interact.Node;
import Items.Item;
import Items.EquippableItems.Backpack;
import Items.EquippableItems.Pants;
import Items.Items.EquippableItems.Boots;
import Items.Items.EquippableItems.Chestplate;
import Items.Items.EquippableItems.Helmet;
import Items.Weapons.CloseRangeWeapon;
import MyGenerator.MapGenerator;
import Places.Location;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MyGame {

    public Player player;
    public MyMap gameMap;
    
    @JsonAlias("xcordinate")
    public int xCordinate;
    
    @JsonAlias("ycordinate")
    public int yCordinate;
    
    // THE FIX: Removed @JsonIgnore so settings are saved/loaded
    public GameSettings settings;

    @JsonIgnore private Location currentLocation;
    @JsonIgnore private CommandProcessor commandProcessor;
    @JsonIgnore private List<Node> allDialogueNodes;
    @JsonIgnore private UserInterface ui;
    @JsonIgnore private GameEventManager eventManager;

    public MyGame() {
        this.ui = new UserInterface();
        this.eventManager = new GameEventManager();
        this.settings = new GameSettings(); // Default settings
    }

    public void startGame() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n=== Welcome to the Dungeon! ===");
            System.out.println("1. Start New Game");
            System.out.println("2. Load Game");
            System.out.println("3. Settings");
            System.out.println("4. Exit");
            System.out.print(">> ");

            String choice = scanner.nextLine();

            if (choice.equals("1")) {
                MyGame gameInstance = createNewGame();
                gameInstance.initialize();
                gameInstance.commandProcessor = new CommandProcessor(gameInstance.player, gameInstance);
                gameInstance.commandProcessor.run();
                break;
            } else if (choice.equals("2")) {
                MyGame gameInstance = loadGame();
                // THE FIX: Ensure we only run if load was successful
                if (gameInstance != null) {
                    gameInstance.initialize();
                    gameInstance.commandProcessor = new CommandProcessor(gameInstance.player, gameInstance);
                    gameInstance.commandProcessor.run();
                    break;
                }
            } else if (choice.equals("3")) {
                openSettingsMenu(scanner);
            } else if (choice.equals("4")) {
                System.out.println("Goodbye!");
                System.exit(0);
            } else {
                System.out.println("Invalid choice.");
            }
        }
    }

    private void openSettingsMenu(Scanner scanner) {
        while (true) {
            System.out.println("\n--- Settings ---");
            System.out.println("1. Map Size: " + settings.getMapWidth() + "x" + settings.getMapHeight());
            System.out.println("2. Difficulty: " + settings.getDifficulty());
            System.out.println("3. NPC Spawn Chance: " + settings.getNpcSpawnChance() + "%");
            System.out.println("4. Enemy Spawn Chance: " + settings.getEnemySpawnChance() + "%");
            System.out.println("5. Item Spawn Chance: " + settings.getItemSpawnChance() + "%");
            System.out.println("6. Back");
            System.out.print(">> ");

            String choice = scanner.nextLine();

            if (choice.equals("1")) {
                System.out.print("Enter new size (e.g., 10 for 10x10): ");
                try {
                    int size = Integer.parseInt(scanner.nextLine());
                    if (size >= 5 && size <= 20) {
                        settings.setMapWidth(size);
                        settings.setMapHeight(size);
                    } else {
                        System.out.println("Size must be between 5 and 20.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid number.");
                }
            } else if (choice.equals("2")) {
                System.out.println("Choose difficulty: 1. Easy, 2. Normal, 3. Hard");
                String diff = scanner.nextLine();
                if (diff.equals("1")) settings.setDifficulty("Easy");
                else if (diff.equals("2")) settings.setDifficulty("Normal");
                else if (diff.equals("3")) settings.setDifficulty("Hard");
            } else if (choice.equals("3")) {
                settings.setNpcSpawnChance(getIntInput(scanner, "Enter NPC spawn chance (0-100): "));
            } else if (choice.equals("4")) {
                settings.setEnemySpawnChance(getIntInput(scanner, "Enter Enemy spawn chance (0-100): "));
            } else if (choice.equals("5")) {
                settings.setItemSpawnChance(getIntInput(scanner, "Enter Item spawn chance (0-100): "));
            } else if (choice.equals("6")) {
                break;
            }
        }
    }

    private int getIntInput(Scanner scanner, String prompt) {
        System.out.print(prompt);
        try {
            int val = Integer.parseInt(scanner.nextLine());
            return Math.max(0, Math.min(100, val));
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private MyGame createNewGame() {
        System.out.println("Generating world with settings: " + settings.getDifficulty() + ", " + settings.getMapWidth() + "x" + settings.getMapHeight());
        MyGame newGame = new MyGame();
        newGame.settings = this.settings;
        newGame.gameMap = MapGenerator.generateMap(this.settings);
        
        newGame.player = new Player("Hero", "Human", 100, 70, 5, 100, 10, 10, 10);
        newGame.xCordinate = 0;
        newGame.yCordinate = 0;
        
        newGame.player.getInventory().addItem(new Item("Axe", "tool", 3.0, 100, "A sturdy axe."));
        newGame.player.getInventory().addItem(new Item("Pickaxe", "tool", 5.0, 100, "A heavy pickaxe."));
        newGame.player.getInventory().addItem(new Helmet("Iron Helmet", 4.0, 100, "A sturdy iron helmet.", 5));
        newGame.player.getInventory().addItem(new Chestplate("Leather Tunic", 5.0, 100, "A simple leather tunic.", 8));
        newGame.player.getInventory().addItem(new Pants("Cloth Pants", 2.0, 80, "Basic cloth pants.", 2));
        newGame.player.getInventory().addItem(new Boots("Leather Boots", 1.5, 90, "Sturdy leather boots.", 5));
        newGame.player.getInventory().addItem(new Backpack("Traveler's Backpack", 1.0, 200, "A large backpack.", 50));
        newGame.player.getInventory().addItem(new CloseRangeWeapon("Steel Sword", 5.0, 100, "A sharp steel blade.", 25));
        newGame.player.getInventory().addItem(new Item("Health Potion", "potion", 0.5, 1, "Restores 25 health."));
        newGame.player.getInventory().addItem(new Item("Health Potion", "potion", 0.5, 1, "Restores 25 health."));
        newGame.player.getInventory().addItem(new Item("Health Potion", "potion", 0.5, 1, "Restores 25 health."));
        
        return newGame;
    }

    private MyGame loadGame() {
        MyGame loadedGame = FileManager.loadGame();
        if (loadedGame != null) {
            System.out.println("Game loaded successfully.");
            return loadedGame;
        } else {
            System.out.println("Failed to load game. Please start a new game.");
            return null; // Return null to indicate failure
        }
    }

    public void saveGame() {
        FileManager.saveGame(this);
    }
    
    public void initialize() {
        // Re-initialize transient fields
        this.ui = new UserInterface();
        this.eventManager = new GameEventManager();
        this.allDialogueNodes = FileManager.loadDialogues();
        
        // Ensure map and location are valid
        if (this.gameMap != null) {
            this.currentLocation = this.gameMap.getLocation(this.xCordinate, this.yCordinate);
        }
    }

    public void handlePlayerDefeat() {
        eventManager.handlePlayerDefeat(this);
    }

    public void move(String direction) {
        int newX = this.xCordinate;
        int newY = this.yCordinate;
        switch (direction.toLowerCase()) {
            case "north": newY--; break;
            case "south": newY++; break;
            case "west":  newX--; break;
            case "east":  newX++; break;
            default: return;
        }
        Location newLocation = gameMap.getLocation(newX, newY);
        if (newLocation != null) {
            this.currentLocation = newLocation;
            this.xCordinate = newX;
            this.yCordinate = newY;
        }
    }

    @JsonIgnore
    public String getDashboard() {
        return ui.getDashboard(this, player);
    }

    // Getters and Setters
    public int getXCordinate() { return xCordinate; }
    public int getYCordinate() { return yCordinate; }
    public MyMap getGameMap() { return gameMap; }
    public Location getCurrentLocation() { return currentLocation; }
    public void setCurrentLocation(Location location) { this.currentLocation = location; }
    public Node getDialogueNodeByName(String name) {
        if (allDialogueNodes == null) return null;
        for (Node node : allDialogueNodes) {
            if (node != null && node.getName().equalsIgnoreCase(name)) {
                return node;
            }
        }
        return null;
    }
}
