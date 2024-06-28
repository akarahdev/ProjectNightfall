package me.endistic.skyblock.items.gear.entropy;

import me.endistic.skyblock.items.*;
import me.endistic.skyblock.items.crafting.Recipe;
import me.endistic.skyblock.stats.StatsObject;
import org.bukkit.Color;
import org.bukkit.Material;

import java.util.List;

public class EntropicChestplate extends CustomItemTemplate {
    @Override
    public ItemMetadata getItemData() {
        return new ItemMetadata()
            .setMaterial(Material.LEATHER_CHESTPLATE)
            .setName("Entropic Chestplate")
            .setType(ItemSlot.CHESTPLATE)
            .setRarity(Rarity.LEGENDARY)
            .setArmorColor(Color.BLUE);
    }

    @Override
    public StatsObject getStats() {
        return new StatsObject()
            .setMaxHealth(230)
            .setDefense(65)
            .setCriticalDamage(30)
            .setMeleeDamagePct(-5);
    }

    @Override
    public String getId() {
        return "entropic_chestplate";
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
                .setSlot(5, "silence_chestplate", 1)
                .setModifierCloneSlot(5),
            new Recipe()
                .setSlot(2, "entropic_fragment", 1)
                .setSlot(5, "conqueror_chestplate", 1)
                .setModifierCloneSlot(5)
        );
    }
}
