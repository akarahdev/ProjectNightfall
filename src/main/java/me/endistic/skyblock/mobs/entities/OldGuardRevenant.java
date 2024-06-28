package me.endistic.skyblock.mobs.entities;

import me.endistic.skyblock.inventories.utils.BestiaryFilter;
import me.endistic.skyblock.inventories.utils.MobInventoryData;
import me.endistic.skyblock.mobs.CustomMob;
import me.endistic.skyblock.mobs.MobId;
import me.endistic.skyblock.mobs.drops.DropTable;
import org.bukkit.Material;
import org.bukkit.entity.*;

import java.util.ArrayList;

public class OldGuardRevenant implements CustomMob {
    @Override
    public EntityType getEntityType() {
        return EntityType.WITHER_SKELETON;
    }

    @Override
    public int getLevel() {
        return 15;
    }

    @Override
    public String getName() {
        return "Old Guard Revenant";
    }

    @Override
    public double getMeleeDamage() {
        return 3000;
    }


    @Override
    public MobId getId() {
        return MobId.OLD_GUARD_REVENANT;
    }

    @Override
    public DropTable getDropTable() {
        return new DropTable()
            .putDrop("reaver_fragment", 1.0)
            .putDrop("reaver_core", 1/4.0);
    }

    @Override
    public double getHealth() {
        return 17000;
    }

    @Override
    public int getCombatXp() {
        return 24;
    }

    @Override
    public MobInventoryData getBestiaryData() {
        return new MobInventoryData()
            .setMobBase(this)
            .setAbilities(new ArrayList<>())
            .setSlayerQuest(null)
            .setSpawnEgg(Material.WITHER_SKELETON_SPAWN_EGG);
    }

    @Override
    public BestiaryFilter getBestiaryFilter() {
        return BestiaryFilter.MOB;
    }
}
