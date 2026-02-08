package Game;

import Items.Item;
import java.util.List;

public class ItemFinder {

    /**
     * Finds an item in a list using partial name matching.
     * @param items The list of items to search.
     * @param searchText The text to search for.
     * @return The first matching item, or null if not found.
     */
    public static Item findItem(List<Item> items, String searchText) {
        if (items == null || items.isEmpty()) return null;
        
        String lowerSearch = searchText.toLowerCase();
        for (Item item : items) {
            if (item.getName().toLowerCase().contains(lowerSearch)) {
                return item;
            }
        }
        return null;
    }

    /**
     * Helper to join arguments into a single search string.
     */
    public static String joinArgs(String[] args, int startIndex) {
        StringBuilder sb = new StringBuilder();
        for (int i = startIndex; i < args.length; i++) {
            sb.append(args[i]).append(" ");
        }
        return sb.toString().trim();
    }
}
