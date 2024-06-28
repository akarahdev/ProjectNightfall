package me.endistic.skyblock.cutscenes.actions;

import me.endistic.skyblock.SkyBlock;
import me.endistic.skyblock.cutscenes.CutsceneExecutor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Sleep implements CutsceneAction {
    public int ticks;

    public Sleep(int ticks) {
        this.ticks = ticks;
    }

    @Override
    public void execute(Player p, CutsceneExecutor executor) {
        Bukkit.getScheduler().scheduleSyncDelayedTask(SkyBlock.main, executor::nextStep, ticks);
    }
}
