package me.endistic.skyblock.mobs.entities;

import me.endistic.skyblock.abilities.Ability;
import me.endistic.skyblock.abilities.entity.*;
import me.endistic.skyblock.abilities.shared.Heal;
import me.endistic.skyblock.inventories.utils.BestiaryFilter;
import me.endistic.skyblock.inventories.utils.MobAbilityData;
import me.endistic.skyblock.inventories.utils.MobInventoryData;
import me.endistic.skyblock.mobs.CustomMob;
import me.endistic.skyblock.mobs.MobId;
import me.endistic.skyblock.mobs.drops.DropTable;
import me.endistic.skyblock.quests.SlayerQuestFactory;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.inventory.EntityEquipment;

import java.util.List;

public class LordCalahann implements CustomMob {
    @Override
    public EntityType getEntityType() {
        return EntityType.WITHER_SKELETON;
    }

    @Override
    public List<Ability> getAbilities() {
        return List.of(
            new Heal(1000, 3, 0, 10),
            new PassiveAOEDamage(200, 10),
            new Explosions(),
            new SpinningBeam(0, 4000)
        );
    }

    @Override
    public int getLevel() {
        return 50;
    }

    @Override
    public String getName() {
        return "Lord Calahann";
    }

    @Override
    public double getMeleeDamage() {
        return 9000;
    }


    @Override
    public MobId getId() {
        return MobId.LORD_CALAHANN;
    }

    @Override
    public DropTable getDropTable() {
        return new DropTable()
            .putDrop("reaver_flux", 1.0);
    }

    @Override
    public double getHealth() {
        return 390000;
    }

    @Override
    public int getCombatXp() {
        return 190;
    }

    @Override
    public MobInventoryData getBestiaryData() {
        return new MobInventoryData()
            .setMobBase(this)
            .setAbilities(List.of(
                new MobAbilityData()
                    .setName("Reaver Radiation")
                    .setNameColor(ChatColor.RED)
                    .setIcon(Material.RED_BANNER)
                    .setOccurance("Constant")
                    .setDescription("A fast-rotating beam spins around the boss. " +
                        "Jump over it to dodge it or suffer ${stat.damage:4000}"),
                new MobAbilityData()
                    .setName("Healing")
                    .setNameColor(ChatColor.BLUE)
                    .setIcon(Material.SPLASH_POTION)
                    .setOccurance("Every 10 seconds")
                    .setDescription("The boss will heal ${stat.health:3000} over 2 seconds. "),
                new MobAbilityData()
                    .setName("Grenade Technologies")
                    .setNameColor(ChatColor.DARK_RED)
                    .setIcon(Material.TNT)
                    .setOccurance("Constant")
                    .setDescription("The boss tosses out 2 Primed TNTs that will explode after" +
                        " 2 seconds. Avoid the TNT or suffer ${stat.damage:5000}")
            ))
            .setSlayerQuest(SlayerQuestFactory.reaverKingQuest())
            .setSpawnEgg(Material.WITHER_SKELETON_SPAWN_EGG);
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
