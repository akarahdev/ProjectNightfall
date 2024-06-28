package me.endistic.skyblock.powerstones;

import me.endistic.skyblock.stats.StatsObject;
import org.bukkit.Material;

public class Berserker extends PowerStone {
    @Override
    public StatsObject getBasePerkStats() {
        return new StatsObject()
            .setBonusAttackSpeed(10);
    }

    @Override
    public StatsObject getMultipliedPerkStats() {
        return new StatsObject()
            .setStrength(40)
            .setBonusAttackSpeed(5)
            .setDamage(-30)
            .setFerocity(7)
            .setLifesteal(10);
    }

    @Override
    public PowerStoneIcon generateIcon() {
        return new PowerStoneIcon()
            .setName("Berserker")
            .setMaterial(Material.DIAMOND_SWORD)
            .setId("berserker");
    }
}
