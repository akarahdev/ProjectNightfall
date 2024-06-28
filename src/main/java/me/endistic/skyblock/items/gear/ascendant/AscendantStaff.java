package me.endistic.skyblock.items.gear.ascendant;

import me.endistic.skyblock.abilities.Ability;
import me.endistic.skyblock.abilities.player.AbilityTrigger;
import me.endistic.skyblock.abilities.shared.InlineAbility;
import me.endistic.skyblock.data.DataStorage;
import me.endistic.skyblock.items.*;
import me.endistic.skyblock.items.crafting.Recipe;
import me.endistic.skyblock.stats.StatsObject;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;

import java.util.List;

public class AscendantStaff extends CustomItemTemplate {
    @Override
    public ItemMetadata getItemData() {
        return new ItemMetadata()
            .setMaterial(Material.WHEAT)
            .setName("Ascendant Staff")
            .setType(ItemSlot.SWORD)
            .setRarity(Rarity.LEGENDARY);
    }

    @Override
    public StatsObject getStats() {
        return new StatsObject()
            .setDamage(30)
            .setMending(50);
    }

    @Override
    public String getId() {
        return "ascendant_staff";
    }

    @Override
    public UpgradingCost getUpgradingCost() {
        return Defaults.getUpgradeCost();
    }

    @Override
    public List<Recipe> getRecipe() {
        return List.of();
    }

    @Override
    public Ability getAbility() {
        return new InlineAbility(
            50,
            (le) -> {
                le.sendMessage(DataStorage.playerStats.keySet().toString());
                var heal = 150 * (DataStorage.playerStats.get(le.getUniqueId()).getMending()/100);
                for(var p : Bukkit.getOnlinePlayers()) {

                    if(p.getLocation().distanceSquared(le.getLocation()) <= 100) {
                        DataStorage.currentHealth.put(
                            p.getUniqueId(),
                            DataStorage.currentHealth.get(p.getUniqueId()) + heal
                        );
                        p.sendMessage(ChatColor.GREEN + le.getName() + " healed you for " + heal + " HP!");
                        le.sendMessage(ChatColor.GREEN + "Healed " + p.getName() + " for " + heal + " HP!");
                    }
                }
            },
            AbilityTrigger.RIGHT_CLICK,
            "Ascendance",
            "Heal players in a 10 block radius around you {health:150}"
        );
    }
}
