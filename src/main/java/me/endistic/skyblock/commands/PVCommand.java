package me.endistic.skyblock.commands;

import me.endistic.skyblock.cutscenes.Introduction;
import me.endistic.skyblock.inventories.*;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

public class PVCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player p) {
            var playerName = strings[0];
            try {
                var p2 = Bukkit.getPlayer(playerName);
                ProfileViewerInventory.openFor(p, p2);
            } catch (NullPointerException ignored) {}

            return true;
        }
        return false;
    }
}
