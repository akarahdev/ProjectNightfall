package me.endistic.skyblock.items.resources;

import me.endistic.skyblock.items.CustomItemTemplate;
import me.endistic.skyblock.items.ItemMetadata;
import me.endistic.skyblock.items.Rarity;
import me.endistic.skyblock.items.ItemSlot;
import me.endistic.skyblock.stats.StatsObject;
import org.bukkit.Material;

public class ReaverCore extends CustomItemTemplate {
    public ItemMetadata getItemData() {
        return new ItemMetadata()
            .setMaterial(Material.RED_MUSHROOM)
            .setName("Reaver Core")
            .setType(ItemSlot.RESOURCE)
            .setRarity(Rarity.EPIC);
    }

    public StatsObject getStats() {
        return new StatsObject();
    }

    public String getId() {
        return "reaver_core";
    }
}
