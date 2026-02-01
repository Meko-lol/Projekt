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
    
    @JsonIgnore private EquippableItem headSlot;
    @JsonIgnore private EquippableItem chestSlot;
    @JsonIgnore private CloseRangeWeapon equippedWeapon;

    public Player() {
        super();
    }

    public Player(String name, String race, double health, double weight, double speed, double stamina, int strength, int agility, int intelligence) {
        super(name, race, health, weight, speed, stamina, strength, agility, intelligence);
    }

    // Getters
    public Inventory getInventory() { return inventory; }
    public List<Quest> getActiveQuests() { return activeQuests; }
    public CloseRangeWeapon getEquippedWeapon() { return equippedWeapon; }

    // Setters
    public void setInventory(Inventory inventory) { this.inventory = (inventory != null) ? inventory : new Inventory(); }
    public void setActiveQuests(List<Quest> quests) { this.activeQuests = (quests != null) ? quests : new ArrayList<>(); }
    public void setEquippedWeapon(CloseRangeWeapon weapon) { this.equippedWeapon = weapon; }

    // Logic Methods
    public void addQuest(Quest quest) { this.activeQuests.add(quest); }

    /**
     * THE FIX: A single method to handle equipping any item to its correct slot.
     * It returns the item that was previously in the slot, if any.
     */
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
            // Add cases for other slots like "legs", "feet" here in the future
        }
        return oldItem;
    }

    @JsonIgnore
    public String getPlayerInfo() {
        StringBuilder info = new StringBuilder();
        info.append("--- Player Info ---\n");
        info.append("Name:     ").append(getName()).append("\n");
        info.append("Health:   ").append(String.format("%.0f", getHealth())).append("\n");
        info.append("Stamina:  ").append(String.format("%.0f", getStamina())).append("\n");
        info.append("Strength: ").append(getStrength()).append("\n");
        String weaponName = "None";
        if (equippedWeapon != null) {
            weaponName = equippedWeapon.getName();
        }
        info.append("Equipped: ").append(weaponName).append("\n");
        info.append("-------------------");
        return info.toString();
    }
}
