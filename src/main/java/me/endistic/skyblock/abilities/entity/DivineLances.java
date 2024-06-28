package me.endistic.skyblock.abilities.entity;

import me.endistic.skyblock.SkyBlock;
import me.endistic.skyblock.abilities.Ability;
import me.endistic.skyblock.abilities.player.PlayerAbilityDescription;
import me.endistic.skyblock.utils.PlayerHitbox;
import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.entity.LivingEntity;
import org.bukkit.util.Vector;

public class DivineLances implements Ability {
    @Override
    public int getDelay() {
        return 40;
    }

    @Override
    public void castSpell(LivingEntity e) {
        for(var x = 0; x<8; x++) {
            var locVector = new Vector(
                Math.random()-0.5,
                0,
                Math.random()-0.5
            ).multiply((Math.random()*5)+15);



            for(var i = 0; i<10; i++) {
                Bukkit.getScheduler().scheduleSyncDelayedTask(SkyBlock.main, () -> {
                    var loc = e.getLocation().add(0, 1, 0);
                    var div = locVector.clone().divide(new Vector(5, 5, 5));
                    for(var j = 0; j<5; j++) {
                        loc.add(div);
                        for(var p : Bukkit.getOnlinePlayers()) {
                            p.spawnParticle(
                                Particle.BUBBLE,
                                loc,
                                2,
                                0, 0, 0,
                                0
                            );
                        }
                    }
                }, (i*2));
            }
            Bukkit.getScheduler().scheduleSyncDelayedTask(SkyBlock.main, () -> {
                var loc = e.getLocation().add(0, 1, 0);
                var div = locVector.clone().divide(new Vector(5, 5, 5));
                for(var j = 0; j<5; j++) {
                    loc.add(div);
                    for(var p : Bukkit.getOnlinePlayers()) {
                        p.spawnParticle(
                            Particle.FLAME,
                            loc,
                            1,
                            0, 0, 0,
                            0
                        );
                        if(PlayerHitbox.isInHitbox(loc, p.getLocation()))
                            p.damage(10000);
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
