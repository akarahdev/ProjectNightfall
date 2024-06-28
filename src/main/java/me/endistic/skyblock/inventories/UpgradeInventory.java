package me.endistic.skyblock.inventories;

import me.endistic.skyblock.SkyBlock;
import me.endistic.skyblock.items.ItemDatabase;
import me.endistic.skyblock.items.ItemModifiers;
import org.bukkit.*;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;

public class UpgradeInventory implements Listener {
    public static int resultSlot = 22;
    public static int anvilSlot = 31;

    public static Inventory getInventoryFor(Player p) {
        var inv = Bukkit.createInventory(null, 54, "Upgrading     ");

        var pane = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        var paneMeta = pane.getItemMeta();
        assert paneMeta != null;
        paneMeta.setDisplayName("");
        pane.setItemMeta(paneMeta);

        var pane2 = new ItemStack(Material.RED_STAINED_GLASS_PANE);
        pane2.setItemMeta(paneMeta);


        var anvil = new ItemStack(Material.ANVIL);
        var anvilMeta = anvil.getItemMeta();
        // TODO: add way to see list of upgradable items
        anvilMeta.setDisplayName(ChatColor.RED + "Put an item in!");

        for (var i = 0; i < 54; i++) {
            if (i != UpgradeInventory.resultSlot
                && i != UpgradeInventory.anvilSlot)
                inv.setItem(i, pane);
            if (i == UpgradeInventory.anvilSlot)
                inv.setItem(i, new ItemStack(Material.ANVIL));
            if (i > 44)
                inv.setItem(i, pane2);
            if (i == 49)
                inv.setItem(i, InventoryItems.closeButton);
        }

        UpgradeInventory.updateInvFor(p);

        return inv;
    }

    public static void openFor(Player p) {
        p.openInventory(UpgradeInventory.getInventoryFor(p));
    }

    public static void updateInvFor(
        HumanEntity p
    ) {
        Bukkit.getScheduler().scheduleSyncDelayedTask(SkyBlock.main, () -> {
            var item = p.getOpenInventory().getItem(UpgradeInventory.resultSlot);
            if (item == null) {
                var anvil = new ItemStack(Material.ANVIL);
                var meta = anvil.getItemMeta();
                meta.setDisplayName(ChatColor.RED + "Add an item!");
                p.getOpenInventory().setItem(UpgradeInventory.anvilSlot, anvil);
                return;
            }
            var id = item.getItemMeta().getPersistentDataContainer().get(SkyBlock.key("id"), PersistentDataType.STRING);
            var stars = item.getItemMeta()
                .getPersistentDataContainer().get(SkyBlock.key("modifiers"), PersistentDataType.TAG_CONTAINER)
                .get(SkyBlock.key("stars"), PersistentDataType.INTEGER);
            if(ItemDatabase.citem(id).getUpgradingCost() == null) {
                var anvil2 = new ItemStack(Material.ANVIL);
                var anvil2Meta = anvil2.getItemMeta();
                anvil2Meta.setDisplayName(ChatColor.RED + "This item is not upgradable!");
                return;
            }
            if (!ItemDatabase.citem(id).getUpgradingCost().upgradeMap.containsKey(stars + 1)) {
                var anvil = new ItemStack(Material.ANVIL);
                var meta = anvil.getItemMeta();
                meta.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "MAXED OUT!");
                anvil.setItemMeta(meta);
                p.getOpenInventory().setItem(UpgradeInventory.anvilSlot, anvil);
                return;
            }
            var upgradeCosts = ItemDatabase.citem(id).getUpgradingCost().upgradeMap.get(stars + 1);

            var anvil = new ItemStack(Material.ANVIL);
            var meta = anvil.getItemMeta();
            meta.setDisplayName(ChatColor.YELLOW + "Upgrade Item");
            var lore = new ArrayList<String>();
            lore.add(ChatColor.GRAY + "Cost:");
            for (var c : upgradeCosts) {
                var name = ItemDatabase.citem(c.id).getData().getRarity().getColor()
                    + ItemDatabase.citem(c.id).getData().getName();
                lore.add(ChatColor.AQUA + "» " + ChatColor.DARK_GRAY + c.amount + "x " + name);
            }
            meta.setLore(lore);
            anvil.setItemMeta(meta);

            p.getOpenInventory().setItem(UpgradeInventory.anvilSlot, anvil);
        }, 2);
    }

    @EventHandler
    public void invClick(InventoryClickEvent e) {
        if (e.getClickedInventory() == e.getWhoClicked().getInventory())
            return;

        if (e.getView().getTitle().equals("Upgrading     ")) {
            if (e.getSlot() == 49) {
                e.setCancelled(true);
                e.getWhoClicked().closeInventory();
                return;
            }
            if (e.getSlot() == UpgradeInventory.resultSlot) {
                UpgradeInventory.updateInvFor(e.getWhoClicked());
                return;
            }

            if (e.getSlot() == UpgradeInventory.anvilSlot) {
                e.setCancelled(true);

                var item = e.getWhoClicked().getOpenInventory().getItem(UpgradeInventory.resultSlot);
                var id = item.getItemMeta().getPersistentDataContainer().get(SkyBlock.key("id"), PersistentDataType.STRING);
                var stars = item.getItemMeta()
                    .getPersistentDataContainer().get(SkyBlock.key("modifiers"), PersistentDataType.TAG_CONTAINER)
                    .get(SkyBlock.key("stars"), PersistentDataType.INTEGER);
                if (ItemDatabase.citem(id).getUpgradingCost() == null)
                    return;
                if (!ItemDatabase.citem(id).getUpgradingCost().upgradeMap.containsKey(stars + 1))
                    return;

                var upgradeCosts = ItemDatabase.citem(id).getUpgradingCost().upgradeMap.get(stars + 1);

                for (var c : upgradeCosts) {
                    if (!c.match(Bukkit.getPlayer(e.getWhoClicked().getUniqueId()))) {
                        Bukkit.getPlayer(e.getWhoClicked().getUniqueId()).playSound(
                            Bukkit.getPlayer(e.getWhoClicked().getUniqueId()).getEyeLocation(),
                            Sound.BLOCK_ANVIL_PLACE,
                            SoundCategory.MASTER,
                            1,
                            1
                        );
                        return;
                    }

                }
                for (var c : upgradeCosts) {
                    c.apply(Bukkit.getPlayer(e.getWhoClicked().getUniqueId()));
                }
                var modifiers = ItemModifiers.from(
                    item.getItemMeta()
                        .getPersistentDataContainer().get(SkyBlock.key("modifiers"), PersistentDataType.TAG_CONTAINER)
                );
                var i = ItemDatabase.citem(id).build(modifiers.setStars(modifiers.getStars() + 1));
                e.getWhoClicked().getOpenInventory().setItem(UpgradeInventory.resultSlot, i);

                Bukkit.getPlayer(e.getWhoClicked().getUniqueId()).playSound(
                    Bukkit.getPlayer(e.getWhoClicked().getUniqueId()).getEyeLocation(),
                    Sound.BLOCK_ANVIL_USE,
                    SoundCategory.MASTER,
                    1,
                    1
                );
            }

            if (e.getCurrentItem() != null &&
                (e.getCurrentItem().getType() == Material.BLACK_STAINED_GLASS_PANE
                    || e.getCurrentItem().getType() == Material.RED_STAINED_GLASS_PANE)) {
                e.setCancelled(true);
                return;
            }

            updateInvFor(e.getWhoClicked());
        }
    }

    @EventHandler
    public void invInteract(InventoryDragEvent e) {

    }

    @EventHandler
    public void invClose(InventoryCloseEvent e) {
        if (e.getView().getTitle().equals("Upgrading     ")) {
            var item = e.getInventory().getItem(UpgradeInventory.resultSlot);
            e.getPlayer().getInventory().addItem(item);
        }
    }
}
