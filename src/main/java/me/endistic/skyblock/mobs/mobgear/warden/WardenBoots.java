package me.endistic.skyblock.items.armor.warden;

import me.endistic.skyblock.items.*;
import me.endistic.skyblock.stats.StatsObject;
import org.bukkit.Color;
import org.bukkit.Material;

public class WardenBoots extends CustomItemTemplate {
    public ItemMetadata getItemData() {
        return new ItemMetadata()
            .setMaterial(Material.LEATHER_BOOTS)
            .setName("Warden Boots")
            .setType(ItemSlot.BOOTS)
            .setRarity(Rarity.DIVINE)
            .setArmorColor(Color.fromRGB(0, 77, 77));
    }

    public StatsObject getStats() {
        return new StatsObject()
            .setMaxHealth(525)
            .setDefense(200)
            .setStrength(50)
            .setCriticalDamage(40)
            .setWalkSpeed(15);
    }

    public String getId() {
        return "warden_boots";
    }

    @Override
    public boolean hideFromLists() { return true; }
}
