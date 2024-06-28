package me.endistic.skyblock.mobs;

import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class MobLoop {
    public static void tickLoop() {

        for(var w : Bukkit.getWorlds()) {
            for(var e : w.getEntities()) {
                if(e instanceof LivingEntity le
                && !(e instanceof Player)) {
                    var id = MobUtils.getStringValue(le, "id");
                    MobId mobId;
                    try {
                        mobId = MobId.valueOf(id);
                    } catch (IllegalArgumentException ex) { continue; }

                    var mob = MobDatabase.get(mobId);

                    if(mob == null)
                        continue;

                    e.setFireTicks(0);
                    e.setVisualFire(false);
                }
            }
        }
    }
}
