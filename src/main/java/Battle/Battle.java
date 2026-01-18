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
        //TODO
    }

    public Character getWinner() {
        //TODO
        return null;
    }
}
