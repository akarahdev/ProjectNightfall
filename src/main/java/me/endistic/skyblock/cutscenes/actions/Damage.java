package me.endistic.skyblock.cutscenes.actions;

import me.endistic.skyblock.cutscenes.CutsceneExecutor;
import org.bukkit.entity.Player;

public class Damage implements CutsceneAction {
    public int ticks;

    public Damage(int ticks) {
        this.ticks = ticks;
    }

    @Override
    public void execute(Player p, CutsceneExecutor executor) {
        p.damage(ticks);
    }
}
