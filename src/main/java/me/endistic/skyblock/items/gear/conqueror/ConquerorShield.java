package me.endistic.skyblock.items.gear.conqueror;

import me.endistic.skyblock.items.*;
import me.endistic.skyblock.stats.StatsObject;
import me.endistic.skyblock.utils.Range;
import org.bukkit.Material;

import java.util.List;

public class ConquerorShield extends CustomItemTemplate {
    @Override
    public ItemMetadata getItemData() {
        return new ItemMetadata()
            .setMaterial(Material.SHIELD)
            .setName("Conqueror Shield")
            .setType(ItemSlot.SHIELD)
            .setRarity(Rarity.LEGENDARY);
    }

    @Override
    public StatsObject getStats() {
        return new StatsObject()
            .setStrength(15)
            .setCriticalDamage(40)
            .setMeleeDamagePct(10)
            .setRangedDamagePct(-50)
            .setLifesteal(15);
    }

    @Override
    public String getId() {
        return "conqueror_shield";
    }

    @Override
    public UpgradingCost getUpgradingCost() {
        return Defaults.getUpgradeCost();
    }
}
