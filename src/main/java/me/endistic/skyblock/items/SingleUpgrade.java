package me.endistic.skyblock.items;

import me.endistic.skyblock.SkyBlock;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;

import java.util.Objects;

public class SingleUpgrade {
    public String id;
    public int amount;

    public SingleUpgrade(String id, int amount) {
        this.id = id;
        this.amount = amount;
    }

    public boolean match(Player p) {
        for(var idx = 0; idx<36; idx++) {
            var i = p.getInventory().getItem(idx);
            if(i != null && i.hasItemMeta()) {
                var id = i
                    .getItemMeta()
                    .getPersistentDataContainer()
                    .get(SkyBlock.key("id"), PersistentDataType.STRING);
                var amount = i.getAmount();
                if(Objects.equals(this.id, id) && amount >= this.amount)
                    return true;
            }
        }
        return false;
    }

    public void apply(Player p) {
        var items = p.getInventory();
        var idx = 0;
        for(var i : items) {

            if(i != null && i.hasItemMeta() && i.getItemMeta().getPersistentDataContainer() != null) {
                i = i.clone();
                var id = i
                    .getItemMeta()
                    .getPersistentDataContainer()
                    .get(SkyBlock.key("id"), PersistentDataType.STRING);
                var amount = i.getAmount();
                if(Objects.equals(this.id, id) && amount >= this.amount) {
                    i.setAmount(i.getAmount() - this.amount);
                    p.getInventory().setItem(idx, i);
                    break;
                }
            }
            idx++;
        }
    }
}
