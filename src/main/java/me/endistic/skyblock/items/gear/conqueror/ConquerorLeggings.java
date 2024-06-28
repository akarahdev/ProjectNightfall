package me.endistic.skyblock.items.gear.conqueror;

import me.endistic.skyblock.items.*;
import me.endistic.skyblock.items.crafting.Recipe;
import me.endistic.skyblock.stats.StatsObject;
import org.bukkit.Color;
import org.bukkit.Material;

import java.util.List;

public class ConquerorLeggings extends CustomItemTemplate {
    @Override
    public ItemMetadata getItemData() {
        return new ItemMetadata()
            .setMaterial(Material.LEATHER_LEGGINGS)
            .setName("Conqueror Leggings")
            .setType(ItemSlot.LEGGINGS)
            .setRarity(Rarity.LEGENDARY)
            .setArmorColor(Color.RED);
    }

    @Override
    public StatsObject getStats() {
        return new StatsObject()
            .setMaxHealth(205)
            .setDefense(55)
            .setStrength(30)
            .setRangedDamagePct(-5)
            .setLifesteal(15);
    }

    @Override
    public String getId() {
        return "conqueror_leggings";
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
                .setSlot(5, "silence_leggings", 1)
                .setModifierCloneSlot(5),
            new Recipe()
                .setSlot(2, "conqueror_fragment", 1)
                .setSlot(5, "gravitational_leggings", 1)
                .setModifierCloneSlot(5),
            new Recipe()
                .setSlot(2, "conqueror_fragment", 1)
                .setSlot(5, "entropic_leggings", 1)
                .setModifierCloneSlot(5)
        );
    }
}
