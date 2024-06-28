package me.endistic.skyblock.powerstones;

import me.endistic.skyblock.stats.StatsObject;
import org.bukkit.Material;

public class EmptyStone extends PowerStone {
    @Override
    public PowerStoneIcon generateIcon() {
        return new PowerStoneIcon()
            .setName("Empty Stone")
            .setMaterial(Material.BARRIER)
            .setId("selected");
    }
}
