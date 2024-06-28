package me.endistic.skyblock.inventories;

import me.endistic.skyblock.data.DataStorage;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class ProfileViewerInventory implements Listener {
    public static Inventory getInventoryFor(Player p, Player target) {
        var inv = Bukkit.createInventory(null, 54, "Profile Viewer");
        var targetInv = target.getInventory();

        var pane = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        var paneMeta = pane.getItemMeta();
        assert paneMeta != null;
        paneMeta.setDisplayName("");
        pane.setItemMeta(paneMeta);

        for (var i = 0; i < 54; i++) {
            inv.setItem(i, pane);
        }

        inv.setItem(9, targetInv.getItemInMainHand());
        inv.setItem(9+9, targetInv.getItemInOffHand());
        inv.setItem(10, targetInv.getHelmet());
        inv.setItem(10+9, targetInv.getChestplate());
        inv.setItem(10+9+9, targetInv.getLeggings());
        inv.setItem(10+9+9+9, targetInv.getBoots());
        inv.setItem(49, InventoryItems.closeButton);

        var stats = new ItemStack(Material.DIAMOND_AXE);
        var statsMeta = stats.getItemMeta();
        var statsLore = DataStorage.playerStats.get(target.getUniqueId()).getCVisualStatsLore();
        statsMeta.setLore(statsLore);
        statsMeta.setDisplayName(ChatColor.LIGHT_PURPLE + target.getName() + "'s Stats");
        statsMeta.setHideTooltip(true);
        statsMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        stats.setItemMeta(statsMeta);

        inv.setItem(4, stats);
        return inv;
    }

    public static void openFor(Player p, Player target) {
        p.openInventory(ProfileViewerInventory.getInventoryFor(p, target));
    }

    @EventHandler
    public void clickInv(InventoryClickEvent e) {
        if(e.getView().getTitle().equals("Profile Viewer")) {
            e.setCancelled(true);
        }
    }
}
