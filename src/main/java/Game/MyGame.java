package Game;

import Characters.Player;
import Commands.CommandProcessor;
import GameMap.MyMap;
import MyFileManager.MyFileManager;
import Places.Location;

public class MyGame {

    private Player player;
    private MyMap gameMap;
    private Location currentLocation;
    private int xCordinate;
    private int yCordinate;
    private CommandProcessor commandProcessor;
    private MyFileManager fileManager;

    public void startGame() {
        this.fileManager = new MyFileManager();
        this.fileManager.loadAllData();

        this.gameMap = this.fileManager.getGameMap();
        this.player = new Player("Hero", "Human", 100, 70, 5, 100, 10, 10, 10);
        
        this.xCordinate = 0;
        this.yCordinate = 0;
        this.currentLocation = this.gameMap.getLocation(this.xCordinate, this.yCordinate);

        this.commandProcessor = new CommandProcessor(this.player, this); 
        this.commandProcessor.run();
    }

    public void move(String direction) {
        int newX = this.xCordinate;
        int newY = this.yCordinate;

        switch (direction.toLowerCase()) {
            case "north":
                newY--;
                break;
            case "south":
                newY++;
                break;
            case "west":
                newX--;
                break;
            case "east":
                newX++;
                break;
            default:
                return;
        }

        Location newLocation = gameMap.getLocation(newX, newY);

        if (newLocation != null) {
            this.currentLocation = newLocation;
            this.xCordinate = newX;
            this.yCordinate = newY;
        } else {
            //TODO
        }
    }

    // --- Getters for Commands to use ---
    public int getXCordinate() {
        return xCordinate;
    }

    public int getYCordinate() {
        return yCordinate;
    }

    public MyMap getGameMap() {
        return gameMap;
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }
}
