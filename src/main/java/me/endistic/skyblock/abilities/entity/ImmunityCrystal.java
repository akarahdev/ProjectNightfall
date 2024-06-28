package me.endistic.skyblock.abilities.entity;

import me.endistic.skyblock.SkyBlock;
import me.endistic.skyblock.abilities.Ability;
import me.endistic.skyblock.abilities.player.PlayerAbilityDescription;
import me.endistic.skyblock.mobs.MobDatabase;
import me.endistic.skyblock.mobs.MobId;
import me.endistic.skyblock.mobs.MobTrigger;
import me.endistic.skyblock.mobs.SpawningMetadata;
import me.endistic.skyblock.mobs.MobUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.*;

public class ImmunityCrystal implements Ability {
    @Override
    public int getDelay() {
        return 200;
    }

    @Override
    public void castSpell(LivingEntity e) {

        if (MobUtils.isInvulnerable(e))
            return;

        for(var i = 0; i<3; i++) {
            Location loc = e.getLocation().add(0, -5, 0);
            while (loc.getBlock().getType() != Material.AIR) {
                loc = e.getLocation();
                loc.add(
                    (Math.random() * 24) - 12,
                    0,
                    (Math.random() * 24) - 12
                );
            }
            while (loc.getBlock().getType() == Material.AIR) {
                loc.add(0, -1, 0);
            }
            loc.add(0, 1, 0);

            Location finalLoc = loc;
            Bukkit.getScheduler().scheduleSyncDelayedTask(SkyBlock.main, () -> {
                var crystal = MobDatabase.spawnCustomMob(
                    MobId.ENDBLIGHT_SUBSOUL,
                    finalLoc,
                    new SpawningMetadata()
                        .setOwner(MobUtils.getOwner(e))
                        .setSpawnId(-1)
                        .setSummonedBy(e)
                );
                MobUtils.addInvulnerable(e);
                MobTrigger.runOnDeath(crystal, () -> {
                    Bukkit.getScheduler().scheduleSyncDelayedTask(SkyBlock.main, () -> {
                        if (
                            finalLoc.getWorld()
                                .getEntities()
                                .stream().filter(
                                    it -> MobUtils.getStringValue(it, "id").equals(MobId.ENDBLIGHT_SUBSOUL.toString())
                                        && MobUtils.getOwner(it).getUniqueId() == MobUtils.getOwner(e).getUniqueId())
                                .count() == 0
                        )
                            MobUtils.removeInvulnerable(e);
                    }, 10);
                });
            });
        }

    }

    @Override
    public PlayerAbilityDescription getDescription() {
        return null;
    }
}
