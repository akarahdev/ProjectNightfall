package me.endistic.skyblock.utils;

import me.endistic.skyblock.SkyBlock;
import me.endistic.skyblock.packets.PacketUtils;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_20_R4.entity.CraftPlayer;
import org.bukkit.entity.Entity;

public class DisguiseMob {
    public static void disguise(Entity bukkitEntity, net.minecraft.world.entity.Entity base) {
        Bukkit.getScheduler().scheduleSyncDelayedTask(SkyBlock.main, () -> {
            for(var p : Bukkit.getOnlinePlayers()) {
                var p1 = PacketUtils.destroyEntity(bukkitEntity);
                var p2 = PacketUtils.spawnEntity(
                    base,
                    bukkitEntity.getEntityId()
                );

                // NMS:
                // send the packets to the player
                // `c` is an instance of `PlayerConnection`
                Bukkit.broadcastMessage(String.valueOf(((CraftPlayer) p).getHandle().c));
                ((CraftPlayer) p).getHandle().c.b(p1);
                ((CraftPlayer) p).getHandle().c.b(p2);
            }
        }, 1);

    }
}
