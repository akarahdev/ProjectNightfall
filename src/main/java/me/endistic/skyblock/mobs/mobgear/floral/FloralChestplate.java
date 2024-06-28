package me.endistic.skyblock.items.armor.floral;

import me.endistic.skyblock.items.*;
import me.endistic.skyblock.stats.StatsObject;
import org.bukkit.Color;
import org.bukkit.Material;

public class FloralChestplate extends CustomItemTemplate {
    public ItemMetadata getItemData() {
        return new ItemMetadata()
            .setMaterial(Material.LEATHER_CHESTPLATE)
            .setName("Floral Chestplate")
            .setType(ItemSlot.CHESTPLATE)
            .setRarity(Rarity.LEGENDARY)
            .setArmorColor(Color.GREEN);
    }

    public StatsObject getStats() {
        return new StatsObject()
            .setMaxHealth(400)
            .setDefense(150)
            .setStrength(30)
            .setCriticalDamage(20);
    }

    public String getId() {
        return "floral_chestplate";
    }

    @Override
    public boolean hideFromLists() { return true; }

}
