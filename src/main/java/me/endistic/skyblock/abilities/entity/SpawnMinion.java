package me.endistic.skyblock.abilities.entity;

import me.endistic.skyblock.abilities.Ability;
import me.endistic.skyblock.abilities.player.PlayerAbilityDescription;
import me.endistic.skyblock.mobs.CustomMob;
import me.endistic.skyblock.mobs.MobDatabase;
import me.endistic.skyblock.mobs.SpawningMetadata;
import me.endistic.skyblock.mobs.MobUtils;
import org.bukkit.entity.LivingEntity;

public class SpawnMinion implements Ability {
    @Override
    public int getDelay() {
        return 300;
    }

    public CustomMob mob;
    public int amount;

    public SpawnMinion(CustomMob mob, int amount) {
        this.mob = mob;
        this.amount = amount;
    }

    @Override
    public void castSpell(LivingEntity e) {
        for (var i = 0; i < amount; i++) {
            MobDatabase.spawnCustomMob(
                mob.getId(),
                e.getLocation(),
                new SpawningMetadata()
                    .setOwner(MobUtils.getOwner(e))
                    .setSpawnId(-1)
                    .setSummonedBy(e)
            );
        }


    }

    @Override
    public PlayerAbilityDescription getDescription() {
        return null;
    }
}
