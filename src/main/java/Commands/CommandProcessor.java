package Commands;

import Characters.Player;
import Game.MyGame;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

public class CommandProcessor {
    private boolean shouldExit = false;
    private Map<String, Command> commands;
    private Scanner scanner = new Scanner(System.in);
    private Player player;
    private MyGame game;

    public static final String COMMAND_HISTORY_FILE = "command_history.txt";

    public CommandProcessor(Player player, MyGame game) {
        this.player = player;
        this.game = game;
        this.commands = CommandRegistry.getCommands();
        resetCommandHistory();
    }

    /**
     * Clears the terminal by printing many newlines.
     * This is more reliable than ANSI codes in some IDE consoles.
     */
    private void clearTerminal() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

    private String processInput() {
        System.out.print(">> ");
        String inputLine = scanner.nextLine().trim().toLowerCase();

        if (inputLine.isEmpty()) {
            return "";
        }
        
        saveCommandToHistory(inputLine);

        String[] parts = inputLine.split("\\s+");
        String commandName = parts[0];
        Command command = commands.get(commandName);

        if (command != null) {
            command.setContext(player, game, parts);
            String result = command.execute();
            this.shouldExit = command.exit();
            return result;
        } else {
            return "Unknown command. Type 'help' for a list of commands.";
        }
    }

    public void run() {
        String commandResult = "";
        do {
            clearTerminal();
            
            if (shouldExit && commandResult.contains("Congratulations")) {
                System.out.println("******************************************");
                System.out.println("*             VICTORY!                   *");
                System.out.println("******************************************");
            }

            if (commandResult != null && !commandResult.isEmpty()) {
                System.out.println(commandResult);
                System.out.println();
            }
            
            System.out.println(game.getDashboard());
            
            if (!shouldExit) {
                commandResult = processInput();
            }

        } while (!shouldExit);
        
        scanner.close();
        System.out.println("Thank you for playing!");
    }

    private void saveCommandToHistory(String command) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(COMMAND_HISTORY_FILE, true))) {
            writer.write(command);
            writer.newLine();
        } catch (IOException e) {}
    }

    private void resetCommandHistory() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(COMMAND_HISTORY_FILE, false))) {} 
        catch (IOException e) {}
    }
}
