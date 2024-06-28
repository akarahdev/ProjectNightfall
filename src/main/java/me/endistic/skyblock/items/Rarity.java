package me.endistic.skyblock.items;

import org.bukkit.ChatColor;

public enum Rarity {
    COMMON(ChatColor.WHITE, "COMMON"),
    UNCOMMON(ChatColor.GREEN, "UNCOMMON"),
    RARE(ChatColor.BLUE, "RARE"),
    EPIC(ChatColor.DARK_PURPLE, "EPIC"),
    LEGENDARY(ChatColor.GOLD, "LEGENDARY"),
    MYTHIC(ChatColor.LIGHT_PURPLE, "MYTHIC"),
    DIVINE(ChatColor.AQUA, "DIVINE"),
    SUPREME(ChatColor.DARK_RED, "SUPREME"),
    ULTIMATE(ChatColor.RED, "ULTIMATE");

    public final ChatColor color;
    public final String name;

    public ChatColor getColor() {
        return color;
    }

    public String getName() {
        return name;
    }



    Rarity(ChatColor color, String name) {
        this.color = color;
        this.name = name;
    }
}
