package me.endistic.skyblock.abilities;

import me.endistic.skyblock.SkyBlock;
import me.endistic.skyblock.utils.PlayerHitbox;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class AbilityUtils {
    public static <T> void customProjectile(
        Location loc,
        int delay,
        int length,
        int damage,
        Particle particle,
        T data,
        Player owner
    ) {
        var nl = loc.clone();
        var locs = new ArrayList<Location>();
        var dir = nl.getDirection().normalize().clone();
        for (var i = 0; i < length; i++) {
            nl.add(dir);
            locs.add(nl.clone());
        }
        for (var i = 0; i < locs.size(); i++) {
            var fi = i;
            Bukkit.getScheduler().scheduleSyncDelayedTask(SkyBlock.main, () -> {
                var loc2 = locs.get(fi);
                for (var p : Bukkit.getOnlinePlayers()) {
                    p.spawnParticle(
                        particle,
                        loc2,
                        3,
                        0.25, 0.25, 0.25,
                        0,
                        data
                    );
                    if(PlayerHitbox.isInHitbox(loc, p.getLocation())) {
                        p.damage(damage);
                    }
                }
            }, (long) fi * delay);
        }
    }
}
