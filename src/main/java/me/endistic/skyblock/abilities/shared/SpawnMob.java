package me.endistic.skyblock.abilities.shared;

import me.endistic.skyblock.SkyBlock;
import me.endistic.skyblock.abilities.Ability;
import me.endistic.skyblock.abilities.player.AbilityTrigger;
import me.endistic.skyblock.abilities.player.PlayerAbilityDescription;
import me.endistic.skyblock.mobs.CustomMob;
import me.endistic.skyblock.mobs.MobDatabase;
import me.endistic.skyblock.mobs.SpawningMetadata;
import me.endistic.skyblock.mobs.MobUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class SpawnMob implements Ability {

    public CustomMob mob;
    public SpawnMob(CustomMob mob, int manaCost) {
        this.mob = mob;
    }
    @Override
    public int getDelay() {
        return 200;
    }

    @Override
    public void castSpell(LivingEntity e) {
        Player owner;
        owner = MobUtils.getOwner(e);
        if(e instanceof Player p)
            owner = p;
        var finalOwner = owner;
        Bukkit.getScheduler().scheduleSyncDelayedTask(SkyBlock.main, () -> {
            MobDatabase.spawnCustomMob(
                mob.getId(),
                e.getLocation(),
                new SpawningMetadata()
                    .setOwner(finalOwner)
                    .setSpawnId(-1)
                    .setSummonedBy(null)
            );
        });

    }

    @Override
    public PlayerAbilityDescription getDescription() {
        return new PlayerAbilityDescription()
            .setName("Spawn [Lv" + mob.getLevel() + "] " + mob.getName())
            .setDescription("Spawn the enemy. Ask the "
                + ChatColor.RED + "Beastmaster " + ChatColor.GRAY + "for more information.")
            .setTrigger(AbilityTrigger.RIGHT_CLICK)
            .setManaCost(0);
    }
}
