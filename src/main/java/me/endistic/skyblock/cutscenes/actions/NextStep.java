package me.endistic.skyblock.cutscenes.actions;

import me.endistic.skyblock.cutscenes.CutsceneExecutor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class NextStep implements CutsceneAction {
    public CutsceneExecutor meta;
    public NextStep(CutsceneExecutor meta) {
        this.meta = meta;
    }

    @Override
    public void execute(Player p, CutsceneExecutor executor) {
        executor.nextStep();
        meta.nextStep();
    }
}
