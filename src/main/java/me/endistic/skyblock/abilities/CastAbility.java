package me.endistic.skyblock.abilities;

import me.endistic.skyblock.SkyBlock;
import org.bukkit.entity.LivingEntity;
import org.bukkit.scheduler.BukkitRunnable;

public class CastAbility {
    public static void cast(LivingEntity e, Ability a) {
        new BukkitRunnable() {
            @Override
            public void run() {
                if(e.getHealth() <= 10 || e.isDead()) {
                    this.cancel();
                    return;
                }

                a.castSpell(e);

                if(e.getHealth() <= 10 || e.isDead()) {
                    this.cancel();
                }
            }
        }.runTaskTimer(SkyBlock.main, a.getDelay(), a.getDelay());
    }
}
