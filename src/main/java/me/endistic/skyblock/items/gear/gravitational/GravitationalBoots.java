package me.endistic.skyblock.items.gear.gravitational;

import me.endistic.skyblock.items.*;
import me.endistic.skyblock.items.crafting.Recipe;
import me.endistic.skyblock.stats.StatsObject;
import org.bukkit.Color;
import org.bukkit.Material;

import java.util.List;

public class GravitationalBoots extends CustomItemTemplate {
    @Override
    public ItemMetadata getItemData() {
        return new ItemMetadata()
            .setMaterial(Material.LEATHER_BOOTS)
            .setName("Gravitational Boots")
            .setType(ItemSlot.BOOTS)
            .setRarity(Rarity.LEGENDARY)
            .setArmorColor(Color.PURPLE);
    }

    @Override
    public StatsObject getStats() {
        return new StatsObject()
            .setMaxHealth(130)
            .setDefense(20)
            .setIntelligence(200)
            .setWalkSpeed(20)
            .setAbilityDamageScaling(0.25);
    }

    @Override
    public String getId() {
        return "gravitational_boots";
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
                .setSlot(5, "silence_boots", 1)
        );
    }
}
