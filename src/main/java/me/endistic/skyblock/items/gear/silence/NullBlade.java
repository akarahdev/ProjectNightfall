package me.endistic.skyblock.items.gear.silence;

import me.endistic.skyblock.items.*;
import me.endistic.skyblock.items.crafting.Recipe;
import me.endistic.skyblock.stats.StatsObject;
import me.endistic.skyblock.utils.Range;
import org.bukkit.Material;

import java.util.List;

public class NullBlade extends CustomItemTemplate {
    @Override
    public ItemMetadata getItemData() {
        return new ItemMetadata()
            .setMaterial(Material.NETHERITE_SWORD)
            .setName("Null Blade")
            .setType(ItemSlot.SWORD)
            .setRarity(Rarity.LEGENDARY);
    }

    @Override
    public StatsObject getStats() {
        return new StatsObject()
            .setDamage(300)
            .setStrength(75)
            .setCriticalDamage(75)
            .setLifesteal(20);
    }

    @Override
    public String getId() {
        return "null_blade";
    }

    @Override
    public UpgradingCost getUpgradingCost() {
        return Defaults.getUpgradeCost();
    }
}
