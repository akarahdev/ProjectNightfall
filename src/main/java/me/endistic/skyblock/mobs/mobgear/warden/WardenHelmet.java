package me.endistic.skyblock.items.armor.warden;

import me.endistic.skyblock.items.*;
import me.endistic.skyblock.stats.StatsObject;
import org.bukkit.Material;

public class WardenHelmet extends CustomItemTemplate {
    public ItemMetadata getItemData() {
        return new ItemMetadata()
            .setMaterial(Material.PLAYER_HEAD)
            .setName("Warden Helmet")
            .setType(ItemSlot.HELMET)
            .setRarity(Rarity.DIVINE)
            .setTexture(ItemTexture.WARDEN_HELMET);
    }

    public StatsObject getStats() {
        return new StatsObject()
            .setMaxHealth(500)
            .setDefense(175)
            .setStrength(50)
            .setCriticalDamage(40)
            .setWalkSpeed(15);
    }

    public String getId() {
        return "warden_helmet";
    }

    @Override
    public boolean hideFromLists() { return true; }
}
