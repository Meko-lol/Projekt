package Game;

import Characters.Player;
import GameMap.MyMap;
import Items.Item;
import Places.Location;
import java.util.Random;

public class GameEventManager {

    public void handlePlayerDefeat(MyGame game) {
        Player player = game.player;
        MyMap gameMap = game.gameMap;

        System.out.println("\n*** YOU HAVE BEEN DEFEATED ***");
        System.out.println("You wake up in a cold, damp cell...");
        
        player.setHealth(20);
        
        // Drop equipped weapon
        Item weapon = player.getEquippedWeapon();
        if (weapon != null) {
            player.setEquippedWeapon(null);
            dropItemRandomly(weapon, gameMap);
        }
        
        // Move to Prison
        Location prison = findLocationByName("The Prison", gameMap);
        if (prison != null) {
            game.xCordinate = prison.getX();
            game.yCordinate = prison.getY();
            // We can't set currentLocation directly on game as it's private, 
            // but MyGame.move() or a setter would handle this. 
            // For now, we assume MyGame will update its state based on coordinates or we add a setter.
            game.setCurrentLocation(prison); 
        } else {
            // Fallback
            game.xCordinate = 0;
            game.yCordinate = 0;
            game.setCurrentLocation(gameMap.getLocation(0, 0));
        }
    }

    private void dropItemRandomly(Item item, MyMap gameMap) {
        int dropX, dropY;
        Random random = new Random();
        do {
            dropX = random.nextInt(gameMap.getGrid()[0].length);
            dropY = random.nextInt(gameMap.getGrid().length);
        } while (gameMap.getLocation(dropX, dropY) == null);
        
        gameMap.getLocation(dropX, dropY).addItem(item);
        System.out.println("Your " + item.getName() + " was lost!");
    }

    private Location findLocationByName(String name, MyMap gameMap) {
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
}
