package me.endistic.skyblock.inventories;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Objects;

public class EnderChestPageInventory implements Listener {
    public static Inventory getInventoryFor(Player p) {
        var inv = Bukkit.createInventory(null, 45, "Pages");

        var pane = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        var paneMeta = pane.getItemMeta();

        assert paneMeta != null;

        paneMeta.setDisplayName("");
        pane.setItemMeta(paneMeta);

        for (var i = 0; i < 36; i++) {
            inv.setItem(i, pane);
        }

        var abilitySlots = new int[]{11, 12, 13, 14, 15, 16, 17};
        var abilityIndex = 0;

        var items = new ItemStack[]{
            getPageIcon(1),
            getPageIcon(2),
            getPageIcon(3),
            getPageIcon(4),
            getPageIcon(5)
        };

        for(var i : items) {
            inv.setItem(abilitySlots[abilityIndex], i);
            abilityIndex++;
        }


        inv.setItem(40, InventoryItems.closeButton);
        return inv;
    }

    public static void openFor(Player p) {
        p.openInventory(EnderChestPageInventory.getInventoryFor(p));
    }

    @EventHandler
    public void clickInv(InventoryClickEvent e) {
        System.out.println("f");
        if(e.getView().getTitle().equals("Pages")) {
            e.setCancelled(true);
            System.out.println("a");
            if(e.getSlot() == 11)
                EnderChestInventory.openFor(Objects.requireNonNull(Bukkit.getPlayer(e.getWhoClicked().getUniqueId())), 1);
            if(e.getSlot() == 12)
                EnderChestInventory.openFor(Objects.requireNonNull(Bukkit.getPlayer(e.getWhoClicked().getUniqueId())), 2);
            if(e.getSlot() == 13)
                EnderChestInventory.openFor(Objects.requireNonNull(Bukkit.getPlayer(e.getWhoClicked().getUniqueId())), 3);
            if(e.getSlot() == 14)
                EnderChestInventory.openFor(Objects.requireNonNull(Bukkit.getPlayer(e.getWhoClicked().getUniqueId())), 4);
            if(e.getSlot() == 15)
                EnderChestInventory.openFor(Objects.requireNonNull(Bukkit.getPlayer(e.getWhoClicked().getUniqueId())), 5);
            System.out.println("b");
        }
        System.out.println("c");
    }

    public static ItemStack getPageIcon(int page) {
        var is = new ItemStack(Material.ENDER_CHEST);
        var meta = is.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN + "Page #" + page);
        meta.setLore(List.of(
            ChatColor.GRAY + "TODO: Description",
            "",
            ChatColor.YELLOW + "Click to open!"
        ));
        is.setItemMeta(meta);
        return is;
    }

}
