package me.endistic.skyblock.cutscenes.actions;

import me.endistic.skyblock.SkyBlock;
import me.endistic.skyblock.cutscenes.CutsceneExecutor;
import me.endistic.skyblock.items.CustomItem;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;

public class WaitForCustomItem implements CutsceneAction {
    CustomItem item;

    public WaitForCustomItem(CustomItem item) {
        this.item = item;
    }

    @Override
    public void execute(Player p, CutsceneExecutor executor) {
        var t = this;
        new BukkitRunnable() {
            @Override
            public void run() {
                for(var i : p.getInventory())
                    if(i != null
                    && i.hasItemMeta()
                    && i.getItemMeta().getPersistentDataContainer().has(SkyBlock.key("id"))
                    && i.getItemMeta().getPersistentDataContainer().get(SkyBlock.key("id"), PersistentDataType.STRING).equals(item.getId())) {
                        executor.nextStep();
                        this.cancel();
                        return;
                    }

            }
        }.runTaskTimer(SkyBlock.main, 1, 20);
    }
}
