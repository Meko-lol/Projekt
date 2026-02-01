package Characters;

import Inventory.Inventory;
import Items.Item;
import Items.Weapons.CloseRangeWeapon;
import Quest.Quest;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.List;
import Items.EquippableItems.EquippableItem;

public class Player extends Character {

    public Inventory inventory = new Inventory();
    public List<Quest> activeQuests = new ArrayList<>();
    private int money;

    @JsonIgnore private EquippableItem headSlot;
    @JsonIgnore private EquippableItem chestSlot;
    @JsonIgnore private EquippableItem legSlot;
    @JsonIgnore private EquippableItem footSlot;
    @JsonIgnore private EquippableItem backpackSlot;
    @JsonIgnore private CloseRangeWeapon equippedWeapon;

    public Player() {
        super();
        this.money = 50;
    }

    public Player(String name, String race, double health, double weight, double speed, double stamina, int strength, int agility, int intelligence) {
        super(name, race, health, weight, speed, stamina, strength, agility, intelligence);
        this.money = 50;
    }

    // Getters
    public Inventory getInventory() { return inventory; }
    public List<Quest> getActiveQuests() { return activeQuests; }
    public int getMoney() { return money; }
    public CloseRangeWeapon getEquippedWeapon() { return equippedWeapon; }
    public EquippableItem getHeadSlot() { return headSlot; }
    public EquippableItem getChestSlot() { return chestSlot; }
    public EquippableItem getLegSlot() { return legSlot; }
    public EquippableItem getFootSlot() { return footSlot; }
    public EquippableItem getBackpackSlot() { return backpackSlot; }

    // Setters
    public void setInventory(Inventory inventory) { this.inventory = (inventory != null) ? inventory : new Inventory(); }
    public void setActiveQuests(List<Quest> quests) { this.activeQuests = (quests != null) ? quests : new ArrayList<>(); }
    public void setMoney(int money) { this.money = money; }
    public void setEquippedWeapon(CloseRangeWeapon weapon) { this.equippedWeapon = weapon; }
    public void setHeadSlot(EquippableItem item) { this.headSlot = item; }
    public void setChestSlot(EquippableItem item) { this.chestSlot = item; }
    public void setLegSlot(EquippableItem item) { this.legSlot = item; }
    public void setFootSlot(EquippableItem item) { this.footSlot = item; }
    public void setBackpackSlot(EquippableItem item) { this.backpackSlot = item; }

    // Logic Methods
    public void addQuest(Quest quest) { this.activeQuests.add(quest); }
    public void addMoney(int amount) { this.money += amount; }
    public boolean spendMoney(int amount) {
        if (this.money >= amount) {
            this.money -= amount;
            return true;
        }
        return false;
    }

    public EquippableItem equipItem(EquippableItem item) {
        if (item == null) return null;
        
        EquippableItem oldItem = null;
        String slot = item.getSlot().toLowerCase();

        switch (slot) {
            case "head":
                oldItem = this.headSlot;
                this.headSlot = item;
                break;
            case "chest":
                oldItem = this.chestSlot;
                this.chestSlot = item;
                break;
            case "legs":
                oldItem = this.legSlot;
                this.legSlot = item;
                break;
            case "feet":
                oldItem = this.footSlot;
                this.footSlot = item;
                break;
            case "backpack":
                oldItem = this.backpackSlot;
                this.backpackSlot = item;
                this.inventory.equipBackpack();
                break;
        }
        return oldItem;
    }

    /**
     * THE FIX: Checks if the item is equipped and removes it from the slot if so.
     */
    public void unequipIfEquipped(Item item) {
        if (item == null) return;
        
        if (equippedWeapon == item) {
            equippedWeapon = null;
        } else if (headSlot == item) {
            headSlot = null;
        } else if (chestSlot == item) {
            chestSlot = null;
        } else if (legSlot == item) {
            legSlot = null;
        } else if (footSlot == item) {
            footSlot = null;
        } else if (backpackSlot == item) {
            backpackSlot = null;
            inventory.unequipBackpack();
        }
    }

    @JsonIgnore
    public String getPlayerInfo() {
        StringBuilder info = new StringBuilder();
        info.append("--- Player Info ---\n");
        info.append(String.format("Name:     %s\n", getName()));
        info.append(String.format("Health:   %.0f\n", getHealth()));
        info.append(String.format("Stamina:  %.0f\n", getStamina()));
        info.append(String.format("Strength: %d\n", getStrength()));
        info.append(String.format("Money:    %d Gold\n", getMoney()));
        
        info.append("Weapon:   ").append(equippedWeapon != null ? equippedWeapon.getName() : "None").append("\n");
        info.append("Head:     ").append(headSlot != null ? headSlot.getName() : "None").append("\n");
        info.append("Chest:    ").append(chestSlot != null ? chestSlot.getName() : "None").append("\n");
        info.append("Legs:     ").append(legSlot != null ? legSlot.getName() : "None").append("\n");
        info.append("Feet:     ").append(footSlot != null ? footSlot.getName() : "None").append("\n");
        info.append("Backpack: ").append(backpackSlot != null ? backpackSlot.getName() : "None").append("\n");

        info.append("-------------------");
        return info.toString();
    }
}
