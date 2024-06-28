package me.endistic.skyblock.mobs;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.UUID;

public class SpawningMetadata {
    public Player owner;

    public Player getOwner() {
        return owner;
    }

    public SpawningMetadata setOwner(Player owner) {
        this.owner = owner;
        return this;
    }

    public int getSpawnId() {
        return spawnId;
    }

    public SpawningMetadata setSpawnId(int spawnId) {
        this.spawnId = spawnId;
        return this;
    }

    public LivingEntity getSummonedBy() {
        return summonedBy;
    }

    public SpawningMetadata setSummonedBy(LivingEntity summonedBy) {
        this.summonedBy = summonedBy;
        return this;
    }

    public int spawnId = 0;
    public LivingEntity summonedBy;
}
