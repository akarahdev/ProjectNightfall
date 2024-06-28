package me.endistic.skyblock.powerstones;

import me.endistic.skyblock.stats.StatsObject;
import org.bukkit.inventory.ItemStack;

public class PowerStone {

    public StatsObject getMultipliedPerkStats() {
        return new StatsObject();
    }
    public StatsObject getBasePerkStats() {
        return new StatsObject();
    }

    public static double mpToMultiplier(int magicalPower) {
        return ((719.28*Math.pow(Math.log(1+(0.0019 * magicalPower)), 1.2))/100)+1;
    }

    public static PowerStone idToStone(String id) {
        switch(id) {
            case "berserker" -> { return new Berserker(); }
            case "boltslinger" -> { return new Boltslinger(); }
            case "ranger" -> { return new Ranger(); }
            case "tank" -> { return new Tank(); }
            case "magician" -> { return new Magician(); }
            case "slammer" -> { return new Slammer(); }
            case "knight" -> { return new Knight(); }
            default -> { return new EmptyStone(); }

        }
    }

    public PowerStoneIcon generateIcon() {
        return new PowerStoneIcon();
    }
}
