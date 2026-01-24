package Commands.commandList;

import Commands.Command;
import Places.Location;
import Characters.NPCs.NPC;
import java.util.Arrays;

public class GetLocationInfoCommand extends Command {
    @Override
    public String execute() {
        Location currentLocation = game.getCurrentLocation();

        if (currentLocation == null) {
            return "You are in a void. There is nothing here.";
        }

        StringBuilder info = new StringBuilder();
        info.append("You are at: ").append(currentLocation.getName()).append("\n");

        // Display Obstacles
        String[] obstacles = currentLocation.getObstacles();
        if (obstacles != null && obstacles.length > 0) {
            info.append("Obstacles: ").append(String.join(", ", obstacles)).append("\n");
        }

        // Display NPCs
        NPC[] npcs = currentLocation.getNpcs();
        if (npcs != null && npcs.length > 0) {
            info.append("People here: ");
            String[] npcNames = Arrays.stream(npcs).map(NPC::getName).toArray(String[]::new);
            info.append(String.join(", ", npcNames)).append("\n");
        }

        System.out.println(info.toString());
        return info.toString();
    }

    @Override
    public boolean exit() {
        return false;
    }
}
