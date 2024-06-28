package me.endistic.skyblock.items.gear.entropy;

import me.endistic.skyblock.items.*;
import me.endistic.skyblock.stats.StatsObject;
import me.endistic.skyblock.utils.Range;
import org.bukkit.Material;

import java.util.List;

public class EntropicShield extends CustomItemTemplate {
    @Override
    public ItemMetadata getItemData() {
        return new ItemMetadata()
            .setMaterial(Material.SHIELD)
            .setName("Entropic Shield")
            .setType(ItemSlot.SHIELD)
            .setRarity(Rarity.LEGENDARY);
    }

    @Override
    public StatsObject getStats() {
        return new StatsObject()
            .setArrowCount(0.5)
            .setCriticalDamage(30)
            .setStrength(20)
            .setCriticalChance(3)
            .setFerocity(-50)
            .setRangedDamagePct(10)
            .setMeleeDamagePct(-20);
    }

    @Override
    public String getId() {
        return "entropic_shield";
    }

    @Override
    public UpgradingCost getUpgradingCost() {
        return Defaults.getUpgradeCost();
    }
}
