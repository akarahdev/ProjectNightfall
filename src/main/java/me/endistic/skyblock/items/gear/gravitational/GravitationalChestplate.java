package me.endistic.skyblock.items.gear.gravitational;

import me.endistic.skyblock.items.*;
import me.endistic.skyblock.items.crafting.Recipe;
import me.endistic.skyblock.stats.StatsObject;
import org.bukkit.Color;
import org.bukkit.Material;

import java.util.List;

public class GravitationalChestplate extends CustomItemTemplate {
    @Override
    public ItemMetadata getItemData() {
        return new ItemMetadata()
            .setMaterial(Material.LEATHER_CHESTPLATE)
            .setName("Gravitational Chestplate")
            .setType(ItemSlot.CHESTPLATE)
            .setRarity(Rarity.LEGENDARY)
            .setArmorColor(Color.BLUE);
    }

    @Override
    public StatsObject getStats() {
        return new StatsObject()
            .setMaxHealth(230)
            .setDefense(30)
            .setIntelligence(400)
            .setAbilityDamageScaling(0.25);
    }

    @Override
    public String getId() {
        return "gravitational_chestplate";
    }

    @Override
    public UpgradingCost getUpgradingCost() {
        return Defaults.getUpgradeCost();
    }

    @Override
    public List<Recipe> getRecipe() {
        return List.of(
            new Recipe()
                .setSlot(2, "gravitational_fragment", 1)
                .setSlot(5, "silence_chestplate", 1)
        );
    }
}
