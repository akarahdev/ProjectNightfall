package me.endistic.skyblock.inventories;

import me.endistic.skyblock.SkyBlock;
import me.endistic.skyblock.items.ItemDatabase;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.persistence.PersistentDataType;

public class RecipesInventory implements Listener {
    public static Inventory getInventoryFor(Player p) {
        var inv = Bukkit.createInventory(null, 9 * 6, "Recipes");
        var slot = 0;
        for (var id : ItemDatabase.idsWithRecipes) {
            var item = ItemDatabase.item(id);
            var meta = item.getItemMeta();
            var pdc = meta.getPersistentDataContainer();
            pdc.set(SkyBlock.key("open_recipe"), PersistentDataType.STRING, id);
            var lore = meta.getLore();
            lore.add("");
            lore.add(ChatColor.YELLOW + "Click to view recipes!");
            meta.setLore(lore);
            item.setItemMeta(meta);
            inv.setItem(slot, item);
            slot++;
        }
        return inv;
    }

    public static void openFor(Player p) {
        p.openInventory(RecipesInventory.getInventoryFor(p));
    }

    @EventHandler
    public void invClick(InventoryClickEvent e) {
        if (e.getCurrentItem() == null)
            return;
        if (e.getView().getTitle().equals("Recipes")) {
            e.setCancelled(true);
            var pdc = e.getCurrentItem().getItemMeta().getPersistentDataContainer();
            if (pdc.has(SkyBlock.key("open_recipe"))) {
                var set = pdc.get(SkyBlock.key("open_recipe"), PersistentDataType.STRING);
                ViewRecipeInventory.openFor((Player) e.getWhoClicked(), set, 1);
            }
        }
    }
}
