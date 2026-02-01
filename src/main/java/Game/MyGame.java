package Game;

import Characters.Player;
import Commands.CommandProcessor;
import GameMap.MyMap;
import Interact.Node;
import Items.Item;
import MyGenerator.MapGenerator;
import Places.Location;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class MyGame {

    public Player player;
    public MyMap gameMap;
    
    @JsonAlias("xcordinate")
    public int xCordinate;
    
    @JsonAlias("ycordinate")
    public int yCordinate;

    @JsonIgnore private Location currentLocation;
    @JsonIgnore private CommandProcessor commandProcessor;
    @JsonIgnore private List<Node> allDialogueNodes;

    public MyGame() {}

    public void startGame() {
        System.out.println("Welcome!");
        System.out.println("1. Start New Game");
        System.out.println("2. Load Game");
        System.out.print(">> ");

        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine();

        MyGame gameInstance;
        if (choice.equals("2")) {
            gameInstance = loadGame();
        } else {
            gameInstance = createNewGame();
        }

        gameInstance.initialize();
        
        gameInstance.commandProcessor = new CommandProcessor(gameInstance.player, gameInstance);
        gameInstance.commandProcessor.run();
    }

    private MyGame createNewGame() {
        System.out.println("Creating a new game world...");
        MyGame newGame = new MyGame();
        
        newGame.gameMap = MapGenerator.generateMap();
        
        newGame.player = new Player("Hero", "Human", 100, 70, 5, 100, 10, 10, 10);
        newGame.xCordinate = 0;
        newGame.yCordinate = 0;
        
        return newGame;
    }

    private MyGame loadGame() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(new File("Source/game.json"), MyGame.class);
        } catch (Exception e) {
            System.out.println("Failed to load game: " + e.getMessage() + ". Starting a new game.");
            return createNewGame();
        }
    }

    public void saveGame() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            objectMapper.writeValue(new File("Source/game.json"), this);
            System.out.println("Game saved.");
        } catch (Exception e) {
            System.out.println("Error saving game: " + e.getMessage());
        }
    }
    
    public void initialize() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonContent = new String(Files.readAllBytes(Paths.get("Source/Interacts.json")));
            this.allDialogueNodes = new ArrayList<>(Arrays.asList(objectMapper.readValue(jsonContent, Node[].class)));
        } catch (Exception e) {
            this.allDialogueNodes = new ArrayList<>();
        }
        
        this.currentLocation = this.gameMap.getLocation(this.xCordinate, this.yCordinate);
    }

    public void handlePlayerDefeat() {
        System.out.println("\nYou collapse from your wounds...");
        player.setHealth(20);
        Item weapon = player.getEquippedWeapon();
        if (weapon != null) {
            player.setEquippedWeapon(null);
            int dropX, dropY;
            Random random = new Random();
            do {
                dropX = random.nextInt(gameMap.getGrid()[0].length);
                dropY = random.nextInt(gameMap.getGrid().length);
            } while (gameMap.getLocation(dropX, dropY) == null);
            gameMap.getLocation(dropX, dropY).addItem(weapon);
            System.out.println("Your " + weapon.getName() + " was dropped somewhere on the map!");
        }
        Location prison = findLocationByName("The Prison");
        if (prison != null) {
            this.xCordinate = prison.getX();
            this.yCordinate = prison.getY();
            this.currentLocation = prison;
            System.out.println("You wake up in a prison cell.\n");
        } else {
            System.out.println("You wake up, dazed and confused, back where you started.");
            this.xCordinate = 0;
            this.yCordinate = 0;
            this.currentLocation = gameMap.getLocation(0, 0);
        }
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

    private Location findLocationByName(String name) {
        if (gameMap == null || gameMap.getGrid() == null) return null;
        for (Location[] row : gameMap.getGrid()) {
            for (Location loc : row) {
                if (loc != null && name.equals(loc.getName())) {
                    return loc;
                }
            }
        }
        return null;
    }

    @JsonIgnore
    private String getMapAsString() {
        if (gameMap == null || gameMap.getGrid() == null) return "";
        Location[][] grid = gameMap.getGrid();
        StringBuilder mapString = new StringBuilder();
        mapString.append("--- World Map ---\n");
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[y].length; x++) {
                Location loc = grid[y][x];
                if (x == this.xCordinate && y == this.yCordinate) mapString.append("[P] ");
                else if (loc != null && "The Prison".equals(loc.getName())) mapString.append("[!] ");
                else if (loc != null && "The Exit".equals(loc.getName())) mapString.append("[E] ");
                else if (loc != null) mapString.append("[ ] ");
                else mapString.append(" .  ");
            }
            mapString.append("\n");
        }
        mapString.append("-----------------");
        return mapString.toString();
    }

    @JsonIgnore
    public String getDashboard() {
        String[] mapLines = getMapAsString().split("\n");
        String[] infoLines = player.getPlayerInfo().split("\n");
        StringBuilder dashboard = new StringBuilder();
        int maxLines = Math.max(mapLines.length, infoLines.length);
        for (int i = 0; i < maxLines; i++) {
            String mapLine = (i < mapLines.length) ? mapLines[i] : "";
            String infoLine = (i < infoLines.length) ? infoLines[i] : "";
            dashboard.append(String.format("%-30s", mapLine));
            dashboard.append(" | ");
            dashboard.append(infoLine);
            dashboard.append("\n");
        }
        dashboard.append("P = Your Position, [!] = Prison, [E] = Exit");
        return dashboard.toString();
    }

    // --- Getters for Commands ---
    public int getXCordinate() { return xCordinate; }
    public int getYCordinate() { return yCordinate; }
    public MyMap getGameMap() { return gameMap; }
    public Location getCurrentLocation() { return currentLocation; }
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
