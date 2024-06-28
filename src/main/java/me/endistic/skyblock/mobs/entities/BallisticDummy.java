package me.endistic.skyblock.mobs.entities;

import me.endistic.skyblock.inventories.utils.BestiaryFilter;
import me.endistic.skyblock.inventories.utils.MobInventoryData;
import me.endistic.skyblock.mobs.CustomMob;
import me.endistic.skyblock.mobs.MobId;
import me.endistic.skyblock.mobs.drops.DropTable;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;

import java.util.ArrayList;

public class BallisticDummy implements CustomMob {

    @Override
    public EntityType getEntityType() {
        return EntityType.ZOMBIE;
    }

    @Override
    public int getLevel() {
        return 5;
    }

    @Override
    public String getName() {
        return "Ballistic Dummy";
    }

    @Override
    public double getMeleeDamage() {
        return 500;
    }


    @Override
    public MobId getId() {
        return MobId.BALLISTIC_DUMMY;
    }

    @Override
    public DropTable getDropTable() {
        return new DropTable();
    }

    @Override
    public double getHealth() {
        return 5000;
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
            .setSpawnEgg(Material.ZOMBIE_SPAWN_EGG);
    }

    @Override
    public BestiaryFilter getBestiaryFilter() {
        return BestiaryFilter.MOB;
    }
}
