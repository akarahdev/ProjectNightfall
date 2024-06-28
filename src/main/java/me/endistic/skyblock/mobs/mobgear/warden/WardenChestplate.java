package me.endistic.skyblock.items.armor.warden;

import me.endistic.skyblock.items.*;
import me.endistic.skyblock.stats.StatsObject;
import org.bukkit.Color;
import org.bukkit.Material;

public class WardenChestplate extends CustomItemTemplate {
    public ItemMetadata getItemData() {
        return new ItemMetadata()
            .setMaterial(Material.LEATHER_CHESTPLATE)
            .setName("Warden Chestplate")
            .setType(ItemSlot.CHESTPLATE)
            .setRarity(Rarity.DIVINE)
            .setArmorColor(Color.fromRGB(0, 77, 77));
    }

    public StatsObject getStats() {
        return new StatsObject()
            .setMaxHealth(600)
            .setDefense(225)
            .setStrength(50)
            .setCriticalDamage(40)
            .setWalkSpeed(15);
    }

    public String getId() {
        return "warden_chestplate";
    }

    @Override
    public boolean hideFromLists() { return true; }
}
