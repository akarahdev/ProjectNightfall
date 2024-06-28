package me.endistic.skyblock.commands;

import me.endistic.skyblock.SkyBlock;
import me.endistic.skyblock.player.PlayerUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ReloadCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player p) {
            if(PlayerUtils.getRankWeight(p) < 70)
                return true;
            Bukkit.getScheduler().scheduleSyncDelayedTask(SkyBlock.main, () -> {
                Bukkit.broadcastMessage(
                    ChatColor.RED + "" + ChatColor.BOLD + "RELOAD! " + ChatColor.GRAY + "Reloading..."
                );
            });
            Bukkit.getScheduler().scheduleSyncDelayedTask(SkyBlock.main, () -> {
                Bukkit.broadcastMessage(
                    ChatColor.GREEN + "" + ChatColor.BOLD + "RELOAD! " + ChatColor.GRAY + "Reloading complete!"
                );
            }, 10);
            Bukkit.getScheduler().scheduleSyncDelayedTask(SkyBlock.main, () -> {
                Bukkit.dispatchCommand(
                    p,
                    "plugman reload SkyBlock"
                );
            }, 1);

        }
        return false;
    }
}
