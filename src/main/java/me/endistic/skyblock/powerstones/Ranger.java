package me.endistic.skyblock.powerstones;

import me.endistic.skyblock.stats.StatsObject;
import org.bukkit.Material;

public class Ranger extends PowerStone {
    @Override
    public StatsObject getBasePerkStats() {
        return new StatsObject()
            .setArrowSpeed(200);
    }

    @Override
    public StatsObject getMultipliedPerkStats() {
        return new StatsObject()
            .setCriticalDamage(50)
            .setCriticalChance(10)
            .setArrowSpeed(40)
            .setStrength(30);
    }

    @Override
    public PowerStoneIcon generateIcon() {
        return new PowerStoneIcon()
            .setName("Ranger")
            .setMaterial(Material.CROSSBOW)
            .setId("ranger");
    }
}
