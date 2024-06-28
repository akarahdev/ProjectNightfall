package me.endistic.skyblock.items.gear.silence;

import me.endistic.skyblock.items.*;
import me.endistic.skyblock.stats.StatsObject;
import org.bukkit.Color;
import org.bukkit.Material;

public class SilenceChestplate extends CustomItemTemplate {
    @Override
    public ItemMetadata getItemData() {
        return new ItemMetadata()
            .setMaterial(Material.LEATHER_CHESTPLATE)
            .setName("Silence Chestplate")
            .setType(ItemSlot.CHESTPLATE)
            .setRarity(Rarity.LEGENDARY)
            .setArmorColor(Color.BLACK);
    }

    @Override
    public StatsObject getStats() {
        return new StatsObject()
            .setMaxHealth(270)
            .setDefense(70)
            .setIntelligence(15);
    }

    @Override
    public String getId() {
        return "silence_chestplate";
    }


    @Override
    public UpgradingCost getUpgradingCost() {
        return Defaults.getUpgradeCost();
    }
}
