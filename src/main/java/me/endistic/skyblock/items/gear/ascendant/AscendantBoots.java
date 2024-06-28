package me.endistic.skyblock.items.gear.ascendant;

import me.endistic.skyblock.items.*;
import me.endistic.skyblock.items.crafting.Recipe;
import me.endistic.skyblock.stats.StatsObject;
import org.bukkit.Color;
import org.bukkit.Material;

import java.util.List;

public class AscendantBoots extends CustomItemTemplate {
    @Override
    public ItemMetadata getItemData() {
        return new ItemMetadata()
            .setMaterial(Material.GOLDEN_BOOTS)
            .setName("Ascendant Boots")
            .setType(ItemSlot.BOOTS)
            .setRarity(Rarity.LEGENDARY);
    }

    @Override
    public StatsObject getStats() {
        return new StatsObject()
            .setMaxHealth(230)
            .setDefense(60)
            .setWalkSpeed(40)
            .setMending(20);
    }

    @Override
    public String getId() {
        return "ascendant_boots";
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
