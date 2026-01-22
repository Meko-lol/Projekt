package Places;

import Characters.Character;
import Characters.NPCs.Enemy;
import java.util.Map;

public class NaturePlace extends Place {
    private int forestDensity;
    private Enemy[] enemies;
    private Character[] animals;

    public NaturePlace(String name, String[] obstacles, Characters.NPCs.NPC[] npcs, Map<String, Integer> resources, int forestDensity, Enemy[] enemies, Character[] animals) {
        super(name, obstacles, npcs, resources);
        this.forestDensity = forestDensity;
        this.enemies = enemies;
        this.animals = animals;
    }

    public int getForestDensity() {
        return forestDensity;
    }

    public void setForestDensity(int forestDensity) {
        this.forestDensity = forestDensity;
    }

    public Enemy[] getEnemies() {
        return enemies;
    }

    public void setEnemies(Enemy[] enemies) {
        this.enemies = enemies;
    }

    public Character[] getAnimals() {
        return animals;
    }

    public void setAnimals(Character[] animals) {
        this.animals = animals;
    }
}
