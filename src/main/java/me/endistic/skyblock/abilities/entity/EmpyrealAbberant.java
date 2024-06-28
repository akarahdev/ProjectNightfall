package me.endistic.skyblock.abilities.entity;

import me.endistic.skyblock.SkyBlock;
import me.endistic.skyblock.abilities.Ability;
import me.endistic.skyblock.abilities.player.PlayerAbilityDescription;
import me.endistic.skyblock.mobs.MobUtils;
import me.endistic.skyblock.utils.PlayerHitbox;
import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.LivingEntity;

public class EmpyrealAbberant implements Ability {
    @Override
    public int getDelay() {
        return 100;
    }

    @Override
    public void castSpell(LivingEntity e) {
        final org.bukkit.Location[] loc = {e.getEyeLocation()};

        var owner = MobUtils.getOwner(e);

        for(var i = 0; i<20; i++) {
            Bukkit.getScheduler().scheduleSyncDelayedTask(SkyBlock.main, () -> {
                var dist = loc[0]
                    .toVector()
                    .subtract(
                        owner
                            .getEyeLocation()
                            .toVector()
                    )
                    .multiply(-0.5)
                    .normalize();

                loc[0].add(dist);

                for(var p : Bukkit.getOnlinePlayers()) {
                    p.spawnParticle(
                        Particle.FLAME,
                        loc[0],
                        6,
                        0.25, 0.25, 0.25,
                        0
                    );

                    p.playSound(
                        loc[0],
                        Sound.ITEM_FIRECHARGE_USE,
                        0.1f,
                        1f
                    );

                    if(PlayerHitbox.isInHitbox(loc[0], p.getLocation())) {
                        p.damage(4000);
                    }
                }
            }, i*2);
        }
    }

    @Override
    public PlayerAbilityDescription getDescription() {
        return null;
    }
}
