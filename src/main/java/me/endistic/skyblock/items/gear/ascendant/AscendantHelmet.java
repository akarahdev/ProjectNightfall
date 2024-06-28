package me.endistic.skyblock.items.gear.ascendant;

import me.endistic.skyblock.items.*;
import me.endistic.skyblock.items.crafting.Recipe;
import me.endistic.skyblock.stats.StatsObject;
import org.bukkit.Material;

import java.util.List;

public class AscendantHelmet extends CustomItemTemplate {
    @Override
    public ItemMetadata getItemData() {
        return new ItemMetadata()
            .setMaterial(Material.PLAYER_HEAD)
            .setName("Ascendant Helmet")
            .setType(ItemSlot.HELMET)
            .setRarity(Rarity.LEGENDARY)
            .setTexture(ItemTexture.ASCENDANT_HELMET);
    }

    @Override
    public StatsObject getStats() {
        return new StatsObject()
            .setMaxHealth(260)
            .setDefense(80)
            .setMending(20);
    }

    @Override
    public String getId() {
        return "ascendant_helmet";
    }

    @Override
    public UpgradingCost getUpgradingCost() {
        return Defaults.getUpgradeCost();
    }

    @Override
    public List<Recipe> getRecipe() {
        return List.of();
    }
}
