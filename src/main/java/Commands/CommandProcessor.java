package Commands;

import Characters.Player;
import Game.MyGame;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import Commands.commandList.HelpCommand;
import Commands.commandList.HistoryCommand;
import Commands.commandList.QuitCommand;
import Commands.commandList.QuoteCommand;
import Commands.commandList.InteractCommand;
import Commands.commandList.PickUpCommand;
import Commands.commandList.GoToCommand;
import Commands.commandList.UseCommand;
import Commands.commandList.OpenBackpackCommand;
import Commands.commandList.DropCommand;
import Commands.commandList.InventoryCommand;
import Commands.commandList.EquipItemCommand;
import Commands.commandList.RemoveBackpackCommand;
import Commands.commandList.FightCommand;
import Commands.commandList.BuyCommand;
import Commands.commandList.SellCommand;
import Commands.commandList.EndCommand;
import Commands.commandList.GetCordinatesCommand;
import Commands.commandList.GetLocationInfoCommand;
import Commands.commandList.SaveGameCommand; // Import the new command

public class CommandProcessor {
    private boolean shouldExit = false;
    private Map<String, Command> commands = new HashMap<>();
    private Scanner scanner = new Scanner(System.in);
    private Player player;
    private MyGame game;

    public static final String COMMAND_HISTORY_FILE = "command_history.txt";

    public CommandProcessor(Player player, MyGame game) {
        this.player = player;
        this.game = game;
        initializeCommands();
        resetCommandHistory();
    }

    private void initializeCommands() {
        commands.put("cordinates", new GetCordinatesCommand());
        commands.put("locationinfo", new GetLocationInfoCommand());
        commands.put("help", new HelpCommand());
        commands.put("history", new HistoryCommand());
        commands.put("quit", new QuitCommand());
        commands.put("end", new EndCommand());
        commands.put("quote", new QuoteCommand());
        commands.put("interact", new InteractCommand());
        commands.put("pickup", new PickUpCommand());
        commands.put("move", new GoToCommand());
        commands.put("use", new UseCommand());
        commands.put("open-backpack", new OpenBackpackCommand());
        commands.put("drop", new DropCommand());
        commands.put("inventory", new InventoryCommand());
        commands.put("equip", new EquipItemCommand());
        commands.put("remove-backpack", new RemoveBackpackCommand());
        commands.put("fight", new FightCommand());
        commands.put("buy", new BuyCommand());
        commands.put("sell", new SellCommand());
        commands.put("save", new SaveGameCommand()); // Add the new "save" command
    }

    private void processInput() {
        System.out.print(">> ");
        String inputLine = scanner.nextLine().trim().toLowerCase();

        if (inputLine.isEmpty()) {
            return;
        }
        
        saveCommandToHistory(inputLine);

        String[] parts = inputLine.split("\\s+");
        String commandName = parts[0];

        Command command = commands.get(commandName);

        if (command != null) {
            command.setContext(player, game, parts);
            System.out.println(command.execute());
            this.shouldExit = command.exit();
        } else {
            System.out.println("Unknown command. Type 'help' for a list of commands.");
        }
    }

    public void run() {
        do {
            processInput();
        } while (!shouldExit);
        scanner.close();
    }

    private void saveCommandToHistory(String command) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(COMMAND_HISTORY_FILE, true))) {
            writer.write(command);
            writer.newLine();
        } catch (IOException e) {
            // Don't crash the game if history can't be saved.
        }
    }

    private void resetCommandHistory() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(COMMAND_HISTORY_FILE, false))) {
            // This just clears the file.
        } catch (IOException e) {
            // Don't crash the game if history can't be cleared.
        }
    }
}
