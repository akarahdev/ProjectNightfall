package me.endistic.skyblock.mobs.entities;

import me.endistic.skyblock.inventories.utils.BestiaryFilter;
import me.endistic.skyblock.inventories.utils.MobInventoryData;
import me.endistic.skyblock.mobs.CustomMob;
import me.endistic.skyblock.mobs.MobId;
import me.endistic.skyblock.mobs.drops.DropTable;
import org.bukkit.Material;
import org.bukkit.entity.*;

import java.util.ArrayList;

public class EmpyrealAspect implements CustomMob {
    @Override
    public EntityType getEntityType() {
        return EntityType.PIGLIN;
    }

    @Override
    public int getLevel() {
        return 1;
    }

    @Override
    public String getName() {
        return "Empyreal Aspect";
    }

    @Override
    public double getMeleeDamage() {
        return 10000;
    }


    @Override
    public MobId getId() {
        return MobId.EMPYREAL_ASPECT;
    }

    @Override
    public DropTable getDropTable() {
        return new DropTable();
    }

    @Override
    public double getHealth() {
        return 40000;
    }

    @Override
    public int getCombatXp() {
        return 0;
    }

    @Override
    public MobInventoryData getBestiaryData() {
        return new MobInventoryData()
            .setMobBase(this)
            .setAbilities(new ArrayList<>())
            .setSlayerQuest(null)
            .setSpawnEgg(Material.WARDEN_SPAWN_EGG);
    }

    @Override
    public BestiaryFilter getBestiaryFilter() {
        return BestiaryFilter.MOB;
    }
}
