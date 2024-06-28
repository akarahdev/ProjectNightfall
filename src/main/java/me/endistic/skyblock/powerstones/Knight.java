package me.endistic.skyblock.powerstones;

import me.endistic.skyblock.stats.StatsObject;
import org.bukkit.Material;

public class Knight extends PowerStone {
    @Override
    public StatsObject getBasePerkStats() {
        return new StatsObject()
            .setStrength(50)
            .setCriticalDamage(50)
            .setCriticalChance(5);
    }

    @Override
    public StatsObject getMultipliedPerkStats() {
        return new StatsObject()
            .setStrength(30)
            .setCriticalDamage(20)
            .setLifesteal(5);
    }

    @Override
    public PowerStoneIcon generateIcon() {
        return new PowerStoneIcon()
            .setName("Knight")
            .setMaterial(Material.IRON_SWORD)
            .setId("knight");
    }
}
