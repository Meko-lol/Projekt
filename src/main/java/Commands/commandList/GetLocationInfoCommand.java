package Commands.commandList;

import Commands.Command;
import Places.Location;
import Places.Obstacle;
import Characters.NPCs.NPC;
import Items.Item; // Import Item
import java.util.List;
import java.util.Map;

public class GetLocationInfoCommand extends Command {
    @Override
    public String execute() {
        Location currentLocation = game.getCurrentLocation();
        if (currentLocation == null) return "You are in a void.";

        StringBuilder info = new StringBuilder();
        info.append("--- Location: ").append(currentLocation.getName()).append(" ---\n\n");

        // --- Display NPCs ---
        List<NPC> npcs = currentLocation.getNpcs();
        if (npcs != null && !npcs.isEmpty()) {
            info.append("You see the following people here:\n");
            for (NPC npc : npcs) {
                info.append("- ").append(npc.getName());
                if (npc.isHostile()) {
                    info.append(" (Hostile)\n");
                } else {
                    info.append("\n");
                }
            }
        } else {
            info.append("You are alone here.\n");
        }
        info.append("\n");

        // --- THE FIX: Display Items on Ground ---
        List<Item> items = currentLocation.getItemsOnGround();
        if (items != null && !items.isEmpty()) {
            info.append("Items on the ground:\n");
            for (Item item : items) {
                info.append("- ").append(item.getName()).append("\n");
            }
        } else {
            info.append("There are no items here.\n");
        }
        info.append("\n");

        // --- Display Obstacles ---
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
