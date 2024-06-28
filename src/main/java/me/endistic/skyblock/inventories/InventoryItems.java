package me.endistic.skyblock.inventories;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class InventoryItems {
    public static ItemStack viewRecipesButton = new ItemStack(Material.KNOWLEDGE_BOOK);
    public static ItemStack closeButton = new ItemStack(Material.BARRIER);
    public static ItemStack menuItem = new ItemStack(Material.NETHER_STAR);
    static {
        var meta = viewRecipesButton.getItemMeta();
        var lore = new ArrayList<String>();
        lore.add(ChatColor.GRAY + "View all the recipes in the game!");
        lore.add("");
        lore.add(ChatColor.YELLOW + "Click to view!");
        meta.setLore(lore);
        viewRecipesButton.setItemMeta(meta);

        meta = closeButton.getItemMeta();
        meta.setDisplayName(ChatColor.RED + "Close");
        closeButton.setItemMeta(meta);

        meta = menuItem.getItemMeta();
        meta.setDisplayName(ChatColor.AQUA + "SkyBlock Menu " + ChatColor.GRAY + "(Right Click)");
        menuItem.setItemMeta(meta);
    }
}
