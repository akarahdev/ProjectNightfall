package me.endistic.skyblock.commands;

import me.endistic.skyblock.items.ItemDatabase;
import me.endistic.skyblock.items.ItemModifiers;
import me.endistic.skyblock.player.PlayerUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class KickCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player p) {
            if (PlayerUtils.getRankWeight(p) < 40)
                return true;
            var player = strings[0];
            var args = new ArrayList<>(List.of(strings));
            args.removeFirst();

            var reason = String.join(" ", args);

            Bukkit.getPlayer(player).kickPlayer(
                ChatColor.RED + "" + ChatColor.BOLD + "KICKED!" +
                    "\n" +
                    ChatColor.GRAY + "You have been kicked for:" +
                    "\n" + ChatColor.WHITE +
                    reason
            );
        }
        return false;
    }
}
