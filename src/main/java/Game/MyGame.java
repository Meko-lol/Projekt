package Game;

import Battle.Battle;
import Characters.NPCs.NPC;
import Characters.Player;
import Commands.CommandProcessor;
import GameMap.MyMap;
import Interact.InteractHandler;
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
import ending.boulder.Boulder;
import ending.prison.Prison;
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
    
    public GameSettings settings;

    @JsonIgnore private Location currentLocation;
    @JsonIgnore private CommandProcessor commandProcessor;
    @JsonIgnore private List<Node> allDialogueNodes;
    @JsonIgnore private UserInterface ui;
    @JsonIgnore private GameEventManager eventManager;
    @JsonIgnore private TestManager testManager;

    public MyGame() {
        this.ui = new UserInterface();
        this.eventManager = new GameEventManager();
        this.settings = new GameSettings();
        this.testManager = new TestManager();
    }

    public void startGame() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n=== ESCAPE FROM THE DWARVEN RUINS ===");
            System.out.println("1. Start New Game");
            System.out.println("2. Load Game");
            System.out.println("3. Settings");
            System.out.println("4. Test Functions");
            System.out.println("5. Exit");
            System.out.print(">> ");

            String choice = scanner.nextLine().trim();

            if (choice.equals("1")) {
                MyGame gameInstance = createNewGame();
                gameInstance.initialize();
                gameInstance.commandProcessor = new CommandProcessor(gameInstance.player, gameInstance);
                gameInstance.commandProcessor.run();
                break;
            } else if (choice.equals("2")) {
                MyGame gameInstance = loadGame();
                if (gameInstance != null) {
                    gameInstance.initialize();
                    gameInstance.commandProcessor = new CommandProcessor(gameInstance.player, gameInstance);
                    gameInstance.commandProcessor.run();
                    break;
                }
            } else if (choice.equals("3")) {
                openSettingsMenu(scanner);
            } else if (choice.equals("4")) {
                openTestMenu(scanner);
            } else if (choice.equals("5")) {
                System.out.println("Goodbye!");
                System.exit(0);
            } else {
                System.out.println("Invalid choice.");
            }
        }
    }

    private void openTestMenu(Scanner scanner) {
        while (true) {
            System.out.println("\n--- Test Functions ---");
            System.out.println("1. Test Battle");
            System.out.println("2. Test Prison Escape");
            System.out.println("3. Test NPC Interaction");
            System.out.println("4. Test Win Screen");
            System.out.println("5. Test Boulder Breaking");
            System.out.println("6. Test Equipment");
            System.out.println("7. Test All Commands");
            System.out.println("8. Back");
            System.out.print(">> ");

            String choice = scanner.nextLine().trim();

            if (choice.equals("1")) {
                testManager.testBattle();
                waitForInput(scanner);
            } else if (choice.equals("2")) {
                testManager.testPrison();
                waitForInput(scanner);
            } else if (choice.equals("3")) {
                testManager.testNPC();
                waitForInput(scanner);
            } else if (choice.equals("4")) {
                testManager.testWin();
                waitForInput(scanner);
            } else if (choice.equals("5")) {
                testManager.testBoulder();
                waitForInput(scanner);
            } else if (choice.equals("6")) {
                testManager.testEquipment();
                waitForInput(scanner);
            } else if (choice.equals("7")) {
                testManager.runAllTests();
                waitForInput(scanner);
            } else if (choice.equals("8")) {
                break;
            } else {
                System.out.println("Invalid choice.");
            }
        }
    }

    // THE FIX: Use the Scanner to wait for input.
    private void waitForInput(Scanner scanner) {
        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
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

            String choice = scanner.nextLine().trim();

            if (choice.equals("1")) {
                System.out.print("Enter new size (e.g., 10 for 10x10): ");
                try {
                    int size = Integer.parseInt(scanner.nextLine().trim());
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
                String diff = scanner.nextLine().trim();
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
            int val = Integer.parseInt(scanner.nextLine().trim());
            return Math.max(0, Math.min(100, val));
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private MyGame createNewGame() {
        System.out.println("You wake up in a cold, damp cell. Your head throbs, and you remember nothing.");
        System.out.println("The air smells of ancient stone and decay. You must find a way out.");
        
        MyGame newGame = new MyGame();
        newGame.settings = this.settings;
        newGame.gameMap = MapGenerator.generateMap(this.settings);
        
        newGame.player = new Player("Survivor", "Human", 100, 70, 5, 100, 10, 10, 10);
        newGame.xCordinate = 0;
        newGame.yCordinate = 0;
        
        newGame.player.getInventory().addItem(new Item("Rusty Iron Bar", "weapon", 5.0, 30, "Better than nothing."));
        newGame.player.getInventory().addItem(new Item("Rope", "tool", 2.0, 10, "A sturdy rope."));
        newGame.player.getInventory().addItem(new Item("Pickaxe", "tool", 5.0, 50, "Essential for mining."));
        newGame.player.getInventory().addItem(new Backpack("Scavenger's Pack", 1.0, 200, "A large bag.", 50));
        
        return newGame;
    }

    private MyGame loadGame() {
        MyGame loadedGame = FileManager.loadGame();
        if (loadedGame != null) {
            System.out.println("Game loaded successfully.");
            return loadedGame;
        } else {
            System.out.println("Failed to load game. Please start a new game.");
            return null;
        }
    }

    public void saveGame() {
        FileManager.saveGame(this);
    }
    
    public void initialize() {
        this.ui = new UserInterface();
        this.eventManager = new GameEventManager();
        this.allDialogueNodes = FileManager.loadDialogues();
        this.testManager = new TestManager();
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
