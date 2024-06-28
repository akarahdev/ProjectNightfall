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

public class TheLost implements CustomMob {
    @Override
    public EntityType getEntityType() {
        return EntityType.ZOMBIE;
    }

    @Override
    public void runOnSpawn(LivingEntity le) {
        CustomMob.super.runOnSpawn(le);
    }

    @Override
    public int getLevel() {
        return 25;
    }

    @Override
    public String getName() {
        return "The Lost";
    }

    @Override
    public double getMeleeDamage() {
        return 5000;
    }


    @Override
    public MobId getId() {
        return MobId.THE_LOST;
    }

    @Override
    public DropTable getDropTable() {
        return new DropTable()
            .putDrop("warden_fragment", 1.0)
            .putDrop("warden_core", 1/4.0);
    }

    @Override
    public double getHealth() {
        return 35000;
    }

    @Override
    public int getCombatXp() {
        return 45;
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

    @Override
    public EntityEquipment getEquipment(EntityEquipment equipment) {
        equipment.setItemInMainHand(new ItemStack(Material.IRON_AXE));
        equipment.setHelmet(new ItemStack(Material.SCULK_SENSOR));
        equipment.setChestplate(ItemDatabase.item("warden_chestplate"));
        equipment.setLeggings(ItemDatabase.item("warden_leggings"));
        equipment.setBoots(ItemDatabase.item("warden_boots"));
        return equipment;
    }
}
