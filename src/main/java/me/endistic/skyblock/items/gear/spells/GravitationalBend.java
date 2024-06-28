package me.endistic.skyblock.items.gear.spells;

import me.endistic.skyblock.abilities.Ability;
import me.endistic.skyblock.abilities.player.AbilityTrigger;
import me.endistic.skyblock.abilities.shared.InlineAbility;
import me.endistic.skyblock.data.DataStorage;
import me.endistic.skyblock.items.*;
import me.endistic.skyblock.stats.CalculateDamage;
import me.endistic.skyblock.stats.StatsObject;
import org.bukkit.*;
import org.bukkit.entity.Player;

public class GravitationalBend extends CustomItemTemplate {
    @Override
    public ItemMetadata getItemData() {
        return new ItemMetadata()
            .setMaterial(Material.AMETHYST_SHARD)
            .setName("Gravitational Implosion")
            .setType(ItemSlot.SWORD)
            .setRarity(Rarity.LEGENDARY);
    }

    @Override
    public StatsObject getStats() {
        return new StatsObject()
            .setBaseAbilityDamage(2000)
            .setAbilityDamageScaling(1);
    }

    @Override
    public String getId() {
        return "gravitational_bend";
    }

    @Override
    public UpgradingCost getUpgradingCost() {
        return Defaults.getUpgradeCost();
    }

    @Override
    public Ability getAbility() {
        return new InlineAbility(
            50,
            (le) -> {
                if(DataStorage.spatialMarks.containsKey(le.getUniqueId())) {
                    var loc = DataStorage.spatialMarks.get(le.getUniqueId());
                    for(var p : Bukkit.getOnlinePlayers())
                        p.spawnParticle(
                            Particle.EXPLOSION,
                            loc,
                            1
                        );

                    var stats = DataStorage.playerStats.get(le.getUniqueId());
                    for(var e : le.getWorld().getLivingEntities()) {
                        if(!(e instanceof Player) && e.getLocation().distanceSquared(loc) <= 36) {
                            e.damage(CalculateDamage.calculateMagic(stats).amount);
                        }
                    }
                }
            },
            AbilityTrigger.RIGHT_CLICK,
            "Gravitational Bend",
            "At your marked position, explode the position, dealing " +
                "${stat.ability_damage:2000} to nearby enemies."
        );
    }
}
