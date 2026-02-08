package Commands.commandList;

import Battle.Battle;
import Commands.Command;
import Places.Location;
import Places.Obstacle;
import Characters.NPCs.NPC;
import Items.Item;
import Quest.Quest;
import Quest.KillingQuest;
import ending.prison.Prison; // Import the new Prison class
import java.util.List;
import java.util.Random;

public class GoToCommand extends Command {
    
    private static boolean hasEscapedPrison = false;
    private Random random = new Random();

    @Override
    public String execute() {
        if (args.length < 2) {
            return "Where do you want to move? (e.g., 'move north')";
        }

        String direction = args[1];
        Location currentLocation = game.getCurrentLocation();
        
        if (currentLocation.getNpcs() != null) {
            for (NPC npc : currentLocation.getNpcs()) {
                if (npc.isHostile()) {
                    return "You cannot flee! There are enemies here. You must fight!";
                }
            }
        }
        
        // THE FIX: Use the dedicated Prison class logic
        if ("The Prison".equals(currentLocation.getName()) && !hasEscapedPrison) {
            Prison prison = new Prison();
            boolean success = prison.attemptToBreakOut();
            if (success) {
                hasEscapedPrison = true;
                return "You are now free to move.";
            } else {
                return "You must solve the riddles to escape!";
            }
        }
        
        Obstacle obstacle = currentLocation.getObstacle(direction);
        if (obstacle != null) {
            return "The way is blocked. " + obstacle.getDescription();
        }

        int oldX = game.getXCordinate();
        int oldY = game.getYCordinate();
        game.move(direction);

        if (oldX == game.getXCordinate() && oldY == game.getYCordinate()) {
            return "You can't move in that direction.";
        }
        
        Location newLocation = game.getCurrentLocation();
        
        if ("The Exit".equals(newLocation.getName())) {
            return "You have entered the final chamber. The exit is guarded!";
        }

        if (newLocation.getNpcs() != null) {
            for (NPC npc : newLocation.getNpcs()) {
                if (npc.isHostile()) {
                    if (random.nextInt(100) < 40) {
                        return triggerAmbush(npc);
                    }
                }
            }
        }

        return "You moved " + direction + ".";
    }

    private String triggerAmbush(NPC enemy) {
        System.out.println("\n!!! AMBUSH !!!");
        System.out.println("As you enter the area, a " + enemy.getName() + " jumps out and attacks you!");
        
        Battle battle = new Battle(player, enemy);
        boolean playerWasDefeated = battle.start();

        if (playerWasDefeated) {
            game.handlePlayerDefeat();
            return "";
        } else {
            game.getCurrentLocation().removeNpc(enemy);
            
            List<Item> loot = enemy.getLoot();
            if (loot != null && !loot.isEmpty()) {
                for (Item item : loot) {
                    game.getCurrentLocation().addItem(item);
                }
                System.out.println("The " + enemy.getName() + " dropped its loot on the ground!");
            }
            
            for (Quest quest : player.getActiveQuests()) {
                if (quest instanceof KillingQuest) {
                    KillingQuest kQuest = (KillingQuest) quest;
                    if (kQuest.isTarget(enemy.getName())) {
                        kQuest.incrementKills();
                        System.out.println("Quest progress: " + kQuest.getCurrentKills() + "/" + kQuest.getRequiredKills() + " " + kQuest.getTargetName() + "s killed.");
                    }
                }
            }
            
            int goldReward = 75;
            player.addMoney(goldReward);
            
            return "You survived the ambush and defeated the " + enemy.getName() + "! (Received " + goldReward + " gold)";
        }
    }

    @Override
    public boolean exit() {
        return false;
    }
}
