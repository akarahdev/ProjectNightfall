package me.endistic.skyblock.items.gear.entropy;

import me.endistic.skyblock.items.*;
import me.endistic.skyblock.items.crafting.Recipe;
import me.endistic.skyblock.stats.StatsObject;
import org.bukkit.Material;

import java.util.List;

public class EntropicShortbow extends CustomItemTemplate {
    @Override
    public ItemMetadata getItemData() {
        return new ItemMetadata()
            .setMaterial(Material.BOW)
            .setName("Entropic Shortbow")
            .setType(ItemSlot.BOW)
            .setRarity(Rarity.LEGENDARY);
    }

    @Override
    public StatsObject getStats() {
        return new StatsObject()
            .setDamage(180)
            .setCriticalDamage(30)
            .setStrength(60)
            .setArrowCount(3)
            .setArrowSpeed(50)
            .setCriticalChance(10);
    }

    @Override
    public String getId() {
        return "entropic_shortbow";
    }

    @Override
    public UpgradingCost getUpgradingCost() {
        return Defaults.getUpgradeCost();
    }

    @Override
    public List<Recipe> getRecipe() {
        return List.of(
            new Recipe()
                .setSlot(2, "entropic_fragment", 1)
                .setSlot(5, "null_blade", 1)
                .setModifierCloneSlot(5),
            new Recipe()
                .setSlot(2, "entropic_fragment", 1)
                .setSlot(5, "conqueror_longsword", 1)
                .setModifierCloneSlot(5)
        );
    }
}
