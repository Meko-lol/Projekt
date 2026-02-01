package Commands.commandList;

import Commands.Command;
import Places.Location;
import Characters.NPCs.NPC;

public class WinCommand extends Command {
    @Override
    public String execute() {
        Location currentLocation = game.getCurrentLocation();

        // 1. Check if the player is at the "The Exit" location.
        if (!"The Exit".equals(currentLocation.getName())) {
            return "You cannot win here. You must find the exit.";
        }

        // 2. Check if there are any hostile NPCs (Guardians) still at the location.
        if (currentLocation.getNpcs() != null) {
            for (NPC npc : currentLocation.getNpcs()) {
                if (npc.isHostile()) {
                    return "You cannot win yet! The exit is still guarded.";
                }
            }
        }

        // 3. If both conditions are met, the player wins!
        return "With the Guardians defeated, you step through the final gate and into the light. You have escaped! Congratulations!";
    }

    @Override
    public boolean exit() {
        // This command should cause the game to exit if the player has won.
        Location currentLocation = game.getCurrentLocation();
        if ("The Exit".equals(currentLocation.getName())) {
            // Check if there are any guardians left.
            for (NPC npc : currentLocation.getNpcs()) {
                if (npc.isHostile()) {
                    return false; // Guardians are still here, don't exit.
                }
            }
            return true; // No guardians left, exit the game.
        }
        return false;
    }
}
