package Characters;

public abstract class Character {
    protected String name;
    protected String race;
    protected double health;
    protected double weight;
    protected double speed;
    protected double stamina;
    protected int strength;
    protected int agility;
    protected int intelligence;

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

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getRace() { return race; }
    public void setRace(String race) { this.race = race; }
    public double getHealth() { return health; }
    public void setHealth(double health) { this.health = health; }
    public double getWeight() { return weight; }
    public void setWeight(double weight) { this.weight = weight; }
    public double getSpeed() { return speed; }
    public void setSpeed(double speed) { this.speed = speed; }
    public double getStamina() { return stamina; }
    public void setStamina(double stamina) { this.stamina = stamina; }
    public int getStrength() { return strength; }
    public void setStrength(int strength) { this.strength = strength; }
    public int getAgility() { return agility; }
    public void setAgility(int agility) { this.agility = agility; }
    public int getIntelligence() { return intelligence; }
    public void setIntelligence(int intelligence) { this.intelligence = intelligence; }
}
