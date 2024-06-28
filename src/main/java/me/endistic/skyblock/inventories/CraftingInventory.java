package me.endistic.skyblock.inventories;

import me.endistic.skyblock.SkyBlock;
import me.endistic.skyblock.items.ItemDatabase;
import me.endistic.skyblock.items.ItemModifiers;
import org.bukkit.Bukkit;
import org.bukkit.Material;
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
import java.util.List;
import java.util.Objects;

public class CraftingInventory implements Listener {
    public static int resultSlot = 23;
    public static List<Integer> interactableSlots = List.of(10, 11, 12, 19, 20, 21, CraftingInventory.resultSlot, 28, 29, 30);

    public static Inventory getInventoryFor(Player p) {
        var inv = Bukkit.createInventory(null, 54, "Crafting     ");

        var pane = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        var paneMeta = pane.getItemMeta();
        assert paneMeta != null;
        paneMeta.setDisplayName("");
        pane.setItemMeta(paneMeta);

        var pane2 = new ItemStack(Material.RED_STAINED_GLASS_PANE);
        pane2.setItemMeta(paneMeta);

        for (var i = 0; i < 54; i++) {
            if (!interactableSlots.contains(i))
                inv.setItem(i, pane);
            if (i > 44)
                inv.setItem(i, pane2);
            if (i == 49)
                inv.setItem(i, InventoryItems.closeButton);
        }

        inv.setItem(0, InventoryItems.viewRecipesButton);
        inv.setItem(CraftingInventory.resultSlot, new ItemStack(Material.BARRIER));

        return inv;
    }

    public static void openFor(Player p) {
        p.openInventory(CraftingInventory.getInventoryFor(p));
    }

    public static void updateInvFor(
        HumanEntity p
    ) {
        for (var d = 0; d < 5; d++) {
            Bukkit.getScheduler().scheduleSyncDelayedTask(SkyBlock.main, () -> {
                p.getOpenInventory().setItem(CraftingInventory.resultSlot, new ItemStack(Material.BARRIER));

                var outputItems = new ArrayList<ItemStack>();
                for (var s : interactableSlots) {
                    outputItems.add(p.getOpenInventory().getItem(s));
                }

                outputItems.remove(6);

                for (var id : ItemDatabase.idsWithRecipes) {
                    var items = new ItemStack[9];
                    var itemIds = new String[9];
                    for (var index = 0; index < 9; index++) {
                        items[index] = outputItems.get(index);
                        itemIds[index] = "noid";
                        if (outputItems.get(index) != null && outputItems.get(index).hasItemMeta())
                            itemIds[index] = outputItems.get(index).getItemMeta().getPersistentDataContainer().getOrDefault(
                                SkyBlock.key("id"), PersistentDataType.STRING, "noid"
                            );
                    }

                    for (var r : ItemDatabase.citem(id).getRecipes()) {
                        var result = r.compareRecipe(items);
                        if (result.isValid()) {
                            var modifiers = new ItemModifiers();
                            var resultItem = ItemDatabase.citem(id);
                            ItemStack resultItemStack;
                            if (r.getModifierCloneSlot() != -1) {
                                modifiers = ItemModifiers.from(
                                    Objects.requireNonNull(outputItems.get(r.getModifierCloneSlot() - 1)
                                        .getItemMeta()
                                        .getPersistentDataContainer()
                                        .get(
                                            SkyBlock.key("modifiers"),
                                            PersistentDataType.TAG_CONTAINER
                                        ))
                                );
                                resultItemStack = resultItem.build(modifiers);
                            } else {
                                resultItemStack = resultItem.build(new ItemModifiers());
                            }
                            resultItemStack.setAmount(result.getResultAmount());
                            p.getOpenInventory().setItem(CraftingInventory.resultSlot, resultItemStack);
                        }
                    }

                }
            }, d);
        }

    }

    @EventHandler
    public void invClick(InventoryClickEvent e) {
        if (e.getClickedInventory() == e.getWhoClicked().getInventory())
            return;

        if (e.getView().getTitle().equals("Crafting     ")) {
            if (e.getSlot() == 49) {
                e.setCancelled(true);
                e.getWhoClicked().closeInventory();
                return;
            }
            if (e.getSlot() == 0) {
                e.setCancelled(true);
                RecipesInventory.openFor(Bukkit.getPlayer(e.getWhoClicked().getUniqueId()));
                return;
            }
            if (e.getSlot() == CraftingInventory.resultSlot) {
                if (e.getCurrentItem() == null) {
                    e.setCancelled(true);
                    return;
                }
                if(e.getCurrentItem().getType() == Material.BARRIER) {
                    e.setCancelled(true);
                    return;
                }

                if (e.getClickedInventory().getItem(CraftingInventory.resultSlot) == null) {
                    e.setCancelled(true);
                    return;
                }
                for (var slot : interactableSlots)
                    if (slot != CraftingInventory.resultSlot)
                        e.getWhoClicked().getOpenInventory().setItem(slot, new ItemStack(Material.AIR));

                Bukkit.getScheduler().scheduleSyncDelayedTask(SkyBlock.main, () -> {
                    e.getWhoClicked().getOpenInventory().setItem(CraftingInventory.resultSlot, new ItemStack(Material.AIR));
                });
                return;
            }

            e.getWhoClicked().getOpenInventory().setItem(CraftingInventory.resultSlot, new ItemStack(Material.AIR));

            if (e.getCurrentItem() != null
                && (e.getCurrentItem().getType() == Material.BLACK_STAINED_GLASS_PANE
                || e.getCurrentItem().getType() == Material.RED_STAINED_GLASS_PANE)) {
                e.setCancelled(true);
                return;
            }

            updateInvFor(e.getWhoClicked());


        }
    }

    @EventHandler
    public void invInteract(InventoryDragEvent e) {
        if (e.getView().getTitle().equals("Crafting     "))
            updateInvFor(e.getWhoClicked());
    }

    @EventHandler
    public void invClose(InventoryCloseEvent e) {
        if (e.getView().getTitle().equals("Crafting     ")) {

            for (var i : interactableSlots) {
                if (i == CraftingInventory.resultSlot)
                    continue;
                var item = e.getInventory().getItem(i);
                if (item != null && item.getType() != Material.AIR)
                    e.getPlayer().getInventory().addItem(item);
            }
        }
    }
}
