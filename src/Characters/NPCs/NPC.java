package Characters.NPCs;

import Characters.Character;

public abstract class NPC extends Character {

    public NPC(String name, String race, double health, double weight, double speed, double stamina, int strength, int agility, int intelligence) {
        super(name, race, health, weight, speed, stamina, strength, agility, intelligence);
    }

}
