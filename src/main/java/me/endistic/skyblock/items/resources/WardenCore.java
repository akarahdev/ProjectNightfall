package me.endistic.skyblock.items.resources;

import me.endistic.skyblock.items.CustomItemTemplate;
import me.endistic.skyblock.items.ItemMetadata;
import me.endistic.skyblock.items.ItemSlot;
import me.endistic.skyblock.items.Rarity;
import me.endistic.skyblock.stats.StatsObject;
import org.bukkit.Material;

public class WardenCore extends CustomItemTemplate {
    public ItemMetadata getItemData() {
        return new ItemMetadata()
            .setMaterial(Material.CYAN_CANDLE)
            .setName("Warden Core")
            .setType(ItemSlot.RESOURCE)
            .setRarity(Rarity.EPIC);
    }

    public StatsObject getStats() {
        return new StatsObject();
    }

    public String getId() {
        return "warden_core";
    }
}
