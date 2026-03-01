package cz.meko.Characters;

/**
 * Abstract base class for all characters in the game, including the player and NPCs.
 * Defines common attributes like health, strength, etc.
 * 
 * @author Jan Petrak
 */
public abstract class Character {
    private String name;
    private String race;
    private double health;
    private double weight;
    private double speed;
    private double stamina;
    private int strength;
    private int agility;
    private int intelligence;

    public Character() {}

    public Character(String name, String race, double health, double weight, double speed, double stamina, int strength, int agility, int intelligence) {
        this.name = name;
        this.race = race;
        this.health = health;
        this.weight = weight;
        this.speed = speed;
        this.stamina = stamina;
        this.strength = strength;
        this.agility = agility;
        this.intelligence = intelligence;
    }

    // Getters
    public String getName() { return name; }
    public String getRace() { return race; }
    public double getHealth() { return health; }
    public double getWeight() { return weight; }
    public double getSpeed() { return speed; }
    public double getStamina() { return stamina; }
    public int getStrength() { return strength; }
    public int getAgility() { return agility; }
    public int getIntelligence() { return intelligence; }

    // Setters
    public void setName(String name) { this.name = name; }
    public void setRace(String race) { this.race = race; }
    public void setHealth(double health) { this.health = health; }
    public void setWeight(double weight) { this.weight = weight; }
    public void setSpeed(double speed) { this.speed = speed; }
    public void setStamina(double stamina) { this.stamina = stamina; }
    public void setStrength(int strength) { this.strength = strength; }
    public void setAgility(int agility) { this.agility = agility; }
    public void setIntelligence(int intelligence) { this.intelligence = intelligence; }
}
