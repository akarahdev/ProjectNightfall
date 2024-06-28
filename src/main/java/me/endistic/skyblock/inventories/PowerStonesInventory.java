package me.endistic.skyblock.inventories;

import me.endistic.skyblock.SkyBlock;
import me.endistic.skyblock.data.DataStorage;
import me.endistic.skyblock.powerstones.*;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.persistence.PersistentDataType;

public class PowerStonesInventory implements Listener {
    public static Inventory getInventoryFor(Player p) {
        var inv = Bukkit.createInventory(null, 9 * 6, "Power Stones");
        var id = DataStorage.powerStones.getOrDefault(p.getUniqueId(), "empty");
        var mp = DataStorage.magicalPower.get(p.getUniqueId());
        inv.setItem(0, new EmptyStone().generateIcon().build(id, mp));
        inv.setItem(1, new Berserker().generateIcon().build(id, mp));
        inv.setItem(2, new Knight().generateIcon().build(id, mp));
        inv.setItem(3, new Boltslinger().generateIcon().build(id, mp));
        inv.setItem(4, new Ranger().generateIcon().build(id, mp));
        inv.setItem(5, new Magician().generateIcon().build(id, mp));
        inv.setItem(6, new Tank().generateIcon().build(id, mp));
        inv.setItem(7, new Slammer().generateIcon().build(id, mp));
        return inv;
    }

    public static void openFor(Player p) {
        p.openInventory(PowerStonesInventory.getInventoryFor(p));
    }

    @EventHandler
    public void invClick(InventoryClickEvent e) {
        if(e.getCurrentItem() == null)
            return;
        if(e.getView().getTitle().equals("Power Stones")) {
            e.setCancelled(true);
            var pdc = e.getCurrentItem().getItemMeta().getPersistentDataContainer();
            if(pdc.has(SkyBlock.key("power_stone_set"))) {
                var set = pdc.get(SkyBlock.key("power_stone_set"), PersistentDataType.STRING);
                DataStorage.powerStones.put(
                    e.getWhoClicked().getUniqueId(),
                    set
                );
                PowerStonesInventory.openFor((Player) e.getWhoClicked());
            }
        }
    }
}
