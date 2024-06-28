package me.endistic.skyblock.items.accessories;

import me.endistic.skyblock.items.*;
import me.endistic.skyblock.items.crafting.Recipe;
import me.endistic.skyblock.stats.StatsObject;
import org.bukkit.Material;

import java.util.List;

public class FloralTalisman extends CustomItemTemplate {
    @Override
    public ItemMetadata getItemData() {
        return new ItemMetadata()
            .setMaterial(Material.IRON_INGOT)
            .setName("Floral Talisman")
            .setType(ItemSlot.ACCESSORY)
            .setRarity(Rarity.MYTHIC);
    }

    @Override
    public StatsObject getStats() {
        return new StatsObject();
    }

    @Override
    public String getId() {
        return "floral_talisman";
    }

    @Override
    public List<Recipe> getRecipe() {
        return List.of(new Recipe()
            .setSlot(1, "floral_fragment", 1)
            .setSlot(2, "floral_fragment", 1)
            .setSlot(3, "floral_fragment", 1)
            .setSlot(4, "floral_fragment", 1)
            .setSlot(6, "floral_fragment", 1)
            .setSlot(7, "floral_fragment", 1)
            .setSlot(8, "floral_fragment", 1)
            .setSlot(9, "floral_fragment", 1));
    }
}
