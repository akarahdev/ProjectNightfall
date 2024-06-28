package me.endistic.skyblock.cutscenes.actions;

import me.endistic.skyblock.SkyBlock;
import me.endistic.skyblock.cutscenes.CutsceneExecutor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Repeat implements CutsceneAction {
    public int times;
    public CutsceneAction[] actions;

    public Repeat(int times, CutsceneAction... actions) {
        this.times = times;
        this.actions = actions;
    }

    @Override
    public void execute(Player p, CutsceneExecutor executor) {
        var i = 0;
        for(i = 0; i<times; i++) {
            Bukkit.getScheduler().scheduleSyncDelayedTask(SkyBlock.main, () -> {
                var a = new ArrayList<>(List.of(actions));
                var executorCopy = new CutsceneExecutor(
                    a,
                    p
                );
                executorCopy.nextStep();
            }, i);
        }
        Bukkit.getScheduler().scheduleSyncDelayedTask(SkyBlock.main, executor::nextStep, i+1);
    }
}
