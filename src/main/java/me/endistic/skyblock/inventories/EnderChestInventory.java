package me.endistic.skyblock.inventories;

import me.endistic.skyblock.SkyBlock;
import me.endistic.skyblock.data.DataStorage;
import me.endistic.skyblock.items.ItemDatabase;
import me.endistic.skyblock.items.ItemModifiers;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.persistence.PersistentDataType;

import java.util.HashMap;

public class EnderChestInventory implements Listener {
    public static Inventory getInventoryFor(Player p, int page) {
        var inv = Bukkit.createInventory(null, 9 * 6, "Ender Chest | Page " + page);
        DataStorage.openedEnderchestPage.put(p.getUniqueId(), page);

        var pageOffset = ((page-1)*54);

        var ids = DataStorage.enderChestItems.getOrDefault(p.getUniqueId(), new HashMap<>());
        var counts = DataStorage.enderChestCounts.getOrDefault(p.getUniqueId(), new HashMap<>());
        var mods = DataStorage.enderChestMods.getOrDefault(p.getUniqueId(), new HashMap<>());
        for(var slot : ids.keySet()) {
            if(pageOffset <= slot && slot <= pageOffset+54) {
                var i = ItemDatabase.item(
                    ids.get(slot),
                    mods.getOrDefault(slot, new ItemModifiers())
                );
                i.setAmount(counts.get(slot));
                inv.setItem(slot-pageOffset, i);
            }
        }

        return inv;
    }

    public static void openFor(Player p, int page) {
        p.openInventory(EnderChestInventory.getInventoryFor(p, page));
    }

    @EventHandler
    public void invClick(InventoryClickEvent e) {
        if(e.getView().getTitle().contains("Ender Chest")) {
            e.setCancelled(false);
        }
    }

    @EventHandler
    public void invClose(InventoryCloseEvent e) {
        if(e.getView().getTitle().contains("Ender Chest")) {
            var page = DataStorage.openedEnderchestPage.get(e.getPlayer().getUniqueId());
            var pageOffset = ((page-1)*54);
            var itemIds = DataStorage.enderChestItems.getOrDefault(e.getPlayer().getUniqueId(), new HashMap<>());
            var itemCounts = DataStorage.enderChestCounts.getOrDefault(e.getPlayer().getUniqueId(), new HashMap<>());
            var itemMods = DataStorage.enderChestMods.getOrDefault(e.getPlayer().getUniqueId(), new HashMap<>());

            for(var i = 0; i<9*6; i++) {
                var item = e.getInventory().getItem(i);

                if(item == null){
                    itemCounts.remove(i+pageOffset);
                    itemIds.remove(i+pageOffset);
                    itemMods.remove(i+pageOffset);
                    continue;
                }

                if(!item.hasItemMeta()) {
                    itemCounts.remove(i+pageOffset);
                    itemIds.remove(i+pageOffset);
                    itemMods.remove(i+pageOffset);
                    continue;
                }

                if(item.getItemMeta().getPersistentDataContainer().has(SkyBlock.key("id"))) {
                    itemIds.put(i+pageOffset, item.getItemMeta().getPersistentDataContainer().get(
                        SkyBlock.key("id"), PersistentDataType.STRING
                    ));
                    itemCounts.put(i+pageOffset, item.getAmount());
                    itemMods.put(i+pageOffset, ItemModifiers.from(
                        item.getItemMeta().getPersistentDataContainer().getOrDefault(
                            SkyBlock.key("modifiers"), PersistentDataType.TAG_CONTAINER,
                            item.getItemMeta().getPersistentDataContainer().getAdapterContext().newPersistentDataContainer()
                        )
                    ));
                }
            }

            DataStorage.enderChestItems.put(e.getPlayer().getUniqueId(), itemIds);
            DataStorage.enderChestCounts.put(e.getPlayer().getUniqueId(), itemCounts);
            DataStorage.enderChestMods.put(e.getPlayer().getUniqueId(), itemMods);
        }


    }
}
