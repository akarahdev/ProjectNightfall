package me.endistic.skyblock.abilities.entity;

import me.endistic.skyblock.SkyBlock;
import me.endistic.skyblock.abilities.Ability;
import me.endistic.skyblock.abilities.player.PlayerAbilityDescription;
import me.endistic.skyblock.utils.PlayerHitbox;
import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.entity.*;
import org.bukkit.util.Vector;

public class Explosions implements Ability {
    @Override
    public int getDelay() {
        return 15;
    }

    @Override
    public void castSpell(LivingEntity e) {
        for (var i = 0; i < 3; i++) {
            var tnt = (TNTPrimed) e.getEyeLocation().getWorld().spawnEntity(
                e.getEyeLocation(),
                EntityType.TNT
            );
            tnt.setFuseTicks(1000000);
            tnt.setSource(e);
            tnt.setVelocity(new Vector(
                (Math.random() * 2) - 1, 0.25,
                (Math.random() * 2) - 1));

            Bukkit.getScheduler().scheduleSyncDelayedTask(SkyBlock.main, () -> {
                var loc = tnt.getLocation();
                tnt.remove();
                for (var p : Bukkit.getOnlinePlayers()) {
                    p.spawnParticle(
                        Particle.EXPLOSION,
                        loc,
                        1
                    );
                    if (PlayerHitbox.isInHitbox(loc, p.getLocation())) {
                        p.damage(5000);
                    }
                }



            }, 30);


        }

    }

    @Override
    public PlayerAbilityDescription getDescription() {
        return null;
    }
}
