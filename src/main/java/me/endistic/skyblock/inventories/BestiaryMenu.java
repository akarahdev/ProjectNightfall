package me.endistic.skyblock.inventories;

import me.endistic.skyblock.SkyBlock;
import me.endistic.skyblock.inventories.utils.BestiaryFilter;
import me.endistic.skyblock.mobs.MobDatabase;
import me.endistic.skyblock.mobs.MobId;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import java.util.List;

public class BestiaryMenu implements Listener {
    public static int resultSlot = 23;
    public static List<Integer> interactableSlots = List.of(10, 11, 12, 19, 20, 21, BestiaryMenu.resultSlot, 28, 29, 30);

    public static Inventory getInventoryFor(Player p, BestiaryFilter filter) {
        var inv = Bukkit.createInventory(null, 54, "Bestiary");

        var pane = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        var paneMeta = pane.getItemMeta();
        assert paneMeta != null;
        paneMeta.setDisplayName("");
        pane.setItemMeta(paneMeta);

        for (var i = 0; i < 54; i++) {
            inv.setItem(i, pane);
        }

        var forbiddenSlots = List.of(
            0,
            1,
            2,
            3,
            4,
            5,
            6,
            7,
            8,
            9,
            9+8,
            10+8,
            9+8+8,
            10+8+8,
            9+8+8+8,
            10+8+8+8,
            9+8+8+8+8,
            10+8+8+8+8,
            9+8+8+8+8+8,
            10+8+8+8+8+8
        );
        var i = 0;
        for(var h : MobDatabase.mobs()) {
            while(forbiddenSlots.contains(i))
                i++;
            if(h.getBestiaryFilter() != filter)
                continue;
            inv.setItem(i, h.getBestiaryData().toIcon());
            i++;
        }

        inv.setItem(49, InventoryItems.closeButton);
        return inv;
    }

    public static void openFor(Player p, BestiaryFilter filter) {
        p.openInventory(BestiaryMenu.getInventoryFor(p, filter));
    }

    @EventHandler
    public void invClick(InventoryClickEvent e) {
        if(e.getView().getTitle().equals("Bestiary")) {
            e.setCancelled(true);
            if(e.getSlot() == 49)
                e.getWhoClicked().closeInventory();
            if(e.getCurrentItem() == null)
                return;
            if(e.getCurrentItem().getItemMeta().getPersistentDataContainer().has(SkyBlock.key("bestiary.open_mob"))) {
                var value = e.getCurrentItem().getItemMeta().getPersistentDataContainer().get(
                    SkyBlock.key("bestiary.open_mob"), PersistentDataType.STRING);
                var data = MobDatabase.get(MobId.valueOf(value)).getBestiaryData();
                MobInfoInventory.openFor(
                    Bukkit.getPlayer(e.getWhoClicked().getUniqueId()),
                    data
                );
            }
        }

    }
}
