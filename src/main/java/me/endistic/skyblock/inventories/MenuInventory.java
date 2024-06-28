package me.endistic.skyblock.inventories;

import me.endistic.skyblock.data.DataStorage;
import me.endistic.skyblock.inventories.utils.BestiaryFilter;
import me.endistic.skyblock.items.CustomItem;
import me.endistic.skyblock.items.ItemTexture;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class MenuInventory implements Listener {
    public static int resultSlot = 23;
    public static List<Integer> interactableSlots = List.of(10, 11, 12, 19, 20, 21, MenuInventory.resultSlot, 28, 29, 30);

    public static Inventory getInventoryFor(Player p) {
        var inv = Bukkit.createInventory(null, 54, "SkyBlock Menu");

        var pane = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        var paneMeta = pane.getItemMeta();
        assert paneMeta != null;
        paneMeta.setDisplayName("");
        pane.setItemMeta(paneMeta);

        for (var i = 0; i < 54; i++) {
            inv.setItem(i, pane);
        }

        inv.setItem(13, getPlayerHead(p));
        inv.setItem(21, getBossBestiaryIcon());
        inv.setItem(22, getCraftingTable());
        inv.setItem(23, getEnderChest());
        inv.setItem(31, getFastTravelIcon());

        inv.setItem(45, getUpgraderIcon(p));
        inv.setItem(46, getMobsBestiaryIcon());

        inv.setItem(49, InventoryItems.closeButton);

        inv.setItem(52, getAccessoryBagIcon(p));
        inv.setItem(53, getPowerStoneIcon(p));
        return inv;
    }

    public static void openFor(Player p) {
        p.openInventory(MenuInventory.getInventoryFor(p));
    }

    public static ItemStack getCraftingTable() {
        var crafting = new ItemStack(Material.CRAFTING_TABLE);
        var craftingMeta = crafting.getItemMeta();
        craftingMeta.setDisplayName(ChatColor.YELLOW + "Crafting Table");
        var craftingLore = new ArrayList<String>();
        craftingLore.add(ChatColor.GRAY + "Click to open the Crafting Table");
        craftingLore.add(ChatColor.GRAY + "to craft up items!");
        craftingLore.add("");
        craftingLore.add(ChatColor.YELLOW + "Click to view!");
        craftingMeta.setLore(craftingLore);
        crafting.setItemMeta(craftingMeta);
        return crafting;
    }

    public static ItemStack getPlayerHead(Player p) {
        var player = CustomItem.headWithTexture(new ItemStack(Material.PLAYER_HEAD), p.getUniqueId());
        var playerMeta = player.getItemMeta();
        var playerLore = DataStorage.playerStats.get(p.getUniqueId()).getCVisualStatsLore();
        playerLore.add("");
        playerLore.add(ChatColor.YELLOW + "Click to view more! " + ChatColor.RED + ChatColor.BOLD + "WIP");
        playerMeta.setLore(playerLore);
        playerMeta.setDisplayName(ChatColor.YELLOW + p.getName() + "'s Stats");
        player.setItemMeta(playerMeta);
        return player;
    }

    public static ItemStack getBossBestiaryIcon() {
        var bestiary = new ItemStack(Material.IRON_SWORD);
        var bestiaryMeta = bestiary.getItemMeta();
        bestiaryMeta.setDisplayName(ChatColor.GREEN + "Bosses");
        var bestiaryLore = new ArrayList<String>();
        bestiaryLore.add(ChatColor.GRAY + "View a list of bosses");
        bestiaryLore.add(ChatColor.GRAY + "and summon them!");
        bestiaryLore.add("");
        bestiaryLore.add(ChatColor.YELLOW + "Click to view!");
        bestiaryMeta.setLore(bestiaryLore);
        bestiaryMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ADDITIONAL_TOOLTIP);
        bestiary.setItemMeta(bestiaryMeta);
        return bestiary;
    }

    public static ItemStack getMobsBestiaryIcon() {
        var bestiary = new ItemStack(Material.ZOMBIE_VILLAGER_SPAWN_EGG);
        var bestiaryMeta = bestiary.getItemMeta();
        bestiaryMeta.setDisplayName(ChatColor.GREEN + "Mobs");
        var bestiaryLore = new ArrayList<String>();
        bestiaryLore.add(ChatColor.GRAY + "View a list of mobs");
        bestiaryLore.add(ChatColor.GRAY + "and details about them!");
        bestiaryLore.add("");
        bestiaryLore.add(ChatColor.YELLOW + "Click to view!");
        bestiaryMeta.setLore(bestiaryLore);
        bestiaryMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ADDITIONAL_TOOLTIP);
        bestiary.setItemMeta(bestiaryMeta);
        return bestiary;
    }

    public static ItemStack getEnderChest() {
        var bestiary = new ItemStack(Material.ENDER_CHEST);
        var bestiaryMeta = bestiary.getItemMeta();
        bestiaryMeta.setDisplayName(ChatColor.GREEN + "Ender Chest");
        var bestiaryLore = new ArrayList<String>();
        bestiaryLore.add(ChatColor.GRAY + "Store extra items!");
        bestiaryLore.add("");
        bestiaryLore.add(ChatColor.YELLOW + "Click to view!");
        bestiaryMeta.setLore(bestiaryLore);
        bestiaryMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ADDITIONAL_TOOLTIP);
        bestiary.setItemMeta(bestiaryMeta);
        return bestiary;
    }

    public static ItemStack getUpgraderIcon(Player p) {
        var bestiary = new ItemStack(Material.ANVIL);
        var bestiaryMeta = bestiary.getItemMeta();
        bestiaryMeta.setDisplayName(ChatColor.GREEN + "Upgrader");
        var bestiaryLore = new ArrayList<String>();
        bestiaryLore.add(ChatColor.GRAY + "Upgrade your items here!");
        bestiaryLore.add(ChatColor.YELLOW + "Click to view!");
        bestiaryMeta.setLore(bestiaryLore);
        bestiaryMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ADDITIONAL_TOOLTIP);
        bestiary.setItemMeta(bestiaryMeta);
        return bestiary;
    }

    public static ItemStack getAccessoryBagIcon(Player p) {
        var bestiary = CustomItem.headWithTexture(new ItemStack(Material.PLAYER_HEAD), ItemTexture.ACCESSORY_BAG);
        var bestiaryMeta = bestiary.getItemMeta();
        bestiaryMeta.setDisplayName(ChatColor.GREEN + "Accessory Bag");
        var bestiaryLore = new ArrayList<String>();
        bestiaryLore.add(ChatColor.GRAY + "Put accessories in here!");
        bestiaryLore.add("");
        bestiaryLore.add(ChatColor.GRAY + "Magical Power: " + ChatColor.AQUA
        + DataStorage.magicalPower.get(p.getUniqueId()));
        bestiaryLore.add("");
        bestiaryLore.add(ChatColor.YELLOW + "Click to view!");
        bestiaryMeta.setLore(bestiaryLore);
        bestiaryMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ADDITIONAL_TOOLTIP);
        bestiary.setItemMeta(bestiaryMeta);
        return bestiary;
    }

    public static ItemStack getPowerStoneIcon(Player p) {
        var bestiary = CustomItem.headWithTexture(new ItemStack(Material.PLAYER_HEAD), ItemTexture.ACCESSORY_BAG);
        var bestiaryMeta = bestiary.getItemMeta();
        bestiaryMeta.setDisplayName(ChatColor.GREEN + "Power Stone");
        var bestiaryLore = new ArrayList<String>();
        bestiaryLore.add(ChatColor.GRAY + "Select a power stone!");
        bestiaryLore.add("");
        bestiaryLore.add(ChatColor.GRAY + "Stone: " + ChatColor.AQUA
            + DataStorage.powerStones.get(p.getUniqueId()));
        bestiaryLore.add("");
        bestiaryLore.add(ChatColor.YELLOW + "Click to view!");
        bestiaryMeta.setLore(bestiaryLore);
        bestiaryMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ADDITIONAL_TOOLTIP);
        bestiary.setItemMeta(bestiaryMeta);
        return bestiary;
    }

    public static ItemStack getFastTravelIcon() {
        var bestiary = CustomItem.headWithTexture(new ItemStack(Material.PLAYER_HEAD), ItemTexture.EARTH);
        var bestiaryMeta = bestiary.getItemMeta();
        bestiaryMeta.setDisplayName(ChatColor.BLUE + "Fast Travel");
        var bestiaryLore = new ArrayList<String>();
        bestiaryLore.add(ChatColor.GRAY + "Warp to critical areas quickly!!");
        bestiaryLore.add("");
        bestiaryLore.add(ChatColor.YELLOW + "Click to view!");
        bestiaryMeta.setLore(bestiaryLore);
        bestiaryMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ADDITIONAL_TOOLTIP);
        bestiary.setItemMeta(bestiaryMeta);
        return bestiary;
    }

    @EventHandler
    public void invClick(InventoryClickEvent e) {
        if (e.getClickedInventory() == e.getWhoClicked().getInventory()) {
            return;
        }
        if(e.getView().getTitle().equals("SkyBlock Menu")) {
            e.setCancelled(true);
            if(e.getSlot() == 21)
                BestiaryMenu.openFor(Bukkit.getPlayer(e.getWhoClicked().getUniqueId()), BestiaryFilter.BOSS);
            if(e.getSlot() == 22)
                CraftingInventory.openFor(Bukkit.getPlayer(e.getWhoClicked().getUniqueId()));
            if(e.getSlot() == 23)
                EnderChestPageInventory.openFor(Bukkit.getPlayer(e.getWhoClicked().getUniqueId()));
            if(e.getSlot() == 31)
                FastTravelInventory.openFor(Bukkit.getPlayer(e.getWhoClicked().getUniqueId()));
            if(e.getSlot() == 45)
                UpgradeInventory.openFor(Bukkit.getPlayer(e.getWhoClicked().getUniqueId()));
            if(e.getSlot() == 46)
                BestiaryMenu.openFor(Bukkit.getPlayer(e.getWhoClicked().getUniqueId()), BestiaryFilter.MOB);
            if(e.getSlot() == 52)
                AccessoryBagInventory.openFor(Bukkit.getPlayer(e.getWhoClicked().getUniqueId()));
            if(e.getSlot() == 53)
                PowerStonesInventory.openFor(Bukkit.getPlayer(e.getWhoClicked().getUniqueId()));
            if(e.getSlot() == 49)
                e.getWhoClicked().closeInventory();
        }

    }
}
