package me.endistic.skyblock.cutscenes.actions;

import me.endistic.skyblock.cutscenes.CutsceneExecutor;
import org.bukkit.entity.Player;

public interface CutsceneAction {
    void execute(Player p, CutsceneExecutor executor);
}
