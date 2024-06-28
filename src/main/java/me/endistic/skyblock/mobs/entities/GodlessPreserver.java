package me.endistic.skyblock.mobs.entities;

import me.endistic.skyblock.inventories.utils.BestiaryFilter;
import me.endistic.skyblock.inventories.utils.MobInventoryData;
import me.endistic.skyblock.mobs.CustomMob;
import me.endistic.skyblock.mobs.MobId;
import me.endistic.skyblock.mobs.drops.DropTable;
import org.bukkit.Material;
import org.bukkit.entity.*;

import java.util.ArrayList;

public class GodlessPreserver implements CustomMob {

    @Override
    public EntityType getEntityType() {
        return EntityType.PILLAGER;
    }

    @Override
    public int getLevel() {
        return 60;
    }

    @Override
    public String getName() {
        return "Godless Preserver";
    }

    @Override
    public double getMeleeDamage() {
        return 9000;
    }


    @Override
    public MobId getId() {
        return MobId.GODLESS_PRESERVER;
    }

    @Override
    public DropTable getDropTable() {
        return new DropTable();
    }

    @Override
    public double getHealth() {
        return 210000;
    }

    @Override
    public int getCombatXp() {
        return 110;
    }

    @Override
    public MobInventoryData getBestiaryData() {
        return new MobInventoryData()
            .setMobBase(this)
            .setAbilities(new ArrayList<>())
            .setSlayerQuest(null)
            .setSpawnEgg(Material.PILLAGER_SPAWN_EGG);
    }

    @Override
    public BestiaryFilter getBestiaryFilter() {
        return BestiaryFilter.MOB;
    }
}
