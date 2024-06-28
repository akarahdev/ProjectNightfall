package me.endistic.skyblock.items.armor.reaver;

import me.endistic.skyblock.items.CustomItemTemplate;
import me.endistic.skyblock.items.ItemMetadata;
import me.endistic.skyblock.items.Rarity;
import me.endistic.skyblock.items.ItemSlot;
import me.endistic.skyblock.stats.StatsObject;
import org.bukkit.Color;
import org.bukkit.Material;

public class ReaverChestplate extends CustomItemTemplate {
    public ItemMetadata getItemData() {
        return new ItemMetadata()
            .setMaterial(Material.LEATHER_CHESTPLATE)
            .setName("Reaver Chestplate")
            .setType(ItemSlot.CHESTPLATE)
            .setRarity(Rarity.MYTHIC)
            .setArmorColor(Color.fromRGB(255, 133, 133));
    }

    public StatsObject getStats() {
        return new StatsObject()
            .setMaxHealth(500)
            .setDefense(200)
            .setStrength(40)
            .setCriticalDamage(30)
            .setWalkSpeed(10);
    }

    public String getId() {
        return "reaver_chestplate";
    }

    @Override
    public boolean hideFromLists() { return true; }
}
