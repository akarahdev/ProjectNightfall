package me.endistic.skyblock.abilities.entity;

import me.endistic.skyblock.SkyBlock;
import me.endistic.skyblock.abilities.Ability;
import me.endistic.skyblock.abilities.CastAbility;
import me.endistic.skyblock.abilities.player.PlayerAbilityDescription;
import me.endistic.skyblock.mobs.MobUtils;
import me.endistic.skyblock.mobs.entities.EmpyrealAspect;
import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;

public class ShardOfTheDragonGod implements Ability {
    @Override
    public int getDelay() {
        return 200;
    }

    @Override
    public void castSpell(LivingEntity e) {
        MobUtils.addInvulnerable(e);

        Bukkit.getScheduler().scheduleSyncDelayedTask(SkyBlock.main, () -> {
            MobUtils.removeInvulnerable(e);
        }, 100);

        for(var i = 0; i<5; i++) {
            Bukkit.getScheduler().scheduleSyncDelayedTask(SkyBlock.main, () -> {
                CastAbility.cast(e, new SpawnMinion(new EmpyrealAspect(), 2));
            }, i*20);
        }
    }

    @Override
    public PlayerAbilityDescription getDescription() {
        return null;
    }
}
