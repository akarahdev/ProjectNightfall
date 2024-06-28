package me.endistic.skyblock.items;

import me.endistic.skyblock.abilities.Ability;
import me.endistic.skyblock.items.crafting.Recipe;
import me.endistic.skyblock.stats.StatsObject;

import java.util.List;

/**
 * Acts as an organized template for initializing Custom Item classes.
 */
public class CustomItemTemplate {
    public CustomItemTemplate() {
        ItemInitializer.item(
            this.getItemData(),
            this.getStats(),
            this.getId(),
            this.getAbility(),
            this.getRecipe(),
            this.getUpgradingCost(),
            this.hideFromLists()
        );
    }
    public ItemMetadata getItemData() {
        return null;
    }

    public StatsObject getStats() {
        return null;
    }

    public String getId() {
        return null;
    }
    public Ability getAbility() { return null; }
    public List<Recipe> getRecipe() { return null; }
    public UpgradingCost getUpgradingCost() { return null; }
    public boolean hideFromLists() { return false; }


}
