package Items;

public abstract class Item {
    protected String name;
    protected double weight;
    protected double durability;
    protected String description;

    public Item(String name, double weight, double durability, String description) {
        this.name = name;
        this.weight = weight;
        this.durability = durability;
        this.description = description;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public double getWeight() { return weight; }
    public void setWeight(double weight) { this.weight = weight; }
    public double getDurability() { return durability; }
    public void setDurability(double durability) { this.durability = durability; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
