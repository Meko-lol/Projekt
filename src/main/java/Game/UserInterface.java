package Game;

import Characters.Player;
import GameMap.MyMap;
import Places.Location;

public class UserInterface {

    public String getDashboard(MyGame game, Player player) {
        String mapString = getMapAsString(game);
        String infoString = player.getPlayerInfo();
        
        String[] mapLines = mapString.split("\n");
        String[] infoLines = infoString.split("\n");

        String dashboard = "";
        int maxLines = 0;
        
        if (mapLines.length > infoLines.length) {
            maxLines = mapLines.length;
        } else {
            maxLines = infoLines.length;
        }

        for (int i = 0; i < maxLines; i++) {
            String mapLine = "";
            if (i < mapLines.length) {
                mapLine = mapLines[i];
            }

            String infoLine = "";
            if (i < infoLines.length) {
                infoLine = infoLines[i];
            }
            
            String paddedMapLine = String.format("%-30s", mapLine);
            dashboard = dashboard + paddedMapLine + " | " + infoLine + "\n";
        }
        dashboard = dashboard + "P = Your Position, [!] = Prison, [E] = Exit";
        return dashboard;
    }

    private String getMapAsString(MyGame game) {
        MyMap gameMap = game.getGameMap();
        if (gameMap == null) return "";
        
        Location[][] grid = gameMap.getGrid();
        if (grid == null) return "";
        
        String mapString = "--- World Map ---\n";

        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[y].length; x++) {
                Location loc = grid[y][x];
                
                if (x == game.getXCordinate() && y == game.getYCordinate()) {
                    mapString = mapString + "[P] ";
                } else if (loc != null && loc.getName().trim().equalsIgnoreCase("The Prison")) {
                    mapString = mapString + "[!] ";
                } else if (loc != null && loc.getName().trim().equalsIgnoreCase("The Exit")) {
                    mapString = mapString + "[E] ";
                } else if (loc != null) {
                    mapString = mapString + "[ ] ";
                } else {
                    mapString = mapString + " .  ";
                }
            }
            mapString = mapString + "\n";
        }
        mapString = mapString + "-----------------";
        return mapString;
    }
}
