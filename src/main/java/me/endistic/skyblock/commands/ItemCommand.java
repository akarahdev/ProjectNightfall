package me.endistic.skyblock.commands;

import me.endistic.skyblock.items.ItemDatabase;
import me.endistic.skyblock.items.ItemModifiers;
import me.endistic.skyblock.player.PlayerUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ItemCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player p) {
            if(PlayerUtils.getRankWeight(p) < 70)
                return true;
            var id = strings[0];
            var item = ItemDatabase.database.get(id);
            var modifiers = new ItemModifiers();
            if(strings.length == 2)
                modifiers.setStars(Integer.parseInt(strings[1]));
            p.getInventory().addItem(item.build(modifiers));
        }
        return false;
    }
}
