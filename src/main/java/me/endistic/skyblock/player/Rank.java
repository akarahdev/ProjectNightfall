package me.endistic.skyblock.player;

import org.bukkit.ChatColor;

public enum Rank {
    OWNER(ChatColor.DARK_RED, "[OWNER] ", 100, "OWNER"),
    DEVELOPER(ChatColor.GREEN, "[DEVELOPER] ", 70, "DEVELOPER"),
    ADMIN(ChatColor.DARK_PURPLE, "[ADMIN] ", 70, "ADMIN"),
    LOREMASTER(ChatColor.YELLOW, "[LOREMASTER] ", 70, "LOREMASTER"),
    BUILDER(ChatColor.YELLOW, "[BUILDER] ", 70, "BUILDER"),
    MODERATOR(ChatColor.DARK_GREEN, "[MODERATOR] ", 40, "MODERATOR"),
    GAMEBREAKER(ChatColor.DARK_RED, "[GAMEBREAKER] ", 10, "GAMEBREAKER"),
    MAYOR(ChatColor.DARK_PURPLE, "[MAYOR] ", 10, "MAYOR"),
    TRUSTED(ChatColor.AQUA, "[TRUSTED] ", 10, "TRUSTED"),
    DEFAULT(ChatColor.GRAY, "", 1, "DEFAULT");

    final ChatColor color;
    final String tag;
    public final int weight;
    public final String id;
    Rank(ChatColor color, String tag, int weight, String id) {
        this.color = color;
        this.tag = tag;
        this.weight = weight;
        this.id = id;
    }
}
