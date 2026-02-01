package Commands.commandList;

import Commands.Command;
import Places.Location;
import Characters.NPCs.NPC;
import Interact.InteractHandler;
import Interact.Node;

public class InteractCommand extends Command {
    @Override
    public String execute() {
        if (args.length < 2) {
            return "Who do you want to interact with?";
        }
        String npcName = args[1];
        Location currentLocation = game.getCurrentLocation();
        
        if (currentLocation.getNpcs() == null) {
            return "There is no one here to interact with.";
        }

        NPC targetNpc = null;
        for (NPC npc : currentLocation.getNpcs()) {
            if (npc.getName().equalsIgnoreCase(npcName)) {
                targetNpc = npc;
                break;
            }
        }

        if (targetNpc == null) {
            return "There is no one here by that name.";
        }

        String startingNodeName = targetNpc.getDialogueNodeName();
        if (startingNodeName == null) {
            return targetNpc.getName() + " has nothing to say.";
        }

        // Find the starting node from the file manager
        Node startingNode = game.getFileManager().getInteractByName(startingNodeName);

        if (startingNode == null) {
            return "There seems to be an issue with this character's dialogue.";
        }
        
        // Create a handler and start the interaction
        InteractHandler handler = new InteractHandler();
        handler.startInteraction(startingNode, game.getFileManager()); // Pass the file manager

        return "You finish your conversation.";
    }

    @Override
    public boolean exit() {
        return false;
    }
}
