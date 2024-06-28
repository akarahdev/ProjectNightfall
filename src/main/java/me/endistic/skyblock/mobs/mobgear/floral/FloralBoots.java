package me.endistic.skyblock.items.armor.floral;

import me.endistic.skyblock.items.ItemMetadata;
import me.endistic.skyblock.items.CustomItemTemplate;
import me.endistic.skyblock.items.ItemSlot;
import me.endistic.skyblock.items.Rarity;
import me.endistic.skyblock.stats.StatsObject;
import org.bukkit.Color;
import org.bukkit.Material;

public class FloralBoots extends CustomItemTemplate {
    public ItemMetadata getItemData() {
        return new ItemMetadata()
            .setMaterial(Material.LEATHER_BOOTS)
            .setName("Floral Boots")
            .setType(ItemSlot.BOOTS)
            .setRarity(Rarity.LEGENDARY)
            .setArmorColor(Color.GREEN);
    }

    public StatsObject getStats() {
        return new StatsObject()
            .setMaxHealth(325)
            .setDefense(125)
            .setStrength(30)
            .setCriticalDamage(20);
    }

    public String getId() {
        return "floral_boots";
    }

    @Override
    public boolean hideFromLists() { return true; }

}
