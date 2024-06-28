package me.endistic.skyblock.powerstones;

import me.endistic.skyblock.stats.StatsObject;
import org.bukkit.Material;

public class Boltslinger extends PowerStone {
    @Override
    public StatsObject getBasePerkStats() {
        return new StatsObject()
            .setArrowCount(3)
            .setArrowSpeed(-100);
    }

    @Override
    public StatsObject getMultipliedPerkStats() {
        return new StatsObject()
            .setCriticalChance(5)
            .setCriticalDamage(30)
            .setStrength(30)
            .setWalkSpeed(33)
            .setArrowCount(1)
            .setArrowSpeed(-6);
    }

    @Override
    public PowerStoneIcon generateIcon() {
        return new PowerStoneIcon()
            .setName("Boltslinger")
            .setMaterial(Material.BOW)
            .setId("boltslinger");
    }
}
