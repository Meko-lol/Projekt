package Battle;

import Characters.Character;

public class Battle {
    private Character player;
    private Character opponent;
    private boolean isBattleOver;

    public Battle(Character player, Character opponent) {
        this.player = player;
        this.opponent = opponent;
        this.isBattleOver = false;
    }

    public void startBattle() {
        // Main battle loop logic will go here.
        // For example, characters take turns attacking until one's health is <= 0.
    }

    public Character getWinner() {
        // Logic to determine the winner after the battle is over.
        return null;
    }
}
