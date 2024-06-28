package me.endistic.skyblock.cutscenes.actions;

import me.endistic.skyblock.cutscenes.CutsceneExecutor;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class UnfreezePlayer implements CutsceneAction {
    public UnfreezePlayer() {
        
    }

    @Override
    public void execute(Player p, CutsceneExecutor executor) {
        p.getAttribute(Attribute.GENERIC_GRAVITY).setBaseValue(0.08);
        executor.nextStep();
    }
}
