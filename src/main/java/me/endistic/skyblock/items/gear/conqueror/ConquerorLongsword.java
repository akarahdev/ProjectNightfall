package me.endistic.skyblock.items.gear.conqueror;

import me.endistic.skyblock.items.*;
import me.endistic.skyblock.items.crafting.Recipe;
import me.endistic.skyblock.stats.StatsObject;
import org.bukkit.Material;

import java.util.List;

public class ConquerorLongsword extends CustomItemTemplate {
    @Override
    public ItemMetadata getItemData() {
        return new ItemMetadata()
            .setMaterial(Material.NETHERITE_SWORD)
            .setName("Conqueror Longsword")
            .setType(ItemSlot.SWORD)
            .setRarity(Rarity.LEGENDARY);
    }

    @Override
    public StatsObject getStats() {
        return new StatsObject()
            .setDamage(240)
            .setStrength(140)
            .setCriticalDamage(110)
            .setSwingRange(1)
            .setFerocity(90)
            .setLifesteal(50);
    }

    @Override
    public String getId() {
        return "conqueror_longsword";
    }

    @Override
    public UpgradingCost getUpgradingCost() {
        return Defaults.getUpgradeCost();
    }

    @Override
    public List<Recipe> getRecipe() {
        return List.of(
            new Recipe()
                .setSlot(2, "conqueror_fragment", 1)
                .setSlot(5, "null_blade", 1)
                .setModifierCloneSlot(5),
            new Recipe()
                .setSlot(2, "conqueror_fragment", 1)
                .setSlot(5, "entropic_shortbow", 1)
                .setModifierCloneSlot(5)
        );
    }
}
