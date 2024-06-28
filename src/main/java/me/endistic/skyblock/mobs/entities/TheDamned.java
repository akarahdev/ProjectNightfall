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

public class TheDamned implements CustomMob {
    @Override
    public EntityType getEntityType() {
        return EntityType.ZOMBIE;
    }

    @Override
    public int getLevel() {
        return 35;
    }

    @Override
    public String getName() {
        return "The Damned";
    }

    @Override
    public double getMeleeDamage() {
        return 8000;
    }


    @Override
    public MobId getId() {
        return MobId.THE_DAMNED;
    }

    @Override
    public DropTable getDropTable() {
        return new DropTable()
            .putDrop("warden_fragment", 1)
            .putDrop("warden_core", 1.0);
    }

    @Override
    public double getHealth() {
        return 70000;
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
            .setSpawnEgg(Material.WARDEN_SPAWN_EGG);
    }

    @Override
    public BestiaryFilter getBestiaryFilter() {
        return BestiaryFilter.MOB;
    }

    @Override
    public EntityEquipment getEquipment(EntityEquipment equipment) {
        equipment.setItemInMainHand(new ItemStack(Material.NETHERITE_AXE));
        equipment.setHelmet(new ItemStack(Material.SCULK_CATALYST));
        equipment.setChestplate(ItemDatabase.item("warden_chestplate"));
        equipment.setLeggings(ItemDatabase.item("warden_leggings"));
        equipment.setBoots(ItemDatabase.item("warden_boots"));
        return equipment;
    }
}
