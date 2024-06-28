package me.endistic.skyblock.mobs.entities;

import me.endistic.skyblock.inventories.utils.BestiaryFilter;
import me.endistic.skyblock.inventories.utils.MobInventoryData;
import me.endistic.skyblock.mobs.CustomMob;
import me.endistic.skyblock.mobs.MobId;
import me.endistic.skyblock.mobs.drops.DropTable;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class Wraithwood implements CustomMob {
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
        return "Wraithwood";
    }

    @Override
    public double getMeleeDamage() {
        return 500;
    }

    @Override
    public MobId getId() {
        return MobId.WRAITHWOOD;
    }

    @Override
    public DropTable getDropTable() {
        return new DropTable()
            .putDrop("floral_fragment", 1.0)
            .putDrop("floral_core", 1/4.0);
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
            .setSpawnEgg(Material.ZOMBIE_SPAWN_EGG);
    }

    @Override
    public BestiaryFilter getBestiaryFilter() {
        return BestiaryFilter.MOB;
    }

    @Override
    public EntityEquipment getEquipment(EntityEquipment equipment) {
        equipment.setHelmet(new ItemStack(Material.OAK_LEAVES));
        equipment.setChestplate(new ItemStack(Material.LEATHER_CHESTPLATE));
        equipment.setLeggings(new ItemStack(Material.LEATHER_LEGGINGS));
        equipment.setBoots(new ItemStack(Material.LEATHER_BOOTS));
        equipment.setItemInMainHand(new ItemStack(Material.WOODEN_SWORD));
        return equipment;
    }
}
