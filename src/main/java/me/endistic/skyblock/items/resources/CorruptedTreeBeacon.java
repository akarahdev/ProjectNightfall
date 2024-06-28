package me.endistic.skyblock.items.resources;

import me.endistic.skyblock.abilities.Ability;
import me.endistic.skyblock.abilities.shared.SpawnMob;
import me.endistic.skyblock.items.CustomItemTemplate;
import me.endistic.skyblock.items.ItemMetadata;
import me.endistic.skyblock.items.Rarity;
import me.endistic.skyblock.items.ItemSlot;
import me.endistic.skyblock.mobs.entities.TheDheireadh;
import me.endistic.skyblock.stats.StatsObject;
import org.bukkit.Material;

public class CorruptedTreeBeacon extends CustomItemTemplate {
    public ItemMetadata getItemData() {
        return new ItemMetadata()
            .setMaterial(Material.BEACON)
            .setName("Corrupted Tree Beacon")
            .setType(ItemSlot.RESOURCE)
            .setRarity(Rarity.EPIC);
    }

    public StatsObject getStats() {
        return new StatsObject();
    }

    public Ability getAbility() {
        return new SpawnMob(
            new TheDheireadh(),
            10
        );
    }

    public String getId() {
        return "corrupted_tree_beacon";
    }
}
