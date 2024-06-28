package me.endistic.skyblock.items.gear.entropy;

import me.endistic.skyblock.items.*;
import me.endistic.skyblock.items.crafting.Recipe;
import me.endistic.skyblock.stats.StatsObject;
import org.bukkit.Color;
import org.bukkit.Material;

import java.util.List;

public class EntropicBoots extends CustomItemTemplate {
    @Override
    public ItemMetadata getItemData() {
        return new ItemMetadata()
            .setMaterial(Material.LEATHER_BOOTS)
            .setName("Entropic Boots")
            .setType(ItemSlot.BOOTS)
            .setRarity(Rarity.LEGENDARY)
            .setArmorColor(Color.BLUE);
    }

    @Override
    public StatsObject getStats() {
        return new StatsObject()
            .setMaxHealth(130)
            .setDefense(40)
            .setCriticalDamage(30)
            .setWalkSpeed(5)
            .setMeleeDamagePct(-5);
    }

    @Override
    public String getId() {
        return "entropic_boots";
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
                .setSlot(5, "silence_boots", 1)
                .setModifierCloneSlot(5),
            new Recipe()
                .setSlot(2, "entropic_fragment", 1)
                .setSlot(5, "conqueror_boots", 1)
                .setModifierCloneSlot(5)
        );
    }
}
