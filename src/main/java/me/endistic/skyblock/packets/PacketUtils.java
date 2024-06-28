package me.endistic.skyblock.packets;

import net.minecraft.network.chat.IChatBaseComponent;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundSystemChatPacket;
import net.minecraft.network.protocol.game.PacketPlayOutEntityDestroy;
import net.minecraft.network.protocol.game.PacketPlayOutSpawnEntity;
import net.minecraft.world.level.World;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_20_R4.CraftWorld;
import org.bukkit.craftbukkit.v1_20_R4.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class PacketUtils {
    public static PacketPlayOutEntityDestroy destroyEntity(Entity entity) {
        return new PacketPlayOutEntityDestroy(entity.getEntityId());
    }

    public static PacketPlayOutSpawnEntity spawnEntity(
        net.minecraft.world.entity.Entity entity
    ) {
        return new PacketPlayOutSpawnEntity(entity);
    }

    public static PacketPlayOutSpawnEntity spawnEntity(
        net.minecraft.world.entity.Entity entity,
        int id
    ) {
        var packet = new PacketPlayOutSpawnEntity(entity);
        try {
            var field = packet.getClass().getDeclaredField("c");
            field.setAccessible(true);
            field.set(packet, id);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return packet;
    }

    public static ClientboundSystemChatPacket systemChatPacket(IChatBaseComponent component) {
        return new ClientboundSystemChatPacket(component, true);
    }

    public static World getLocationWorld(Location loc) {
        return ((CraftWorld) loc.getWorld()).getHandle();
    }

    public static void sendPacket(Player p, Packet<?> packet) {
        ((CraftPlayer) p).getHandle().c.b(packet);
    }
}
