package me.endistic.skyblock.items.gear.gravitational;

import me.endistic.skyblock.items.*;
import me.endistic.skyblock.items.crafting.Recipe;
import me.endistic.skyblock.stats.StatsObject;
import org.bukkit.Color;
import org.bukkit.Material;

import java.util.List;

public class GravitationalLeggings extends CustomItemTemplate {
    @Override
    public ItemMetadata getItemData() {
        return new ItemMetadata()
            .setMaterial(Material.LEATHER_LEGGINGS)
            .setName("Gravitational Leggings")
            .setType(ItemSlot.LEGGINGS)
            .setRarity(Rarity.LEGENDARY)
            .setArmorColor(Color.PURPLE);
    }

    @Override
    public StatsObject getStats() {
        return new StatsObject()
            .setMaxHealth(205)
            .setDefense(35)
            .setIntelligence(400)
            .setAbilityDamageScaling(0.25);
    }

    @Override
    public String getId() {
        return "gravitational_leggings";
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
                .setSlot(5, "silence_leggings", 1)
        );
    }
}
