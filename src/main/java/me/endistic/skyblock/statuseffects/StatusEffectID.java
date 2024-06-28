package me.endistic.skyblock.statuseffects;

import me.endistic.skyblock.stats.StatsObject;
import org.bukkit.ChatColor;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public enum StatusEffectID {
    COLD("Cold", "❄", ChatColor.AQUA, (stats, amplifier) -> {
        stats.setWalkSpeed(stats.getWalkSpeed() / (amplifier+1));
    });

    public String displayName() {
        return name;
    }

    public String symbol() {
        return symbol;
    }

    public ChatColor color() {
        return color;
    }

    public BiConsumer<StatsObject, Integer> stats() {
        return stats;
    }

    final String name;
    final String symbol;
    final ChatColor color;
    final BiConsumer<StatsObject, Integer> stats;

    StatusEffectID(
        String name,
        String symbol,
        ChatColor color,
        BiConsumer<StatsObject, Integer> stats
    ) {
        this.name = name;
        this.symbol = symbol;
        this.color = color;
        this.stats = stats;
    }
}
