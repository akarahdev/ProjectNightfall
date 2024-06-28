package me.endistic.skyblock.commands;

import me.endistic.skyblock.items.ItemDatabase;
import me.endistic.skyblock.player.PlayerUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ItemListCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player p) {
            if(PlayerUtils.getRankWeight(p) < 70)
                return true;
            var page = 1;
            if(strings.length != 0)
                page = Integer.parseInt(strings[0]);

            var pageOff = (page-1)*54;
            var inv = Bukkit.createInventory(null, 54, "Item List");

            for(var slot = pageOff; slot<=pageOff+53; slot++) {
                if(slot<ItemDatabase.builtItems.size()-1 && slot>=0)
                    inv.setItem(slot, ItemDatabase.builtItems.get(slot));
            }

            p.openInventory(inv);

        }
        return false;
    }
}
