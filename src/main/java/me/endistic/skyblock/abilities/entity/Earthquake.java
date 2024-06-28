package me.endistic.skyblock.abilities.entity;

import me.endistic.skyblock.SkyBlock;
import me.endistic.skyblock.abilities.Ability;
import me.endistic.skyblock.abilities.AbilityUtils;
import me.endistic.skyblock.abilities.player.PlayerAbilityDescription;
import me.endistic.skyblock.mobs.MobUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.LivingEntity;

public class Earthquake implements Ability {
    @Override
    public int getDelay() {
        return 300;
    }

    @Override
    public void castSpell(LivingEntity e) {


        for(var p : Bukkit.getOnlinePlayers()) {
            p.spawnParticle(
                Particle.BLOCK,
                e.getLocation(),
                10,
                0.75, 2, 0.75,
                0,
                Material.SPRUCE_WOOD.createBlockData()
            );
        }
        Bukkit.getScheduler().scheduleSyncDelayedTask(SkyBlock.main, () -> {
            var center = e.getLocation();
            for(var p = 0; p<360; p += 36) {
                var ll = center.clone();
                ll.setPitch(0);
                ll.setYaw(p);
                Bukkit.getScheduler().scheduleSyncDelayedTask(SkyBlock.main, () -> {
                    AbilityUtils.customProjectile(
                        ll,
                        3,
                        12,
                        3000,
                        Particle.BLOCK,
                        Material.SPRUCE_WOOD.createBlockData(),
                        MobUtils.getOwner(e)
                    );
                });
            }
        }, 20);


    }

    @Override
    public PlayerAbilityDescription getDescription() {
        return null;
    }
}
