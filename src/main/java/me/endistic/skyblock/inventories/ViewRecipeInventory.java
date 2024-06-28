package me.endistic.skyblock.inventories;

import me.endistic.skyblock.SkyBlock;
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
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.Objects;

public class ViewRecipeInventory implements Listener {
    public static Inventory getInventoryFor(Player p, String recipeId, int recipeNumber) {
        var inv = Bukkit.createInventory(null, 9 * 6, "Viewing Recipe");
        var pane = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        var paneMeta = pane.getItemMeta();
        assert paneMeta != null;
        paneMeta.setDisplayName("");
        pane.setItemMeta(paneMeta);

        var pane2 = new ItemStack(Material.RED_STAINED_GLASS_PANE);
        pane2.setItemMeta(paneMeta);

        var insertionSlots = new ArrayList<>(CraftingInventory.interactableSlots);
        insertionSlots.remove((Integer) CraftingInventory.resultSlot);


        for (var i = 0; i < 54; i++) {
            if (!insertionSlots.contains(i))
                inv.setItem(i, pane);
            if (i > 44)
                inv.setItem(i, pane2);
            if (i == 49)
                inv.setItem(i, InventoryItems.closeButton);
        }

        if (recipeNumber > 1) {
            var arrow = new ItemStack(Material.ARROW);
            var arrowMeta = arrow.getItemMeta();
            arrowMeta.setDisplayName(ChatColor.AQUA + "Previous Page");
            var pdc = arrowMeta.getPersistentDataContainer();
            pdc.set(SkyBlock.key("view_recipe_page_goto"), PersistentDataType.INTEGER, recipeNumber - 1);
            pdc.set(SkyBlock.key("view_recipe_id_goto"), PersistentDataType.STRING, recipeId);
            arrow.setItemMeta(arrowMeta);
            inv.setItem(45, arrow);
        }
        if (recipeNumber < ItemDatabase.citem(recipeId).getRecipes().size()) {
            var arrow = new ItemStack(Material.ARROW);
            var arrowMeta = arrow.getItemMeta();
            arrowMeta.setDisplayName(ChatColor.AQUA + "Next Page");
            var pdc = arrowMeta.getPersistentDataContainer();
            pdc.set(SkyBlock.key("view_recipe_page_goto"), PersistentDataType.INTEGER, recipeNumber + 1);
            pdc.set(SkyBlock.key("view_recipe_id_goto"), PersistentDataType.STRING, recipeId);
            arrow.setItemMeta(arrowMeta);
            inv.setItem(53, arrow);
        }

        var recipe = ItemDatabase.citem(recipeId).getRecipes().get(recipeNumber-1);

        for (var i = 0; i < 9; i++) {
            var slot = insertionSlots.get(i);
            if (Objects.equals(recipe.ids.get(i), "air"))
                continue;
            var bi = ItemDatabase.item(recipe.ids.get(i));
            bi.setAmount(recipe.counts.get(i));
            inv.setItem(slot, bi);
        }
        inv.setItem(CraftingInventory.resultSlot, ItemDatabase.item(recipeId));

        return inv;
    }

    public static void openFor(Player p, String recipeId, int recipeNumber) {
        p.openInventory(ViewRecipeInventory.getInventoryFor(p, recipeId, recipeNumber));
    }

    @EventHandler
    public void invClick(InventoryClickEvent e) {
        if (e.getView().getTitle().equals("Viewing Recipe")) {
            if (e.getCurrentItem() == null)
                return;
            if (e.getSlot() == 49) {
                RecipesInventory.openFor(Objects.requireNonNull(Bukkit.getPlayer(e.getWhoClicked().getUniqueId())));
            }
            if (e.getCurrentItem().getItemMeta().getPersistentDataContainer()
                .has(SkyBlock.key("view_recipe_page_goto"))) {
                var page = e.getCurrentItem().getItemMeta().getPersistentDataContainer()
                    .get(SkyBlock.key("view_recipe_page_goto"), PersistentDataType.INTEGER);
                var id = e.getCurrentItem().getItemMeta().getPersistentDataContainer()
                    .get(SkyBlock.key("view_recipe_id_goto"), PersistentDataType.STRING);

                ViewRecipeInventory.openFor(
                    Bukkit.getPlayer(e.getWhoClicked().getUniqueId()),
                    id,
                    page
                );
            }
            e.setCancelled(true);
        }


    }
}
