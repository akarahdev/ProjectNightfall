package me.endistic.skyblock.cutscenes.actions;

import me.endistic.skyblock.cutscenes.CutsceneExecutor;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class Teleport implements CutsceneAction {
    public Location loc;
    public Teleport(Location loc) {
        this.loc = loc;
    }

    @Override
    public void execute(Player p, CutsceneExecutor executor) {
        p.teleport(loc);
        executor.nextStep();
    }
}
