package me.endistic.skyblock.items.gear.silence;

import me.endistic.skyblock.items.*;
import me.endistic.skyblock.stats.StatsObject;
import org.bukkit.Material;

public class SilenceHelmet extends CustomItemTemplate {
    @Override
    public ItemMetadata getItemData() {
        return new ItemMetadata()
            .setMaterial(Material.PLAYER_HEAD)
            .setName("Silence Helmet")
            .setType(ItemSlot.HELMET)
            .setRarity(Rarity.LEGENDARY)
            .setTexture(ItemTexture.SILENCE_ARMOR);
    }

    @Override
    public StatsObject getStats() {
        return new StatsObject()
            .setMaxHealth(195)
            .setDefense(55)
            .setIntelligence(15);
    }

    @Override
    public String getId() {
        return "silence_helmet";
    }


    @Override
    public UpgradingCost getUpgradingCost() {
        return Defaults.getUpgradeCost();
    }
}
