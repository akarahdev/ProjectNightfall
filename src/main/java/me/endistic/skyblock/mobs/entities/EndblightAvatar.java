package me.endistic.skyblock.mobs.entities;

import me.endistic.skyblock.abilities.Ability;
import me.endistic.skyblock.abilities.entity.*;
import me.endistic.skyblock.inventories.utils.BestiaryFilter;
import me.endistic.skyblock.inventories.utils.MobAbilityData;
import me.endistic.skyblock.inventories.utils.MobInventoryData;
import me.endistic.skyblock.mobs.CustomMob;
import me.endistic.skyblock.mobs.MobId;
import me.endistic.skyblock.mobs.drops.DropTable;
import me.endistic.skyblock.quests.SlayerQuestFactory;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;

import java.util.List;

public class EndblightAvatar implements CustomMob {
    @Override
    public EntityType getEntityType() {
        return EntityType.WARDEN;
    }

    @Override
    public List<Ability> getAbilities() {
        return List.of(
            new SpinningBeam(0, 7000),
            new SpinningBeam(180, 7000),
            new Searcher(),
            new ImmunityCrystal(),
            new RapidLightning(),
            new PassiveAOEDamage(800, 10)
        );
    }

    @Override
    public int getLevel() {
        return 70;
    }

    @Override
    public String getName() {
        return "Endblight Avatar";
    }

    @Override
    public double getMeleeDamage() {
        return 10000;
    }


    @Override
    public MobId getId() {
        return MobId.ENDBLIGHT_AVATAR;
    }

    @Override
    public DropTable getDropTable() {
        return new DropTable()
            .putDrop("warden_flux", 1);
    }

    @Override
    public double getHealth() {
        return 750000;
    }

    @Override
    public int getCombatXp() {
        return 360;
    }

    @Override
    public MobInventoryData getBestiaryData() {
        return new MobInventoryData()
            .setMobBase(this)
            .setAbilities(List.of(
                new MobAbilityData()
                    .setNameColor(ChatColor.AQUA)
                    .setName("Hyperbeam")
                    .setOccurance("Constant")
                    .setDescription("A large beam spins around the boss, dealing ${damage:7000} . Jump over it to dodge or die.")
                    .setIcon(Material.END_ROD),
                new MobAbilityData()
                    .setNameColor(ChatColor.RED)
                    .setName("Searching")
                    .setOccurance("Every 15 seconds")
                    .setDescription("The boss spawns a beacon. Stand next to the beacon in 5 seconds or die.")
                    .setIcon(Material.BEACON),
                new MobAbilityData()
                    .setNameColor(ChatColor.AQUA)
                    .setName("Subsoul")
                    .setOccurance("Every 10 seconds")
                    .setDescription("3 Endblight Subsouls spawn around the boss and the boss becomes IMMUNE. Kill the Subsouls to make the boss vulnerable again.")
                    .setIcon(Material.WARDEN_SPAWN_EGG),
                new MobAbilityData()
                    .setNameColor(ChatColor.AQUA)
                    .setName("Sonic Booms")
                    .setOccurance("Every 12.5 seconds")
                    .setDescription("The boss floats into the air and spawns Sonic Booms at your location for 5 seconds. Dodge the sonic booms or suffer {damage:5000} per sonic boom.")
                    .setIcon(Material.WARDEN_SPAWN_EGG)

            ))
            .setSlayerQuest(SlayerQuestFactory.wardenQuest())
            .setSpawnEgg(Material.WARDEN_SPAWN_EGG);
    }

    @Override
    public BestiaryFilter getBestiaryFilter() {
        return BestiaryFilter.BOSS;
    }
}
