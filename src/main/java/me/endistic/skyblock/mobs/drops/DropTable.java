package me.endistic.skyblock.mobs.drops;

import me.endistic.skyblock.items.ItemDatabase;
import me.endistic.skyblock.items.ItemModifiers;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;

public class DropTable {
    /**
     * The internal hashmap backing the Drop Table.
     * Key = The ID of the item
     * Value = The chances to drop the item (x/1.0)
     */
    public HashMap<String, ArrayList<Double>> internalMap = new HashMap<>();

    /**
     * Adds a new drop to the drop table.
     * @param id The ID of the item to drop.
     * @param chance The chance to drop it.
     * @return The table.
     */
    public DropTable putDrop(String id, double chance) {
        if(!internalMap.containsKey(id))
            internalMap.put(id, new ArrayList<>());
        var h = internalMap.get(id);
        h.add(chance);
        return this;
    }

    /**
     * Executes a DropTable on a player.
     * The player will be told when they get a rare drop, and the items will be dropped
     * on the ground for them to collect.
     * @param p The player to execute on.
     * @param loc The location to spawn the dropped items at.
     */
    public void execute(Player p, Location loc) {
        for(var i : internalMap.entrySet()) {
            for(var e : i.getValue()) {
                if(Math.random() <= e) {
                    var item = ItemDatabase.database.get(i.getKey());
                    if(e <= 1.0/1000.0)
                        p.sendMessage(
                            ChatColor.RED + "" + ChatColor.BOLD + "OMEGA RARE DROP! "
                                + item.getData().getRarity().color + item.getData().getName()
                                + ChatColor.DARK_GRAY + " (" + e*100 + "% Drop Chance)"
                        );
                    else if(e <= 1.0/100.0)
                        p.sendMessage(
                            ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "SUPER RARE DROP! "
                                + item.getData().getRarity().color + item.getData().getName()
                                + ChatColor.DARK_GRAY + " (" + e*100 + "% Drop Chance)"
                        );
                    else if(e <= 1.0/10.0)
                        p.sendMessage(
                            ChatColor.GOLD + "" + ChatColor.BOLD + "RARE DROP! "
                                + item.getData().getRarity().color + item.getData().getName()
                                + ChatColor.DARK_GRAY + " (" + e*100 + "% Drop Chance)"
                        );

                    var entity = loc.getWorld().spawnEntity(
                        loc,
                        EntityType.ITEM
                    );
                    ((Item) entity).setItemStack(item.build(new ItemModifiers()));
                }
            }

        }
    }
}
