package me.endistic.skyblock.quests;

import me.endistic.skyblock.SkyBlock;
import me.endistic.skyblock.data.DataStorage;
import me.endistic.skyblock.mobs.CustomMob;
import me.endistic.skyblock.mobs.MobDatabase;
import me.endistic.skyblock.mobs.SpawningMetadata;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

public class SlayerQuest {
    public CustomMob mobToSpawn;
    public int xpGot;
    public int xpRequired;

    public void addXp(int xp, Player p) {
        this.xpGot += xp;
        if(this.xpGot >= this.xpRequired) {
            DataStorage.quest.remove(p.getUniqueId());

            var l = p.getLocation();

            p.sendTitle(
                "",
                ChatColor.RED + "" + ChatColor.BOLD + "BOSS SPAWNING",
                0,
                30,
                5
            );

            for(var i = 0; i<40; i += 5) {
                Bukkit.getScheduler().scheduleSyncDelayedTask(SkyBlock.main, () -> {
                    p.spawnParticle(
                        Particle.SMOKE,
                        l,
                        5,
                        0.1, 0.1, 0.1,
                        0
                    );
                }, i);
            }
            Bukkit.getScheduler().scheduleSyncDelayedTask(SkyBlock.main, () -> {
                MobDatabase.spawnCustomMob(
                    mobToSpawn.getId(),
                    l,
                    new SpawningMetadata()
                        .setSpawnId(-1)
                        .setOwner(p)
                        .setSummonedBy(null)
                );
            }, 40);

        }
    }
}
