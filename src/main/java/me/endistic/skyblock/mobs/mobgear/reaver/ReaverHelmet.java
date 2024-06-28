package me.endistic.skyblock.items.armor.reaver;

import me.endistic.skyblock.items.*;
import me.endistic.skyblock.stats.StatsObject;
import org.bukkit.Material;

public class ReaverHelmet extends CustomItemTemplate {
    public ItemMetadata getItemData() {
        return new ItemMetadata()
            .setMaterial(Material.PLAYER_HEAD)
            .setName("Reaver Helmet")
            .setType(ItemSlot.HELMET)
            .setRarity(Rarity.MYTHIC)
            .setTexture(ItemTexture.REAVER_HELMET);
    }

    public StatsObject getStats() {
        return new StatsObject()
            .setMaxHealth(400)
            .setDefense(150)
            .setStrength(40)
            .setCriticalDamage(30)
            .setWalkSpeed(10);
    }

    public String getId() {
        return "reaver_helmet";
    }

    @Override
    public boolean hideFromLists() { return true; }
}
