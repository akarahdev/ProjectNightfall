package me.endistic.skyblock.items.armor.floral;

import me.endistic.skyblock.items.*;
import me.endistic.skyblock.stats.StatsObject;
import org.bukkit.Material;

public class FloralHelmet extends CustomItemTemplate {
    public ItemMetadata getItemData() {
        return new ItemMetadata()
            .setMaterial(Material.PLAYER_HEAD)
            .setName("Floral Helmet")
            .setType(ItemSlot.HELMET)
            .setRarity(Rarity.LEGENDARY)
            .setTexture(ItemTexture.FLORAL_HELMET);
    }

    public StatsObject getStats() {
        return new StatsObject()
            .setMaxHealth(300)
            .setDefense(100)
            .setStrength(30)
            .setCriticalDamage(20);
    }

    public String getId() {
        return "floral_helmet";
    }

    @Override
    public boolean hideFromLists() { return true; }
}

