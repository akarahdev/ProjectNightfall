package me.endistic.skyblock.items.gear.ascendant;

import me.endistic.skyblock.items.*;
import me.endistic.skyblock.items.crafting.Recipe;
import me.endistic.skyblock.stats.StatsObject;
import org.bukkit.Color;
import org.bukkit.Material;

import java.util.List;

public class AscendantChestplate extends CustomItemTemplate {
    @Override
    public ItemMetadata getItemData() {
        return new ItemMetadata()
            .setMaterial(Material.GOLDEN_CHESTPLATE)
            .setName("Ascendant Chestplate")
            .setType(ItemSlot.CHESTPLATE)
            .setRarity(Rarity.LEGENDARY);
    }

    @Override
    public StatsObject getStats() {
        return new StatsObject()
            .setMaxHealth(330)
            .setDefense(95)
            .setMending(20);
    }

    @Override
    public String getId() {
        return "ascendant_chestplate";
    }

    @Override
    public UpgradingCost getUpgradingCost() {
        return Defaults.getUpgradeCost();
    }

    @Override
    public List<Recipe> getRecipe() {
        return List.of();
    }
}
