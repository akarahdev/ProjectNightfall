package me.endistic.skyblock.items.gear.entropy;

import me.endistic.skyblock.items.*;
import me.endistic.skyblock.items.crafting.Recipe;
import me.endistic.skyblock.stats.StatsObject;
import org.bukkit.Material;

import java.util.List;

public class EntropicHelmet extends CustomItemTemplate {
    @Override
    public ItemMetadata getItemData() {
        return new ItemMetadata()
            .setMaterial(Material.PLAYER_HEAD)
            .setName("Entropic Helmet")
            .setType(ItemSlot.HELMET)
            .setRarity(Rarity.LEGENDARY)
            .setTexture(ItemTexture.ENTROPIC_HELMET);
    }

    @Override
    public StatsObject getStats() {
        return new StatsObject()
            .setMaxHealth(160)
            .setDefense(50)
            .setCriticalDamage(30)
            .setRangedDamagePct(10)
            .setMeleeDamagePct(-5);
    }

    @Override
    public String getId() {
        return "entropic_helmet";
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
                .setSlot(5, "silence_helmet", 1)
                .setModifierCloneSlot(5),
            new Recipe()
                .setSlot(2, "entropic_fragment", 1)
                .setSlot(5, "conqueror_helmet", 1)
                .setModifierCloneSlot(5)
        );
    }
}
