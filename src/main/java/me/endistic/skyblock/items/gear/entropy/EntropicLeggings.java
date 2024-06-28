package me.endistic.skyblock.items.gear.entropy;

import me.endistic.skyblock.items.*;
import me.endistic.skyblock.items.crafting.Recipe;
import me.endistic.skyblock.stats.StatsObject;
import org.bukkit.Color;
import org.bukkit.Material;

import java.util.List;

public class EntropicLeggings extends CustomItemTemplate {
    @Override
    public ItemMetadata getItemData() {
        return new ItemMetadata()
            .setMaterial(Material.LEATHER_LEGGINGS)
            .setName("Entropic Leggings")
            .setType(ItemSlot.LEGGINGS)
            .setRarity(Rarity.LEGENDARY)
            .setArmorColor(Color.BLUE);
    }

    @Override
    public StatsObject getStats() {
        return new StatsObject()
            .setMaxHealth(205)
            .setDefense(55)
            .setCriticalDamage(30)
            .setMeleeDamagePct(-5);
    }

    @Override
    public String getId() {
        return "entropic_leggings";
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
                .setSlot(5, "silence_leggings", 1)
                .setModifierCloneSlot(5),
            new Recipe()
                .setSlot(2, "entropic_fragment", 1)
                .setSlot(5, "conqueror_leggings", 1)
                .setModifierCloneSlot(5)
        );
    }
}
