package me.endistic.skyblock.inventories.utils;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class LegacyMobDescriptors {
    public Material material;
    public String name;
    public int level;

    public Material getMaterial() {
        return material;
    }

    public LegacyMobDescriptors setMaterial(Material material) {
        this.material = material;
        return this;
    }

    public String getName() {
        return name;
    }

    public LegacyMobDescriptors setName(String name) {
        this.name = name;
        return this;
    }

    public int getLevel() {
        return level;
    }

    public LegacyMobDescriptors setLevel(int level) {
        this.level = level;
        return this;
    }

    public String[] getLore() {
        return lore;
    }

    public LegacyMobDescriptors setLore(String... lore) {
        this.lore = lore;
        return this;
    }

    public String[] lore;

    public ItemStack build() {
        var i = new ItemStack(this.getMaterial());
        var meta = i.getItemMeta();
        meta.setDisplayName(
            ChatColor.DARK_GRAY + "[" + ChatColor.GRAY + "Lv" + this.getLevel() + ChatColor.DARK_GRAY + "]"
            + " " + this.getName()
        );
        meta.setLore(List.of(this.getLore()));
        i.setItemMeta(meta);
        return i;
    }
}
