package me.endistic.skyblock.items.accessories;

import me.endistic.skyblock.items.CustomItemTemplate;
import me.endistic.skyblock.items.ItemMetadata;
import me.endistic.skyblock.items.Rarity;
import me.endistic.skyblock.items.ItemSlot;
import me.endistic.skyblock.items.crafting.Recipe;
import me.endistic.skyblock.stats.StatsObject;
import org.bukkit.Material;

import java.util.List;

public class WardenTalisman extends CustomItemTemplate {
    public ItemMetadata getItemData() {
        return new ItemMetadata()
            .setMaterial(Material.SCULK_CATALYST)
            .setName("Warden Talisman")
            .setType(ItemSlot.ACCESSORY)
            .setRarity(Rarity.MYTHIC);
    }

    public StatsObject getStats() {
        return new StatsObject();
    }

    public String getId() {
        return "warden_talisman";
    }

    @Override
    public List<Recipe> getRecipe() {
        return List.of(new Recipe()
            .setSlot(1, "warden_fragment", 1)
            .setSlot(2, "warden_fragment", 1)
            .setSlot(3, "warden_fragment", 1)
            .setSlot(4, "warden_fragment", 1)
            .setSlot(5, "warden_flux", 1)
            .setSlot(6, "warden_fragment", 1)
            .setSlot(7, "warden_fragment", 1)
            .setSlot(8, "warden_fragment", 1)
            .setSlot(9, "warden_fragment", 1));
    }
}
