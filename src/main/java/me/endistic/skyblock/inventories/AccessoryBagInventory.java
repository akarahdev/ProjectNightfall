package me.endistic.skyblock.inventories;

import me.endistic.skyblock.SkyBlock;
import me.endistic.skyblock.data.DataStorage;
import me.endistic.skyblock.items.ItemDatabase;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;

public class AccessoryBagInventory implements Listener {
    public static Inventory getInventoryFor(Player p) {
        var inv = Bukkit.createInventory(null, 9 * 6, "Accessory Bag");
        var s = 0;
        for(var id : DataStorage.accessoryBag.getOrDefault(p.getUniqueId(), List.of())) {
            if(s >= 9*6)
                break;
            inv.setItem(s++, ItemDatabase.item(id));
        }
        return inv;
    }

    public static void openFor(Player p) {
        p.openInventory(AccessoryBagInventory.getInventoryFor(p));
    }

    @EventHandler
    public void invClick(InventoryClickEvent e) {
        if(e.getView().getTitle().equals("Accessory Bag")) {
            e.setCancelled(false);
            if(!e.getCurrentItem().hasItemMeta()) {
                System.out.println("no meta");
                e.setCancelled(true);
                return;
            }
            if(!e.getCurrentItem().getItemMeta().getPersistentDataContainer().has(SkyBlock.key("id"))) {
                System.out.println("no id");
                e.setCancelled(true);
                return;
            }
            if(!ItemDatabase.citem(
                e.getCurrentItem().getItemMeta().getPersistentDataContainer().getOrDefault(SkyBlock.key("id"),
                    PersistentDataType.STRING, "conqueror_longsword")).getData().getType().toString().contains("ACCESSORY")) {
                System.out.println("not acc");
                e.setCancelled(true);
                return;
            }
        }
    }

    @EventHandler
    public void invClose(InventoryCloseEvent e) {
        if(e.getView().getTitle().equals("Accessory Bag")) {
            var list = new ArrayList<String>();
            for(var i = 0; i<9*6; i++) {
                var item = e.getInventory().getItem(i);
                if(item == null)
                    continue;
                if(!item.hasItemMeta())
                    continue;
                if(item.getItemMeta().getPersistentDataContainer().has(SkyBlock.key("id"))) {
                    list.add(item.getItemMeta().getPersistentDataContainer().get(
                        SkyBlock.key("id"), PersistentDataType.STRING
                    ));
                }
            }
            DataStorage.accessoryBag.put(e.getPlayer().getUniqueId(), list);
        }
    }
}
