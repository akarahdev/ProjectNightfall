package me.endistic.skyblock.items.resources;

import me.endistic.skyblock.items.CustomItemTemplate;
import me.endistic.skyblock.items.ItemMetadata;
import me.endistic.skyblock.items.Rarity;
import me.endistic.skyblock.items.ItemSlot;
import me.endistic.skyblock.items.crafting.Recipe;
import me.endistic.skyblock.stats.StatsObject;
import org.bukkit.Material;

import java.util.List;

public class ConquerorFragment extends CustomItemTemplate {
    public ItemMetadata getItemData() {
        return new ItemMetadata()
            .setMaterial(Material.RED_CANDLE)
            .setName("Conqueror Fragment")
            .setType(ItemSlot.RESOURCE)
            .setRarity(Rarity.RARE);
    }

    public StatsObject getStats() {
        return new StatsObject();
    }

    public String getId() {
        return "conqueror_fragment";
    }

    @Override
    public List<Recipe> getRecipe() {
        return List.of(
            new Recipe()
                .setSlot(2, "floral_fragment", 1)
                .setSlot(4, "floral_fragment", 1)
                .setSlot(6, "floral_fragment", 1)
        );
    }
}
