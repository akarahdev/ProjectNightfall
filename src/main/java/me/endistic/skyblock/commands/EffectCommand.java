package me.endistic.skyblock.commands;

import me.endistic.skyblock.data.DataStorage;
import me.endistic.skyblock.items.ItemDatabase;
import me.endistic.skyblock.items.ItemModifiers;
import me.endistic.skyblock.player.PlayerUtils;
import me.endistic.skyblock.statuseffects.StatusEffectData;
import me.endistic.skyblock.statuseffects.StatusEffectID;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class EffectCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player p) {
            if(PlayerUtils.getRankWeight(p) < 70)
                return true;
            var id = strings[0];
            var amp = Integer.parseInt(strings[1]);
            var dur = Integer.parseInt(strings[2]);

            var effect = new StatusEffectData(
                StatusEffectID.valueOf(id),
                amp,
                dur
            );
            
            var a = DataStorage.statusEffects.getOrDefault(p.getUniqueId(), new ArrayList<>());
            a.add(effect);
            DataStorage.statusEffects.put(p.getUniqueId(), a);
        }
        return false;
    }
}
