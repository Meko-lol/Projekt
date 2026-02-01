package Game;

import Characters.Player;
import Commands.CommandProcessor;
import GameMap.MyMap;
import MyFileManager.MyFileManager;
import Places.Location;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Scanner;

public class MyGame {
    @JsonIgnore private MyFileManager fileManager = new MyFileManager();

    // --- Game State (Saved) ---
    public Player player;
    public MyMap gameMap;
    public int xCordinate;
    public int yCordinate;

    // --- Utilities (Not Saved) ---
    @JsonIgnore private Location currentLocation;
    @JsonIgnore private CommandProcessor commandProcessor;

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

        gameInstance.initializeAfterLoad();
        
        gameInstance.commandProcessor = new CommandProcessor(gameInstance.player, gameInstance);
        gameInstance.commandProcessor.run();
    }

    private MyGame createNewGame() {
        System.out.println("Creating a new game world...");
        MyGame newGame = new MyGame();
        newGame.fileManager.loadAllData();

        newGame.gameMap = new MyMap(8, 8);
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                newGame.gameMap.setLocation(x, y, new Location("An empty field", null, null, null));
            }
        }
        
        newGame.player = new Player("Hero", "Human", 100, 70, 5, 100, 10, 10, 10);
        
        newGame.xCordinate = 0;
        newGame.yCordinate = 0;
        
        return newGame;
    }

    private MyGame loadGame() {
        System.out.println("Loading game from Source/game.json...");
        MyGame loadedGame = fileManager.loadFromFile("Source/game.json", MyGame.class);
        if (loadedGame != null) {
            System.out.println("Game loaded successfully.");
            return loadedGame;
        } else {
            System.out.println("Failed to load game. Starting a new game.");
            return createNewGame();
        }
    }

    public void saveGame() {
        fileManager.saveToFile("Source/game.json", this);
        System.out.println("Game saved.");
    }
    
    public void initializeAfterLoad() {
        this.fileManager = new MyFileManager();
        this.fileManager.loadAllData();
        this.currentLocation = this.gameMap.getLocation(this.xCordinate, this.yCordinate);
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
            System.out.println("You moved " + direction + ". You are now at: " + newLocation.getName());
        } else {
            System.out.println("You can't move that way.");
        }
    }

    // --- Getters for Commands ---
    public int getXCordinate() { return xCordinate; }
    public int getYCordinate() { return yCordinate; }
    public MyMap getGameMap() { return gameMap; }
    public Location getCurrentLocation() { return currentLocation; }
    public MyFileManager getFileManager() { return fileManager; } // Added getter
}
