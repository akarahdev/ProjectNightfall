package me.endistic.skyblock.mobs;

import me.endistic.skyblock.abilities.CastAbility;
import me.endistic.skyblock.mobs.entities.*;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;

import java.util.Collection;
import java.util.LinkedHashMap;

public class MobDatabase {
    public static void initializeMob(CustomMob mob) {
        mobDatabase.put(mob.getId(), mob);
    }

    private static final LinkedHashMap<MobId, CustomMob> mobDatabase = new LinkedHashMap<>();

    public static void init() {
        MobDatabase.initializeMob(new BallisticDummy());
        MobDatabase.initializeMob(new TheDheireadh());
        MobDatabase.initializeMob(new Wraithwood());
        MobDatabase.initializeMob(new OldGuardRevenant());
        MobDatabase.initializeMob(new ReaverCourtier());
        MobDatabase.initializeMob(new LordCalahann());
        MobDatabase.initializeMob(new TheLost());
        MobDatabase.initializeMob(new TheDamned());
        MobDatabase.initializeMob(new EndblightAvatar());
        MobDatabase.initializeMob(new EndblightSubsoul());
        MobDatabase.initializeMob(new Silversoul());
        MobDatabase.initializeMob(new TheExiflame());
        MobDatabase.initializeMob(new EmpyrealAspect());
        MobDatabase.initializeMob(new GodlessPreserver());
    }

    public static Entity spawnCustomMob(MobId mob, Location loc, SpawningMetadata metadata) {
        var customMobData = MobDatabase.get(mob);
        var entity = loc.getWorld().spawnEntity(loc, customMobData.getEntityType());

        if(entity instanceof LivingEntity le) {
            le.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(customMobData.getHealth());
            le.setHealth(customMobData.getHealth());

            customMobData.getEquipment(le.getEquipment());

            le.getAttribute(Attribute.GENERIC_FOLLOW_RANGE).setBaseValue(10);

            for(var a : customMobData.getAbilities()) {
                CastAbility.cast(le, a);
            }

            MobUtils.setValue(le, "id", customMobData.getId().toString());
            MobUtils.setValue(le, "spawn_id", metadata.getSpawnId());
            MobUtils.setValue(le, "is_c_mob", "yes");

            if(metadata.getOwner() != null)
                MobUtils.setOwner(le, metadata.getOwner());

            customMobData.runOnSpawn(le);
        }

        return entity;
    }

    public static CustomMob get(MobId id) {
        return MobDatabase.mobDatabase.get(id);
    }

    public static Collection<MobId> ids() {
        return MobDatabase.mobDatabase.keySet();
    }

    public static Collection<CustomMob> mobs() {
        return MobDatabase.mobDatabase.values();
    }


}
