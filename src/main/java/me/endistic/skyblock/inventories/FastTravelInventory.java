package me.endistic.skyblock.inventories;

import me.endistic.skyblock.data.DataStorage;
import me.endistic.skyblock.inventories.utils.MobInventoryData;
import me.endistic.skyblock.items.ItemDatabase;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class FastTravelInventory implements Listener {
    public static Inventory getInventoryFor(Player p) {
        var inv = Bukkit.createInventory(null, 45, "Fast Travel");

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
            getHubIcon(),
            getWildernessIcon(),
            getGraveyardIcon(),
            getCastleIcon(),
            getHighLevelIcon()
        };

        for(var i : items) {
            inv.setItem(abilitySlots[abilityIndex], i);
            abilityIndex++;
        }


        inv.setItem(40, InventoryItems.closeButton);
        return inv;
    }

    public static void openFor(Player p) {
        p.openInventory(FastTravelInventory.getInventoryFor(p));
    }

    @EventHandler
    public void clickInv(InventoryClickEvent e) {
        if(e.getView().getTitle().equals("Fast Travel")) {
            e.setCancelled(true);
            if(e.getSlot() == 11)
                e.getWhoClicked().teleport(new Location(e.getWhoClicked().getWorld(), -2, 71.5, -62));
            if(e.getSlot() == 12)
                e.getWhoClicked().teleport(new Location(e.getWhoClicked().getWorld(), 111, 70, 51));
            if(e.getSlot() == 13)
                e.getWhoClicked().teleport(new Location(e.getWhoClicked().getWorld(), -97, 71.5, -50));
            if(e.getSlot() == 14)
                e.getWhoClicked().teleport(new Location(e.getWhoClicked().getWorld(), -204, 91, 49));
            if(e.getSlot() == 15)
                e.getWhoClicked().teleport(new Location(e.getWhoClicked().getWorld(), -111, 70, 86));

        }
    }

    public static ItemStack getHubIcon() {
        var is = new ItemStack(Material.SHORT_GRASS);
        var meta = is.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN + "The Hub");
        meta.setLore(List.of(
            ChatColor.GRAY + "TODO: Description",
            "",
            ChatColor.YELLOW + "Click to teleport!"
        ));
        is.setItemMeta(meta);
        return is;
    }

    public static ItemStack getWildernessIcon() {
        var is = new ItemStack(Material.DARK_OAK_SAPLING);
        var meta = is.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN + "The Wilderness");
        meta.setLore(List.of(
            ChatColor.GRAY + "TODO: Description",
            "",
            ChatColor.YELLOW + "Click to teleport!"
        ));
        is.setItemMeta(meta);
        return is;
    }

    public static ItemStack getGraveyardIcon() {
        var is = new ItemStack(Material.STONE_BRICKS);
        var meta = is.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN + "The Graveyard");
        meta.setLore(List.of(
            ChatColor.GRAY + "TODO: Description",
            "",
            ChatColor.YELLOW + "Click to teleport!"
        ));
        is.setItemMeta(meta);
        return is;
    }

    public static ItemStack getCastleIcon() {
        var is = new ItemStack(Material.CLAY);
        var meta = is.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN + "The Ruins");
        meta.setLore(List.of(
            ChatColor.GRAY + "TODO: Description",
            "",
            ChatColor.YELLOW + "Click to teleport!"
        ));
        is.setItemMeta(meta);
        return is;
    }

    public static ItemStack getHighLevelIcon() {
        var is = new ItemStack(Material.SNOW);
        var meta = is.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN + "The High Level");
        meta.setLore(List.of(
            ChatColor.GRAY + "TODO: Description",
            "",
            ChatColor.YELLOW + "Click to teleport!"
        ));
        is.setItemMeta(meta);
        return is;
    }
}
