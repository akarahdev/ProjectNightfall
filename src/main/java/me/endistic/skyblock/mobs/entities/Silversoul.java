package me.endistic.skyblock.mobs.entities;

import me.endistic.skyblock.inventories.utils.BestiaryFilter;
import me.endistic.skyblock.inventories.utils.MobInventoryData;
import me.endistic.skyblock.mobs.CustomMob;
import me.endistic.skyblock.mobs.MobId;
import me.endistic.skyblock.mobs.drops.DropTable;
import org.bukkit.Material;
import org.bukkit.entity.*;

import java.util.ArrayList;

public class Silversoul implements CustomMob {
    @Override
    public EntityType getEntityType() {
        return EntityType.BLAZE;
    }

    @Override
    public int getLevel() {
        return 45;
    }

    @Override
    public String getName() {
        return "Silversoul";
    }

    @Override
    public double getMeleeDamage() {
        return 5000;
    }


    @Override
    public MobId getId() {
        return MobId.SILVERSOUL;
    }

    @Override
    public DropTable getDropTable() {
        return new DropTable()
            .putDrop("blazing_fragment", 1.0)
            .putDrop("blazing_core", 1/4.0);
    }

    @Override
    public double getHealth() {
        return 145000;
    }

    @Override
    public int getCombatXp() {
        return 90;
    }

    @Override
    public MobInventoryData getBestiaryData() {
        return new MobInventoryData()
            .setMobBase(this)
            .setAbilities(new ArrayList<>())
            .setSlayerQuest(null)
            .setSpawnEgg(Material.BLAZE_SPAWN_EGG);
    }

    @Override
    public BestiaryFilter getBestiaryFilter() {
        return BestiaryFilter.MOB;
    }
}
