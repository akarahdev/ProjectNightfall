package me.endistic.skyblock.items.accessories;

import me.endistic.skyblock.items.CustomItemTemplate;
import me.endistic.skyblock.items.ItemMetadata;
import me.endistic.skyblock.items.Rarity;
import me.endistic.skyblock.items.ItemSlot;
import me.endistic.skyblock.items.crafting.Recipe;
import me.endistic.skyblock.stats.StatsObject;
import org.bukkit.Material;

import java.util.List;

public class HexTalisman extends CustomItemTemplate {
    public ItemMetadata getItemData() {
        return new ItemMetadata()
            .setMaterial(Material.NETHERITE_INGOT)
            .setName("Hex Talisman")
            .setType(ItemSlot.ACCESSORY)
            .setRarity(Rarity.DIVINE);
    }

    public StatsObject getStats() {
        return new StatsObject();
    }

    public String getId() {
        return "hex_talisman";
    }

    @Override
    public List<Recipe> getRecipe() {
        return List.of(
            new Recipe()
                .setSlot(2, "conqueror_fragment", 3)
                .setSlot(5, "reaver_flux", 1)
                .setSlot(8, "reaver_talisman", 1)
        );
    }
}
