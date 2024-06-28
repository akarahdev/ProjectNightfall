package me.endistic.skyblock.items.armor.warden;

import me.endistic.skyblock.items.CustomItemTemplate;
import me.endistic.skyblock.items.ItemMetadata;
import me.endistic.skyblock.items.Rarity;
import me.endistic.skyblock.items.ItemSlot;
import me.endistic.skyblock.stats.StatsObject;
import org.bukkit.Color;
import org.bukkit.Material;

public class WardenLeggings extends CustomItemTemplate {
    public ItemMetadata getItemData() {
        return new ItemMetadata()
            .setMaterial(Material.LEATHER_LEGGINGS)
            .setName("Warden Leggings")
            .setType(ItemSlot.LEGGINGS)
            .setRarity(Rarity.DIVINE)
            .setArmorColor(Color.fromRGB(0, 77, 77));
    }

    public StatsObject getStats() {
        return new StatsObject()
            .setMaxHealth(550)
            .setDefense(225)
            .setStrength(50)
            .setCriticalDamage(40)
            .setWalkSpeed(15);
    }

    public String getId() {
        return "warden_leggings";
    }

    @Override
    public boolean hideFromLists() { return true; }
}
