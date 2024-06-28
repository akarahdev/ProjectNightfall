package me.endistic.skyblock.mobs;

import me.endistic.skyblock.SkyBlock;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.EntityTeleportEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.persistence.PersistentDataType;

public class MobEvents implements Listener {
    @EventHandler
    public void entityProjHit(ProjectileHitEvent e) {
        var pdc = e.getEntity().getPersistentDataContainer();
        if (e.getHitEntity() instanceof Projectile) {
            e.setCancelled(true);
            return;
        }
        if (pdc.has(SkyBlock.key("proj_dmg"))
            && e.getHitEntity() != null) {
            e.setCancelled(true);
            var dmg = pdc.get(SkyBlock.key("proj_dmg"), PersistentDataType.DOUBLE);
            if (e.getHitEntity() instanceof Player p) {
                p.damage(dmg);
            }
        }
    }

    @EventHandler
    public void entityProjShoot(ProjectileLaunchEvent e) {
        var shooter = e.getEntity().getShooter();
        if (shooter instanceof LivingEntity le) {
            var pdc = le.getPersistentDataContainer();
            if (pdc.has(SkyBlock.key("id"))) {
                var id = pdc.get(SkyBlock.key("id"), PersistentDataType.STRING);
                e.getEntity().getPersistentDataContainer().set(
                    SkyBlock.key("proj_dmg"),
                    PersistentDataType.DOUBLE,
                    MobDatabase.get(MobId.valueOf(id)).getMeleeDamage()
                );
            }
        }
    }

    @EventHandler
    public void tp(EntityTeleportEvent e) {
        if (e.getEntity().getPersistentDataContainer().has(SkyBlock.key("no_tp"))) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void hit(ProjectileHitEvent e) {
        e.getEntity().remove();
    }

    @EventHandler
    public void explosion(EntityExplodeEvent e) {
        e.setYield(0);
        e.setCancelled(true);
    }
}
