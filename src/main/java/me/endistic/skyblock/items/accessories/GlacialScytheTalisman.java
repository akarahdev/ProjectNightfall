package me.endistic.skyblock.items.accessories;

import me.endistic.skyblock.items.*;
import me.endistic.skyblock.items.crafting.Recipe;
import me.endistic.skyblock.stats.StatsObject;
import org.bukkit.Material;

import java.util.List;

public class GlacialScytheTalisman extends CustomItemTemplate {
    public ItemMetadata getItemData() {
        return new ItemMetadata()
            .setMaterial(Material.DIAMOND_HOE)
            .setName("Glacial Scythe Talisman")
            .setType(ItemSlot.ACCESSORY)
            .setRarity(Rarity.DIVINE);
    }

    public StatsObject getStats() {
        return new StatsObject();
    }

    public String getId() {
        return "glacial_scythe_talisman";
    }
}
