package me.endistic.skyblock.mobs.entities;

import me.endistic.skyblock.abilities.Ability;
import me.endistic.skyblock.inventories.utils.BestiaryFilter;
import me.endistic.skyblock.inventories.utils.MobAbilityData;
import me.endistic.skyblock.inventories.utils.MobInventoryData;
import me.endistic.skyblock.mobs.CustomMob;
import me.endistic.skyblock.mobs.MobId;
import me.endistic.skyblock.mobs.drops.DropTable;
import me.endistic.skyblock.quests.SlayerQuestFactory;
import me.endistic.skyblock.abilities.entity.Dash;
import me.endistic.skyblock.abilities.entity.Earthquake;
import me.endistic.skyblock.abilities.entity.PassiveAOEDamage;
import me.endistic.skyblock.abilities.entity.Uproot;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.EntityEquipment;

import java.util.List;

public class TheDheireadh implements CustomMob {

    @Override
    public EntityType getEntityType() {
        return EntityType.ZOMBIE;
    }

    @Override
    public List<Ability> getAbilities() {
        return List.of(
            new Earthquake(),
            new Uproot(),
            new PassiveAOEDamage(50, 10),
            new Dash()
        );
    }

    @Override
    public int getLevel() {
        return 30;
    }

    @Override
    public String getName() {
        return "The Dheireadh";
    }

    @Override
    public double getMeleeDamage() {
        return 1000;
    }


    @Override
    public MobId getId() {
        return MobId.THE_DHEIREADH;
    }

    @Override
    public DropTable getDropTable() {
        return new DropTable()
            .putDrop("floral_flux", 1.0);
    }

    @Override
    public double getHealth() {
        return 100000;
    }

    @Override
    public int getCombatXp() {
        return 90;
    }

    @Override
    public MobInventoryData getBestiaryData() {
        return new MobInventoryData()
            .setMobBase(this)
            .setAbilities(List.of(
                new MobAbilityData()
                    .setName("Earthquake")
                    .setNameColor(ChatColor.GREEN)
                    .setOccurance("Every 15 seconds")
                    .setDescription("The boss shoots a circle of particles, dealing ${stat.damage:3000} each.")
                    .setIcon(Material.SADDLE),
                new MobAbilityData()
                    .setName("Uproot")
                    .setNameColor(ChatColor.DARK_GREEN)
                    .setOccurance("Every 3 seconds")
                    .setDescription("Shoots ${red} 3 ${gray} roots around, each dealing ${stat.damage:2000} each.")
                    .setIcon(Material.COMPARATOR),
                new MobAbilityData()
                    .setName("Dash")
                    .setNameColor(ChatColor.RED)
                    .setOccurance("Every 8 seconds")
                    .setDescription("Launches towards the spawner of the boss.")
                    .setIcon(Material.BLAZE_POWDER)
            ))
            .setSlayerQuest(SlayerQuestFactory.corruptedTreeQuest())
            .setSpawnEgg(Material.WARDEN_SPAWN_EGG);
    }

    @Override
    public BestiaryFilter getBestiaryFilter() {
        return BestiaryFilter.BOSS;
    }

    @Override
    public EntityEquipment getEquipment(EntityEquipment equipment) {
        return CustomMob.super.getEquipment(equipment);
    }
}
