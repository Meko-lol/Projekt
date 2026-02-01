package Game;

import Characters.Player;
import GameMap.MyMap;
import Places.Location;

public class UserInterface {

    public String getDashboard(MyGame game, Player player) {
        String[] mapLines = getMapAsString(game).split("\n");
        String[] infoLines = player.getPlayerInfo().split("\n");

        StringBuilder dashboard = new StringBuilder();
        int maxLines = Math.max(mapLines.length, infoLines.length);

        for (int i = 0; i < maxLines; i++) {
            String mapLine = (i < mapLines.length) ? mapLines[i] : "";
            String infoLine = (i < infoLines.length) ? infoLines[i] : "";
            
            dashboard.append(String.format("%-30s", mapLine));
            dashboard.append(" | ");
            dashboard.append(infoLine);
            dashboard.append("\n");
        }
        dashboard.append("P = Your Position, [!] = Prison, [E] = Exit");
        return dashboard.toString();
    }

    private String getMapAsString(MyGame game) {
        MyMap gameMap = game.getGameMap();
        if (gameMap == null || gameMap.getGrid() == null) return "";
        
        Location[][] grid = gameMap.getGrid();
        StringBuilder mapString = new StringBuilder();
        mapString.append("--- World Map ---\n");

        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[y].length; x++) {
                Location loc = grid[y][x];
                if (x == game.getXCordinate() && y == game.getYCordinate()) mapString.append("[P] ");
                else if (loc != null && "The Prison".equals(loc.getName())) mapString.append("[!] ");
                else if (loc != null && "The Exit".equals(loc.getName())) mapString.append("[E] ");
                else if (loc != null) mapString.append("[ ] ");
                else mapString.append(" .  ");
            }
            mapString.append("\n");
        }
        mapString.append("-----------------");
        return mapString.toString();
    }
}
