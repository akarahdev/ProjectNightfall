package me.endistic.skyblock.commands;

import me.endistic.skyblock.data.DataStorage;
import me.endistic.skyblock.player.PlayerUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PowerStoneCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player p) {
            if(PlayerUtils.getRankWeight(p) < 70)
                return true;
            var id = strings[0];
            DataStorage.powerStones.put(p.getUniqueId(), id);
        }
        return false;
    }
}
