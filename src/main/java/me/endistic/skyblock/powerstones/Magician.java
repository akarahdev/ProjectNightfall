package me.endistic.skyblock.powerstones;

import me.endistic.skyblock.stats.StatsObject;
import org.bukkit.Material;

public class Magician extends PowerStone {
    @Override
    public StatsObject getBasePerkStats() {
        return new StatsObject()
            .setIntelligence(300);
    }

    @Override
    public StatsObject getMultipliedPerkStats() {
        return new StatsObject()
            .setIntelligence(120);
    }

    @Override
    public PowerStoneIcon generateIcon() {
        return new PowerStoneIcon()
            .setName("Magician")
            .setMaterial(Material.BLAZE_ROD)
            .setId("magician");
    }
}
