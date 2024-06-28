package me.endistic.skyblock.mobs.entities;

import me.endistic.skyblock.abilities.Ability;
import me.endistic.skyblock.abilities.entity.DivineLances;
import me.endistic.skyblock.abilities.entity.EmpyrealAbberant;
import me.endistic.skyblock.abilities.entity.FirePits;
import me.endistic.skyblock.abilities.entity.ShardOfTheDragonGod;
import me.endistic.skyblock.inventories.utils.BestiaryFilter;
import me.endistic.skyblock.inventories.utils.MobAbilityData;
import me.endistic.skyblock.inventories.utils.MobInventoryData;
import me.endistic.skyblock.mobs.CustomMob;
import me.endistic.skyblock.mobs.MobId;
import me.endistic.skyblock.mobs.MobTrigger;
import me.endistic.skyblock.mobs.drops.DropTable;
import me.endistic.skyblock.quests.SlayerQuestFactory;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.*;
import org.bukkit.util.Vector;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class TheExiflame implements CustomMob {
    @Override
    public EntityType getEntityType() {
        return EntityType.BLAZE;
    }

    @Override
    public List<Ability> getAbilities() {
        return List.of(
            new DivineLances(),
            new EmpyrealAbberant(),
            new FirePits(),
            new ShardOfTheDragonGod()
        );
    }

    @Override
    public void runOnSpawn(LivingEntity le) {
        le.getAttribute(Attribute.GENERIC_SCALE).setBaseValue(1.5);

        AtomicInteger i = new AtomicInteger();

        MobTrigger.runOnLoop((br) -> {
            if(le.isDead())
                br.cancel();

            i.getAndIncrement();
            if(i.get() % 10 == 1) {
                le.launchProjectile(SmallFireball.class, le.getLocation().getDirection());
            }

            le.setVelocity(le.getVelocity().add(new Vector(0, -100, 0)));

            if(le.isDead())
                br.cancel();
        });
    }

    @Override
    public int getLevel() {
        return 45;
    }

    @Override
    public String getName() {
        return "The Exiflame";
    }

    @Override
    public double getMeleeDamage() {
        return 2000;
    }


    @Override
    public MobId getId() {
        return MobId.THE_EXIFLAME;
    }

    @Override
    public DropTable getDropTable() {
        return new DropTable()
            .putDrop("blazing_flux", 1);
    }

    @Override
    public double getHealth() {
        return 1900000;
    }

    @Override
    public int getCombatXp() {
        return 350;
    }

    @Override
    public MobInventoryData getBestiaryData() {
        return new MobInventoryData()
            .setMobBase(this)
            .setAbilities(List.of(
                new MobAbilityData()
                    .setIcon(Material.LEAD)
                    .setName("Divine Lances")
                    .setNameColor(ChatColor.YELLOW)
                    .setDescription("The boss will cast white beams in various directions. " +
                        "After 1 second, the beams will inflame and deal ${damage:10000}")
                    .setOccurance("Every 3 seconds"),
                new MobAbilityData()
                    .setIcon(Material.BLAZE_POWDER)
                    .setName("Empyreal Abberant")
                    .setNameColor(ChatColor.GOLD)
                    .setDescription("The boss will shoot a fireball homing in on the " +
                        "summoner. Collisions deal ${damage:9000}")
                    .setOccurance("Every 7 seconds"),
                new MobAbilityData()
                    .setIcon(Material.ENDER_DRAGON_SPAWN_EGG)
                    .setName("Shard of the Dragon God")
                    .setNameColor(ChatColor.DARK_PURPLE)
                    .setDescription("The boss will gain Immune Status and start flying around, " +
                        "shooting fireballs at you. Ten Empyreal Aspects will spawn nearby. " +
                        "Kill all of them within 8 seconds or die.")
                    .setOccurance("Every 13 seconds")
            ))
            .setSlayerQuest(SlayerQuestFactory.blazeQuest())
            .setSpawnEgg(Material.BLAZE_SPAWN_EGG);
    }

    @Override
    public BestiaryFilter getBestiaryFilter() {
        return BestiaryFilter.BOSS;
    }
}
