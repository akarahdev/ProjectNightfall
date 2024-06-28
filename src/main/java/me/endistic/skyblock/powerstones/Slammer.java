package me.endistic.skyblock.powerstones;

import me.endistic.skyblock.stats.StatsObject;
import org.bukkit.Material;

public class Slammer extends PowerStone {
    @Override
    public StatsObject getBasePerkStats() {
        return new StatsObject()
            .setCriticalChance(20)
            .setBonusAttackSpeed(-60);
    }

    @Override
    public StatsObject getMultipliedPerkStats() {
        return new StatsObject()
            .setStrength(70)
            .setCriticalDamage(30)
            .setCriticalChance(10)
            .setBonusAttackSpeed(-40)
            .setFerocity(20)
            .setArrowCount(-3)
            .setArrowSpeed(-300);
    }

    @Override
    public PowerStoneIcon generateIcon() {
        return new PowerStoneIcon()
            .setName("Slammer")
            .setMaterial(Material.NETHERITE_AXE)
            .setId("slammer");
    }
}
