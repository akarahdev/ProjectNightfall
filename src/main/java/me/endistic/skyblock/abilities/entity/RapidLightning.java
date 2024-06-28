package me.endistic.skyblock.abilities.entity;

import me.endistic.skyblock.SkyBlock;
import me.endistic.skyblock.abilities.Ability;
import me.endistic.skyblock.abilities.player.PlayerAbilityDescription;
import me.endistic.skyblock.mobs.MobUtils;
import me.endistic.skyblock.utils.PlayerHitbox;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class RapidLightning implements Ability {
    @Override
    public int getDelay() {
        return 250;
    }

    @Override
    public void castSpell(LivingEntity e) {
        Location loc = e.getLocation().add(0, 5, 0);
        e.teleport(loc);
        e.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 100, 120));

        for(var i = 0; i<10; i++) {
            Bukkit.getScheduler().scheduleSyncDelayedTask(SkyBlock.main, () -> {
                var owner = MobUtils.getOwner(e);
                var strikeLoc = owner.getLocation();
                strikeLoc.getWorld().spawnParticle(
                    Particle.SONIC_BOOM,
                    strikeLoc,
                    1
                );
                Bukkit.getScheduler().scheduleSyncDelayedTask(SkyBlock.main, () -> {
                    if(PlayerHitbox.isInHitbox(loc, owner.getLocation())) {
                        owner.damage(5000);
                    }
                }, 12);
            }, i*10);
        }
        loc.add(0, -5, 0);
    }

    @Override
    public PlayerAbilityDescription getDescription() {
        return null;
    }
}
