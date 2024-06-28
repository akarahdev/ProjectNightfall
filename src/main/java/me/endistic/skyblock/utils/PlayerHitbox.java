package me.endistic.skyblock.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class PlayerHitbox {
    public static boolean isInHitbox(
        Location point,
        Location playerLocation
    ) {
        var minX = playerLocation.getX()-0.5;
        var maxX = playerLocation.getX()+0.5;
        var minY = playerLocation.getY();
        var maxY = playerLocation.getY()+2;
        var minZ = playerLocation.getZ()-0.5;
        var maxZ = playerLocation.getZ()+0.5;

        return
            minX <= point.getX() && point.getX() <= maxX
            && minY <= point.getY() && point.getY() <= maxY
            && minZ <= point.getZ() && point.getZ() <= maxZ;
    }
}
