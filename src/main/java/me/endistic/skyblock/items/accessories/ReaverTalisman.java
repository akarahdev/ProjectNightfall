package me.endistic.skyblock.items.accessories;

import me.endistic.skyblock.items.*;
import me.endistic.skyblock.items.crafting.Recipe;
import me.endistic.skyblock.stats.StatsObject;
import org.bukkit.Material;

import java.util.List;

public class ReaverTalisman extends CustomItemTemplate {
    public ItemMetadata getItemData() {
        return new ItemMetadata()
            .setMaterial(Material.NETHERITE_INGOT)
            .setName("Reaver Talisman")
            .setType(ItemSlot.ACCESSORY)
            .setRarity(Rarity.MYTHIC);
    }

    public StatsObject getStats() {
        return new StatsObject();
    }

    public String getId() {
        return "reaver_talisman";
    }

    @Override
    public List<Recipe> getRecipe() {
        return List.of(new Recipe()
            .setSlot(1, "reaver_fragment", 1)
            .setSlot(2, "reaver_fragment", 1)
            .setSlot(3, "reaver_fragment", 1)
            .setSlot(4, "reaver_fragment", 1)
            .setSlot(5, "reaver_flux", 1)
            .setSlot(6, "reaver_fragment", 1)
            .setSlot(7, "reaver_fragment", 1)
            .setSlot(8, "reaver_fragment", 1)
            .setSlot(9, "reaver_fragment", 1));
    }
}
