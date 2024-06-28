package me.endistic.skyblock.mobs.entities;

import me.endistic.skyblock.inventories.utils.BestiaryFilter;
import me.endistic.skyblock.inventories.utils.MobInventoryData;
import me.endistic.skyblock.items.ItemDatabase;
import me.endistic.skyblock.mobs.CustomMob;
import me.endistic.skyblock.mobs.MobId;
import me.endistic.skyblock.mobs.drops.DropTable;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class ReaverCourtier implements CustomMob {
    @Override
    public EntityType getEntityType() {
        return EntityType.WITHER_SKELETON;
    }

    @Override
    public int getLevel() {
        return 10;
    }

    @Override
    public String getName() {
        return "Reaver Courtier";
    }

    @Override
    public double getMeleeDamage() {
        return 3000;
    }


    @Override
    public MobId getId() {
        return MobId.REAVER_COURTIER;
    }

    @Override
    public DropTable getDropTable() {
        return new DropTable();
    }

    @Override
    public double getHealth() {
        return 10000;
    }

    @Override
    public int getCombatXp() {
        return 10;
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

    @Override
    public EntityEquipment getEquipment(EntityEquipment equipment) {
        equipment.setHelmet(ItemDatabase.item("reaver_helmet"));
        equipment.setChestplate(new ItemStack(Material.NETHERITE_CHESTPLATE));
        equipment.setLeggings(new ItemStack(Material.NETHERITE_LEGGINGS));
        equipment.setBoots(new ItemStack(Material.NETHERITE_BOOTS));
        equipment.setItemInMainHand(ItemDatabase.item("reaver_katana"));
        return equipment;
    }
}
