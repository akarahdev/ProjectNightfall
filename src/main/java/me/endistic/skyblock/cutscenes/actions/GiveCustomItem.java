package me.endistic.skyblock.cutscenes.actions;

import me.endistic.skyblock.cutscenes.CutsceneExecutor;
import me.endistic.skyblock.items.CustomItem;
import me.endistic.skyblock.items.ItemModifiers;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class GiveCustomItem implements CutsceneAction {
    CustomItem item;
    public GiveCustomItem(CustomItem item) {
        this.item = item;
    }

    @Override
    public void execute(Player p, CutsceneExecutor executor) {
        p.getInventory().addItem(item.build(new ItemModifiers()));
        executor.nextStep();
    }
}
