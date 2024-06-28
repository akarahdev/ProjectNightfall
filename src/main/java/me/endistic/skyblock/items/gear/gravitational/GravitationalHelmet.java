package me.endistic.skyblock.items.gear.gravitational;

import me.endistic.skyblock.items.*;
import me.endistic.skyblock.items.crafting.Recipe;
import me.endistic.skyblock.stats.StatsObject;
import org.bukkit.Material;

import java.util.List;

public class GravitationalHelmet extends CustomItemTemplate {
    @Override
    public ItemMetadata getItemData() {
        return new ItemMetadata()
            .setMaterial(Material.PLAYER_HEAD)
            .setName("Gravitational Helmet")
            .setType(ItemSlot.HELMET)
            .setRarity(Rarity.LEGENDARY)
            .setTexture(ItemTexture.GRAVITATIONAL_HELMET);
    }

    @Override
    public StatsObject getStats() {
        return new StatsObject()
            .setMaxHealth(160)
            .setDefense(25)
            .setIntelligence(800)
            .setAbilityDamageScaling(0.66);
    }

    @Override
    public String getId() {
        return "gravitational_helmet";
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
                .setSlot(5, "silence_helmet", 1)
        );
    }
}
