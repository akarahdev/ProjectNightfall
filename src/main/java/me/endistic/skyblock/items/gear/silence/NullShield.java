package me.endistic.skyblock.items.gear.silence;

import me.endistic.skyblock.items.*;
import me.endistic.skyblock.stats.StatsObject;
import me.endistic.skyblock.utils.Range;
import org.bukkit.Material;

import java.util.List;

public class NullShield extends CustomItemTemplate {
    @Override
    public ItemMetadata getItemData() {
        return new ItemMetadata()
            .setMaterial(Material.SHIELD)
            .setName("Null Shield")
            .setType(ItemSlot.SHIELD)
            .setRarity(Rarity.LEGENDARY);
    }

    @Override
    public StatsObject getStats() {
        return new StatsObject();
    }

    @Override
    public String getId() {
        return "null_shield";
    }


    @Override
    public UpgradingCost getUpgradingCost() {
        return Defaults.getUpgradeCost();
    }
}
