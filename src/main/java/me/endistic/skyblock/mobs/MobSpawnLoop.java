package me.endistic.skyblock.mobs;

import me.endistic.skyblock.mobs.entities.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.List;

public class MobSpawnLoop {
    public static void tickLoop() {
        var world = Bukkit.getWorld("world");

        assert world != null;

        MobUtils.trySpawn(
            new Wraithwood(),
            List.of(
                new Location(world, 102, 70, 99),
                new Location(world, 93, 70, 110),
                new Location(world, 85, 68, 151),
                new Location(world, 86, 69, 155),
                new Location(world, 59, 71, 114),
                new Location(world, 63, 71, 112),
                new Location(world, 63, 66, 144),
                new Location(world, 76, 71, 170),
                new Location(world, 102, 71, 156)
            )
        );

        MobUtils.trySpawn(
            new OldGuardRevenant(),
            List.of(
                new Location(world, -120, 72, -80),
                new Location(world, -140, 72, -90),
                new Location(world, -162, 73, -100),
                new Location(world, -140, 73, -125),
                new Location(world, -108, 73, -139),
                new Location(world, -89, 74, -112),
                new Location(world, -90, 73, -152),
                new Location(world, -100, 75, -174),
                new Location(world, -121, 73, -140)
            )
        );

        MobUtils.trySpawn(
            new TheLost(),
            List.of(
                new Location(world, -217, 92, 61),
                new Location(world, -215, 92, 75),
                new Location(world, -222, 93, 87),
                new Location(world, -240, 94, 100)
            )
        );

        MobUtils.trySpawn(
            new TheDamned(),
            List.of(
                new Location(world, -276, 91, 98),
                new Location(world, -275, 92, 81)
            )
        );

        MobUtils.trySpawn(
            new Silversoul(),
            List.of(
                new Location(world, -9, 74, 138),
                new Location(world, 8, 74, 151),
                new Location(world, -18, 72, 170),
                new Location(world, -42, 73, 161),
                new Location(world, -49, 75, 135),
                new Location(world, -4, 71, 172),
                new Location(world, -43, 72, 165),
                new Location(world, -5, 74, 121),
                new Location(world, -18, 72, 152)
            )
        );

        MobUtils.trySpawn(
            new GodlessPreserver(),
            List.of(
                new Location(world, -30, 64, 80),
                new Location(world, -28, 64, 82),
                new Location(world, -13, 64, 80),
                new Location(world, -14, 64, 83),
                new Location(world, -22, 52, 114),
                new Location(world, -24, 52, 111),
                new Location(world, -22, 52, 46),
                new Location(world, -24, 52, 48),
                new Location(world, 29, 52, 80),
                new Location(world, 32, 52, 77)
            )
        );


    }


}
