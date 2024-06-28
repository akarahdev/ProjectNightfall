package me.endistic.skyblock.player;

import me.endistic.skyblock.data.DataStorage;
import org.bukkit.entity.Player;

public class PlayerUtils {
    public static void schedulePingGrace(Player p, Runnable runnable) {
        runnable.run();
    }
    public static int getRankWeight(Player p) {
        return DataStorage.ranks.get(p.getUniqueId()).weight;
    }
}
