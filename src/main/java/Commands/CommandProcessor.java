package Commands;

import Characters.Player;
import Game.FileManager; // Use FileManager
import Game.MyGame;
import java.util.Map;
import java.util.Scanner;

public class CommandProcessor {
    private boolean shouldExit = false;
    private Map<String, Command> commands;
    private Scanner scanner = new Scanner(System.in);
    private Player player;
    private MyGame game;

    public CommandProcessor(Player player, MyGame game) {
        this.player = player;
        this.game = game;
        this.commands = CommandRegistry.getCommands();
        FileManager.resetCommandHistory();
    }

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
        
        FileManager.saveCommandToHistory(inputLine);

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
}
