package me.endistic.skyblock.commands;

import me.endistic.skyblock.mobs.MobDatabase;
import me.endistic.skyblock.mobs.MobId;
import me.endistic.skyblock.mobs.SpawningMetadata;
import me.endistic.skyblock.player.PlayerUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SummonCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player p) {
            if (PlayerUtils.getRankWeight(p) < 70)
                return true;
            var id = strings[0];
            var loc = p.getLocation();
            MobDatabase.spawnCustomMob(MobId.valueOf(id), loc, new SpawningMetadata()
                .setOwner(p)
                .setSummonedBy(null));
        }
        return false;
    }
}
