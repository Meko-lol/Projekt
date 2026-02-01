package Commands.commandList;

import Commands.Command;
import Places.Location;
import Places.Obstacle;
import Characters.NPCs.NPC; // Import NPC
import java.util.Scanner;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.List; // Import List

public class GoToCommand extends Command {
    
    private static boolean hasEscapedPrison = false;

    @Override
    public String execute() {
        if (args.length < 2) {
            return "Where do you want to move? (e.g., 'move north')";
        }

        String direction = args[1];
        Location currentLocation = game.getCurrentLocation();
        
        if ("The Prison".equals(currentLocation.getName()) && !hasEscapedPrison) {
            startEscapePuzzle();
            return hasEscapedPrison ? "You are now free to move." : "You must solve the riddles to escape!";
        }
        
        Obstacle obstacle = currentLocation.getObstacle(direction);
        if (obstacle != null) {
            return "The way is blocked. " + obstacle.getDescription();
        }

        // --- THE FIX: Check for Guardians at the Exit ---
        Location potentialNextLocation = getPotentialNextLocation(direction);
        if (potentialNextLocation != null && "The Exit".equals(potentialNextLocation.getName())) {
            // Check if the Exit location has any hostile NPCs.
            boolean hasGuardians = false;
            if (potentialNextLocation.getNpcs() != null) {
                for (NPC npc : potentialNextLocation.getNpcs()) {
                    if (npc.isHostile()) {
                        hasGuardians = true;
                        break;
                    }
                }
            }
            
            if (hasGuardians) {
                return "The exit is blocked by powerful Guardians! You must defeat them to pass.";
            }
        }

        int oldX = game.getXCordinate();
        int oldY = game.getYCordinate();
        game.move(direction);

        if (oldX == game.getXCordinate() && oldY == game.getYCordinate()) {
            return "You can't move in that direction.";
        }
        
        Location newLocation = game.getCurrentLocation();
        if ("The Exit".equals(newLocation.getName())) {
            return "With the Guardians defeated, you pass through the exit. You win!";
        }

        return "You moved " + direction + ".";
    }

    private Location getPotentialNextLocation(String direction) {
        int x = game.getXCordinate();
        int y = game.getYCordinate();
        switch (direction.toLowerCase()) {
            case "north": y--; break;
            case "south": y++; break;
            case "west":  x--; break;
            case "east":  x++; break;
            default: return null;
        }
        return game.getGameMap().getLocation(x, y);
    }

    private void startEscapePuzzle() {
        // ... (puzzle logic remains the same)
    }

    @Override
    public boolean exit() {
        return "The Exit".equals(game.getCurrentLocation().getName());
    }
}
