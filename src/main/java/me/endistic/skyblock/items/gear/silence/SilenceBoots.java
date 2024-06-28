package me.endistic.skyblock.items.gear.silence;

import me.endistic.skyblock.items.*;
import me.endistic.skyblock.stats.StatsObject;
import org.bukkit.Color;
import org.bukkit.Material;

public class SilenceBoots extends CustomItemTemplate {
    @Override
    public ItemMetadata getItemData() {
        return new ItemMetadata()
            .setMaterial(Material.LEATHER_BOOTS)
            .setName("Silence Boots")
            .setType(ItemSlot.BOOTS)
            .setRarity(Rarity.LEGENDARY)
            .setArmorColor(Color.BLACK);
    }

    @Override
    public StatsObject getStats() {
        return new StatsObject()
            .setMaxHealth(170)
            .setDefense(45)
            .setIntelligence(15);
    }

    @Override
    public String getId() {
        return "silence_boots";
    }

    @Override
    public UpgradingCost getUpgradingCost() {
        return Defaults.getUpgradeCost();
    }
}
