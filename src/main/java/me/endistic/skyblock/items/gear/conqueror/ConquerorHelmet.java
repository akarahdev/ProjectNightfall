package me.endistic.skyblock.items.gear.conqueror;

import me.endistic.skyblock.items.*;
import me.endistic.skyblock.items.crafting.Recipe;
import me.endistic.skyblock.stats.StatsObject;
import org.bukkit.Material;

import java.util.List;

public class ConquerorHelmet extends CustomItemTemplate {
    @Override
    public ItemMetadata getItemData() {
        return new ItemMetadata()
            .setMaterial(Material.PLAYER_HEAD)
            .setName("Conqueror Helmet")
            .setType(ItemSlot.HELMET)
            .setRarity(Rarity.LEGENDARY)
            .setTexture(ItemTexture.CONQUEROR_HELMET);
    }

    @Override
    public StatsObject getStats() {
        return new StatsObject()
            .setMaxHealth(160)
            .setDefense(50)
            .setStrength(30)
            .setMeleeDamagePct(10)
            .setRangedDamagePct(-10)
            .setLifesteal(30);
    }

    @Override
    public String getId() {
        return "conqueror_helmet";
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
                .setSlot(5, "silence_helmet", 1)
                .setModifierCloneSlot(5),
            new Recipe()
                .setSlot(2, "conqueror_fragment", 1)
                .setSlot(5, "gravitational_helmet", 1)
                .setModifierCloneSlot(5),
            new Recipe()
                .setSlot(2, "conqueror_fragment", 1)
                .setSlot(5, "entropic_helmet", 1)
                .setModifierCloneSlot(5)
        );
    }
}
