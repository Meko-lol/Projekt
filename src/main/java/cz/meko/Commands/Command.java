package cz.meko.Commands;

import cz.meko.Characters.Player;
import cz.meko.Game.MyGame; // Import MyGame

public abstract class Command {

    protected Player player;
    protected MyGame game; // Add field for the game instance
    protected String[] args;

    // Update setContext to accept the MyGame instance
    public void setContext(Player player, MyGame game, String[] args) {
        this.player = player;
        this.game = game;
        this.args = args;
    }

    public abstract String execute();

    public abstract boolean exit();
}
