package me.endistic.skyblock.items.armor.floral;

import me.endistic.skyblock.items.ItemMetadata;
import me.endistic.skyblock.items.CustomItemTemplate;
import me.endistic.skyblock.items.ItemSlot;
import me.endistic.skyblock.items.Rarity;
import me.endistic.skyblock.stats.StatsObject;
import org.bukkit.Color;
import org.bukkit.Material;

public class FloralLeggings extends CustomItemTemplate {
    public ItemMetadata getItemData() {
        return new ItemMetadata()
            .setMaterial(Material.LEATHER_LEGGINGS)
            .setName("Floral Leggings")
            .setType(ItemSlot.LEGGINGS)
            .setRarity(Rarity.LEGENDARY)
            .setArmorColor(Color.GREEN);
    }

    public StatsObject getStats() {
        return new StatsObject()
            .setMaxHealth(350)
            .setDefense(125)
            .setStrength(30)
            .setCriticalDamage(20);
    }

    public String getId() {
        return "floral_leggings";
    }

    @Override
    public boolean hideFromLists() { return true; }
}
