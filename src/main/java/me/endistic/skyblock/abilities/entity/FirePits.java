package me.endistic.skyblock.abilities.entity;

import me.endistic.skyblock.SkyBlock;
import me.endistic.skyblock.abilities.Ability;
import me.endistic.skyblock.abilities.player.PlayerAbilityDescription;
import me.endistic.skyblock.mobs.MobUtils;
import me.endistic.skyblock.utils.PlayerHitbox;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.LivingEntity;
import org.bukkit.util.Vector;

public class FirePits implements Ability {
    @Override
    public int getDelay() {
        return 60;
    }

    @Override
    public void castSpell(LivingEntity e) {
        var o = MobUtils.getOwner(e);
        var cachedLoc = o.getLocation();
        while(cachedLoc.getWorld().getBlockAt(cachedLoc).getType() == Material.AIR) {
            cachedLoc = cachedLoc.add(new Vector(0, -1, 0));
        }
        cachedLoc = cachedLoc.add(new Vector(0, 1, 0));
        for(var x = 0; x<16; x++) {
            var locVector = new Vector(
                Math.random()-0.5,
                0,
                Math.random()-0.5
            ).multiply(15);

            var finalCachedLoc = cachedLoc;
            for(var i = 0; i<15; i++) {
                Bukkit.getScheduler().scheduleSyncDelayedTask(SkyBlock.main, () -> {
                    var loc = finalCachedLoc.add(locVector);
                    for(var p : Bukkit.getOnlinePlayers()) {
                        p.spawnParticle(
                            Particle.SMOKE,
                            loc,
                            2,
                            0.25, 0.25, 0.25,
                            0
                        );
                    }
                }, (i*2));
            }
            Bukkit.getScheduler().scheduleSyncDelayedTask(SkyBlock.main, () -> {
                var loc = finalCachedLoc.add(locVector);
                for(var j = 0; j<5; j++) {
                    for(var p : Bukkit.getOnlinePlayers()) {
                        p.spawnParticle(
                            Particle.FLAME,
                            loc,
                            5,
                            0.25, 0.25, 0.25,
                            0
                        );
                        if(PlayerHitbox.isInHitbox(loc, p.getLocation()))
                            p.damage(1000000);
                    }
                }
            }, 22);
        }

    }

    @Override
    public PlayerAbilityDescription getDescription() {
        return null;
    }
}
