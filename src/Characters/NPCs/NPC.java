package Characters.NPCs;

import Characters.Character;
import Interact.Interact;

public abstract class NPC extends Character {
    protected Interact startingInteraction;

    public NPC(String name, String race, double health, double weight, double speed, double stamina, int strength, int agility, int intelligence) {
        super(name, race, health, weight, speed, stamina, strength, agility, intelligence);
    }

    public Interact getStartingInteraction() { return null; }
    public void setStartingInteraction(Interact interaction) {}
}
