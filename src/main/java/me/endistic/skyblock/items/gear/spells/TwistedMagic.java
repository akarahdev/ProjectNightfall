package me.endistic.skyblock.items.gear.spells;

import me.endistic.skyblock.abilities.Ability;
import me.endistic.skyblock.abilities.player.AbilityTrigger;
import me.endistic.skyblock.abilities.shared.InlineAbility;
import me.endistic.skyblock.data.DataStorage;
import me.endistic.skyblock.items.*;
import me.endistic.skyblock.stats.StatsObject;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

public class TwistedMagic extends CustomItemTemplate {
    @Override
    public ItemMetadata getItemData() {
        return new ItemMetadata()
            .setMaterial(Material.NETHERITE_INGOT)
            .setName("Twisted Magic")
            .setType(ItemSlot.SWORD)
            .setRarity(Rarity.LEGENDARY);
    }

    @Override
    public StatsObject getStats() {
        return new StatsObject();
    }

    @Override
    public String getId() {
        return "twisted_magic";
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

                    var intelligence = DataStorage.playerStats.get(le.getUniqueId()).getIntelligence();
                    for(var e : le.getWorld().getLivingEntities()) {
                        if(!(e instanceof Player) && e.getLocation().distanceSquared(loc) <= 36) {
                            e.damage(2000 + (intelligence * 2));
                        }
                    }
                }
            },
            AbilityTrigger.RIGHT_CLICK,
            "Twisted Magic",
            "At your marked position, explode the position dealing damage to nearby enemies."
        );
    }
}
