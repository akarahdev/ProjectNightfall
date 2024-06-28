package me.endistic.skyblock.mobs.entities;

import me.endistic.skyblock.inventories.utils.BestiaryFilter;
import me.endistic.skyblock.inventories.utils.MobInventoryData;
import me.endistic.skyblock.items.ItemDatabase;
import me.endistic.skyblock.mobs.CustomMob;
import me.endistic.skyblock.mobs.MobId;
import me.endistic.skyblock.mobs.drops.DropTable;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class EndblightSubsoul implements CustomMob {

    @Override
    public EntityType getEntityType() {
        return EntityType.ZOMBIE;
    }

    @Override
    public EntityEquipment getEquipment(EntityEquipment entityEquipment) {
        entityEquipment.setItemInMainHand(new ItemStack(Material.IRON_AXE));
        entityEquipment.setHelmet(new ItemStack(Material.SCULK_SENSOR));
        entityEquipment.setChestplate(ItemDatabase.item("warden_chestplate"));
        entityEquipment.setLeggings(ItemDatabase.item("warden_leggings"));
        entityEquipment.setBoots(ItemDatabase.item("warden_boots"));
        return entityEquipment;
    }

    @Override
    public int getLevel() {
        return -5;
    }

    @Override
    public String getName() {
        return "Endblight Subsoul";
    }

    @Override
    public double getMeleeDamage() {
        return 5000000;
    }


    @Override
    public MobId getId() {
        return MobId.ENDBLIGHT_SUBSOUL;
    }

    @Override
    public DropTable getDropTable() {
        return new DropTable();
    }

    @Override
    public double getHealth() {
        return 1;
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
