package me.endistic.skyblock.inventories;

import me.endistic.skyblock.data.DataStorage;
import me.endistic.skyblock.inventories.utils.MobInventoryData;
import me.endistic.skyblock.items.ItemDatabase;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class MobInfoInventory implements Listener {
    public static HashMap<UUID, MobInventoryData> openedData = new HashMap<>();
    public static Inventory getInventoryFor(Player p, MobInventoryData data) {
        openedData.put(p.getUniqueId(), data);

        var inv = Bukkit.createInventory(null, 45, "Mob");

        var pane = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        var paneMeta = pane.getItemMeta();

        assert paneMeta != null;

        paneMeta.setDisplayName("");
        pane.setItemMeta(paneMeta);

        for (var i = 0; i < 36; i++) {
            inv.setItem(i, pane);
        }

        var abilitySlots = new int[]{12, 13, 14, 15, 16, 21, 22, 23, 24, 25, 30, 31, 32, 33, 34};
        var abilityIndex = 0;
        for(var a : data.getAbilities()) {
            inv.setItem(abilitySlots[abilityIndex], a.getAbilityItem());
            abilityIndex++;
        }
        inv.setItem(10, data.toIcon());
        if(data.getMobBase().getDropTable() != null) {
            var lootTableLore = new ItemStack(Material.DIAMOND);
            var meta = lootTableLore.getItemMeta();
            var lore = new ArrayList<String>();
            meta.setDisplayName(ChatColor.YELLOW + "Drop Table");

            for(var s : data.getMobBase().getDropTable().internalMap.keySet()) {
                var v = data.getMobBase().getDropTable().internalMap.get(s);
                var itemNameLen = 20 - ItemDatabase.item(s).getItemMeta().getDisplayName().length();
                var chanceBuilder = new StringBuilder();
                var ci = 0;
                for(var chance : v) {
                    if(ci == v.size()) {
                        chanceBuilder.append(ChatColor.GREEN);
                        chanceBuilder.append(chance*100);
                        chanceBuilder.append("%");
                    } else {
                        chanceBuilder.append(ChatColor.GREEN);
                        chanceBuilder.append(chance*100);
                        chanceBuilder.append("%, ");
                    }
                    ci++;

                }
                lore.add(
                    ChatColor.DARK_GRAY + "> "
                    + ItemDatabase.item(s).getItemMeta().getDisplayName()
                    + " ".repeat(itemNameLen)
                    + ChatColor.DARK_GRAY + "| "
                    + chanceBuilder.toString()
                );

            }
            meta.setLore(lore);
            lootTableLore.setItemMeta(meta);
            inv.setItem(19, lootTableLore);
        }
        if(data.getSlayerQuest() != null) {
            var questItem = new ItemStack(Material.GOLD_BLOCK);
            var meta = questItem.getItemMeta();
            meta.setDisplayName(ChatColor.YELLOW + "Click to start quest!");
            questItem.setItemMeta(meta);

            inv.setItem(28, questItem);
        }


        inv.setItem(40, InventoryItems.closeButton);
        return inv;
    }

    public static void openFor(Player p, MobInventoryData data) {
        p.openInventory(MobInfoInventory.getInventoryFor(p, data));
    }

    @EventHandler
    public void clickInv(InventoryClickEvent e) {
        if(e.getView().getTitle().equals("Mob")) {
            e.setCancelled(true);
            var quest = openedData.get(e.getWhoClicked().getUniqueId()).getSlayerQuest();
            if(e.getSlot() == 28 && quest != null) {
                DataStorage.quest.put(e.getWhoClicked().getUniqueId(), quest);
            }
            return;
        }
    }
}
