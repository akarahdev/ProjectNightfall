package me.endistic.skyblock.items.gear.silence;

import me.endistic.skyblock.items.*;
import me.endistic.skyblock.stats.StatsObject;
import org.bukkit.Color;
import org.bukkit.Material;

public class SilenceLeggings extends CustomItemTemplate {
    @Override
    public ItemMetadata getItemData() {
        return new ItemMetadata()
            .setMaterial(Material.LEATHER_LEGGINGS)
            .setName("Silence Leggings")
            .setType(ItemSlot.LEGGINGS)
            .setRarity(Rarity.LEGENDARY)
            .setArmorColor(Color.BLACK);
    }

    @Override
    public StatsObject getStats() {
        return new StatsObject()
            .setMaxHealth(245)
            .setDefense(60)
            .setIntelligence(15);
    }

    @Override
    public String getId() {
        return "silence_leggings";
    }


    @Override
    public UpgradingCost getUpgradingCost() {
        return Defaults.getUpgradeCost();
    }
}
