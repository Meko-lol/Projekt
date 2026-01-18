package Commands.commandList;

import Commands.Command;
import Items.EquippableItems.EquippableItem;

public class EquipItemCommand extends Command {
    @Override
    public String execute() {
        //TODO
        return "What do you want to equip?";
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
