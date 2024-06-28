package me.endistic.skyblock.items;

import me.endistic.skyblock.abilities.Ability;
import me.endistic.skyblock.items.crafting.Recipe;
import me.endistic.skyblock.stats.StatsObject;

import java.util.List;

public class ItemInitializer {
    public static void item(
        ItemMetadata data,
        StatsObject stats,
        String id,
        Ability ability,
        List<Recipe> recipe,
        UpgradingCost upgradingCost,
        boolean hideFromLists
    ) {
        var item = new CustomItem()
            .setData(data)
            .setId(id)
            .setStats(stats)
            .setAbility(ability)
            .setRecipes(recipe)
            .setUpgradingCost(upgradingCost);
        ItemDatabase.database.put(id, item);
        if(recipe != null && !hideFromLists)
            ItemDatabase.idsWithRecipes.add(id);
        ItemDatabase.builtItems.add(item.build(new ItemModifiers()));
    }
}
