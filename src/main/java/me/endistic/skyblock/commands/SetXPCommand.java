package me.endistic.skyblock.commands;

import me.endistic.skyblock.data.DataStorage;
import me.endistic.skyblock.player.PlayerUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetXPCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player p) {
            if(PlayerUtils.getRankWeight(p) < 70)
                return true;
            var name = strings[0];
            var rank = strings[1];
            DataStorage.combatXp.put(
                Bukkit.getPlayer(name).getUniqueId(),
                Integer.parseInt(rank)
            );
        }
        return false;
    }
}
