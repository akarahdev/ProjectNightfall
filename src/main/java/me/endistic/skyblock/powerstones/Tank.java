package me.endistic.skyblock.powerstones;

import me.endistic.skyblock.stats.StatsObject;
import org.bukkit.Material;

public class Tank extends PowerStone {
    @Override
    public StatsObject getBasePerkStats() {
        return new StatsObject()
            .setDefense(200);
    }

    @Override
    public StatsObject getMultipliedPerkStats() {
        return new StatsObject()
            .setMaxHealth(50)
            .setDefense(40);
    }

    @Override
    public PowerStoneIcon generateIcon() {
        return new PowerStoneIcon()
            .setName("Tank")
            .setMaterial(Material.SHIELD)
            .setId("tank");
    }
}
