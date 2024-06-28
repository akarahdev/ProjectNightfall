package me.endistic.skyblock.cutscenes.actions;

import me.endistic.skyblock.cutscenes.CutsceneExecutor;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

public class FreezePlayer implements CutsceneAction {
    public FreezePlayer() {

    }

    @Override
    public void execute(Player p, CutsceneExecutor executor) {
        p.getAttribute(Attribute.GENERIC_GRAVITY).setBaseValue(0);
        p.setVelocity(new Vector(0, 0, 0));
        executor.nextStep();
    }
}
