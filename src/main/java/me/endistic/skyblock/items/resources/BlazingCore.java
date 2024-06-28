package me.endistic.skyblock.items.resources;

import me.endistic.skyblock.items.CustomItemTemplate;
import me.endistic.skyblock.items.ItemMetadata;
import me.endistic.skyblock.items.ItemSlot;
import me.endistic.skyblock.items.Rarity;
import me.endistic.skyblock.stats.StatsObject;
import org.bukkit.Material;

public class BlazingCore extends CustomItemTemplate {
    public ItemMetadata getItemData() {
        return new ItemMetadata()
            .setMaterial(Material.BLAZE_ROD)
            .setName("Blazing Core")
            .setType(ItemSlot.RESOURCE)
            .setRarity(Rarity.RARE);
    }

    public StatsObject getStats() {
        return new StatsObject();
    }

    public String getId() {
        return "blazing_core";
    }
}
