package me.endistic.skyblock.abilities.entity;

import me.endistic.skyblock.SkyBlock;
import me.endistic.skyblock.mobs.MobUtils;
import me.endistic.skyblock.abilities.Ability;
import me.endistic.skyblock.abilities.player.PlayerAbilityDescription;
import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.entity.LivingEntity;
import org.bukkit.util.Vector;

import java.util.UUID;

public class Dash implements Ability {
    @Override
    public int getDelay() {
        return 160;
    }

    @Override
    public void castSpell(LivingEntity e) {

        for(var p : Bukkit.getOnlinePlayers()) {
            p.spawnParticle(
                Particle.FLAME,
                e.getLocation(),
                10,
                0.75, 2, 0.75,
                0
            );
        }

        Bukkit.getScheduler().scheduleSyncDelayedTask(SkyBlock.main, () -> {
            e.setVelocity(new Vector(0, 0.5, 0));
            Bukkit.getScheduler().scheduleSyncDelayedTask(SkyBlock.main, () -> {
                var owner = MobUtils.getStringValue(e, "owner");
                var player = Bukkit.getPlayer(UUID.fromString(owner));
                if(player != null && player.isOnline()) {
                    var distance = e.getLocation().subtract(player.getLocation()).toVector();
                    e.setVelocity(distance.normalize().multiply(
                        -(e.getLocation().distance(player.getLocation()) / 2)
                    ));
                }
            }, 10);

        }, 20);




    }

    @Override
    public PlayerAbilityDescription getDescription() {
        return null;
    }
}
