package Commands.commandList;

import Commands.Command;
import GameMap.MyMap;
import Places.Location;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GoToCommand extends Command {
    @Override
    public String execute() {
        MyMap gameMap = game.getGameMap();
        int x = game.getXCordinate();
        int y = game.getYCordinate();

        List<String> availableDirections = new ArrayList<>();
        if (gameMap.getLocation(x, y - 1) != null) availableDirections.add("north");
        if (gameMap.getLocation(x, y + 1) != null) availableDirections.add("south");
        if (gameMap.getLocation(x - 1, y) != null) availableDirections.add("west");
        if (gameMap.getLocation(x + 1, y) != null) availableDirections.add("east");

        if (availableDirections.isEmpty()) {
            return "You are trapped! There is nowhere to go.";
        }

        System.out.println("Where do you want to move? Available directions: " + String.join(", ", availableDirections));
        System.out.print(">> ");
        Scanner scanner = new Scanner(System.in);
        String direction = scanner.nextLine().trim().toLowerCase();

        if (availableDirections.contains(direction)) {
            game.move(direction);
            System.out.println(game.getXCordinate() + " " + game.getYCordinate());
            return "You move " + direction + ".";
        } else {
            return "That is not a valid direction.";
        }
    }

    @Override
    public boolean exit() {
        return false;
    }
}
