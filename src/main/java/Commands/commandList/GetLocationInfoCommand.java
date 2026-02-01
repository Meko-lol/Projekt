package Commands.commandList;

import Commands.Command;
import Places.Location;
import Places.Obstacle;
import Characters.NPCs.NPC;
import java.util.List;
import java.util.Map;

public class GetLocationInfoCommand extends Command {
    @Override
    public String execute() {
        Location currentLocation = game.getCurrentLocation();
        if (currentLocation == null) return "You are in a void.";

        StringBuilder info = new StringBuilder();
        info.append("--- Location: ").append(currentLocation.getName()).append(" ---\n\n");

        List<NPC> npcs = currentLocation.getNpcs();
        if (npcs != null && !npcs.isEmpty()) {
            info.append("You see the following people here:\n");
            for (NPC npc : npcs) {
                info.append("- ").append(npc.getName()).append(npc.isHostile() ? " (Hostile)\n" : "\n");
            }
        } else {
            info.append("You are alone here.\n");
        }
        info.append("\n");

        Map<String, Obstacle> obstacles = currentLocation.getObstacles();
        if (obstacles != null && !obstacles.isEmpty()) {
            info.append("Your path is blocked:\n");
            for (Map.Entry<String, Obstacle> entry : obstacles.entrySet()) {
                info.append("- To the ").append(entry.getKey()).append(": ").append(entry.getValue().getDescription()).append("\n");
            }
        } else {
            info.append("Your path is clear in all directions.\n");
        }
        
        info.append("---------------------");
        return info.toString();
    }

    @Override
    public boolean exit() {
        return false;
    }
}
