package me.endistic.skyblock.cutscenes.actions;

import me.endistic.skyblock.SkyBlock;
import me.endistic.skyblock.cutscenes.CutsceneExecutor;
import me.endistic.skyblock.data.DataStorage;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class WaitForPowerStone implements CutsceneAction {
    public WaitForPowerStone() {

    }

    @Override
    public void execute(Player p, CutsceneExecutor executor) {
        var t = this;
        new BukkitRunnable() {
            @Override
            public void run() {
                var stone = DataStorage.powerStones.getOrDefault(p.getUniqueId(), "empty");
                if(!stone.equals("empty")) {
                    this.cancel();
                    executor.nextStep();
                }
            }
        }.runTaskTimer(SkyBlock.main, 1, 20);
    }
}
