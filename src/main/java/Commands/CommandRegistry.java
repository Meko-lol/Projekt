package Commands;

import Commands.commandList.*;
import java.util.HashMap;
import java.util.Map;

public class CommandRegistry {

    public static Map<String, Command> getCommands() {
        Map<String, Command> commands = new HashMap<>();
        
        // Core Gameplay
        commands.put("move", new GoToCommand());
        commands.put("look", new GetLocationInfoCommand());
        commands.put("interact", new InteractCommand());
        commands.put("fight", new FightCommand());
        commands.put("quests", new QuestsCommand());
        
        // Inventory & Items
        commands.put("inventory", new InventoryCommand());
        commands.put("pickup", new PickUpCommand());
        commands.put("drop", new DropCommand());
        commands.put("equip", new EquipItemCommand());
        commands.put("use", new UseCommand());
        commands.put("buy", new BuyCommand());
        
        // System
        commands.put("help", new HelpCommand());
        commands.put("quit", new QuitCommand());
        commands.put("save", new SaveGameCommand());
        commands.put("win", new WinCommand());
        
        // Developer Tools
        commands.put("dev", new DevCommand());
        
        return commands;
    }
}
