package me.endistic.skyblock.abilities.entity;

import me.endistic.skyblock.SkyBlock;
import me.endistic.skyblock.abilities.Ability;
import me.endistic.skyblock.abilities.AbilityUtils;
import me.endistic.skyblock.abilities.player.PlayerAbilityDescription;
import me.endistic.skyblock.mobs.MobUtils;
import org.bukkit.*;
import org.bukkit.entity.LivingEntity;

public class Uproot implements Ability {
    @Override
    public int getDelay() {
        return 50;
    }

    @Override
    public void castSpell(LivingEntity e) {
        for (var x = 0; x < 3; x++) {
            Bukkit.getScheduler().scheduleSyncDelayedTask(SkyBlock.main, () -> {
                var yaw = (float) Math.random() * 360;

                var newLoc = e.getLocation();
                newLoc.setYaw(yaw);
                newLoc.setPitch(0);
                newLoc.add(0, 0.5, 0);

                AbilityUtils.customProjectile(
                    newLoc,
                    2,
                    10,
                    2000,
                    Particle.BLOCK,
                    Material.OAK_WOOD.createBlockData(),
                    MobUtils.getOwner(e)
                );
            });
        }



    }

    @Override
    public PlayerAbilityDescription getDescription() {
        return null;
    }
}
