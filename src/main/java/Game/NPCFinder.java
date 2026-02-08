package Game;

import Characters.NPCs.NPC;
import java.util.List;

public class NPCFinder {

    /**
     * Finds an NPC in a list using partial name matching.
     * @param npcs The list of NPCs to search.
     * @param searchText The text to search for.
     * @return The first matching NPC, or null if not found.
     */
    public static NPC findNPC(List<NPC> npcs, String searchText) {
        if (npcs == null || npcs.isEmpty()) return null;

        String lowerSearch = searchText.toLowerCase();
        for (NPC npc : npcs) {
            if (npc.getName().toLowerCase().contains(lowerSearch)) {
                return npc;
            }
        }
        return null;
    }
}
