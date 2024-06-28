package me.endistic.skyblock.abilities.entity;

import me.endistic.skyblock.SkyBlock;
import me.endistic.skyblock.abilities.Ability;
import me.endistic.skyblock.abilities.player.PlayerAbilityDescription;
import me.endistic.skyblock.mobs.MobUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.LivingEntity;

public class Searcher implements Ability {
    @Override
    public int getDelay() {
        return 300;
    }

    @Override
    public void castSpell(LivingEntity e) {
        Location loc = e.getLocation().add(0, -5, 0);
        while(loc.getBlock().getType() != Material.AIR) {
            loc = e.getLocation();
            loc.add(
                (Math.random() * 14) - 7,
                0,
                (Math.random() * 14) - 7
            );
        }
        while(loc.getBlock().getType() == Material.AIR) {
            loc.add(0, -1, 0);
        }
        loc.add(0, 1, 0);

        Location finalLoc = loc;
        Bukkit.getScheduler().scheduleSyncDelayedTask(SkyBlock.main, () -> {
            finalLoc.getBlock().setType(Material.BEACON);
            for(var i = 0; i<6; i++) {
                int finalI = i;
                Bukkit.getScheduler().scheduleSyncDelayedTask(SkyBlock.main, () -> {
                    var cloc = finalLoc.clone();
                    for(var p = 0; p<360; p += 36) {
                        cloc.setYaw(p);
                        cloc.add(
                            cloc.getDirection()
                                .multiply(finalI/2)
                        );
                        for(var pl : Bukkit.getOnlinePlayers()) {
                            pl.spawnParticle(
                                Particle.ENCHANT,
                                cloc,
                                1,
                                0, 0, 0,
                                0
                            );
                        }
                    }
                }, finalI*10);
                e.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0);
            }
            Bukkit.getScheduler().scheduleSyncDelayedTask(SkyBlock.main, () -> {
                var owner = MobUtils.getOwner(e);
                if(!(owner.getLocation().distanceSquared(finalLoc) <= 25))
                    owner.damage(1000000);
                finalLoc.getBlock().setType(Material.AIR);
                e.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.03);
            }, 65);

        });
    }

    @Override
    public PlayerAbilityDescription getDescription() {
        return null;
    }
}
