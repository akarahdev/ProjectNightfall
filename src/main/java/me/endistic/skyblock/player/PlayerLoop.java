package me.endistic.skyblock.player;

import me.endistic.skyblock.SkyBlock;
import me.endistic.skyblock.data.DataStorage;
import me.endistic.skyblock.inventories.InventoryItems;
import me.endistic.skyblock.items.ItemDatabase;
import me.endistic.skyblock.items.ItemModifiers;
import me.endistic.skyblock.stats.StatsObject;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffectType;

import java.util.Objects;

public class PlayerLoop {
    public static int time = 0;
    public static void tickLoop() {
        time++;
        for(var p : Bukkit.getOnlinePlayers()) {
            DataStorage.cold.put(
                p.getUniqueId(),
                0.0
            );

            var stats = DataStorage.playerStats.getOrDefault(p.getUniqueId(), new StatsObject().getPlayerBaseStats());

            p.removePotionEffect(PotionEffectType.DARKNESS);

            DataStorage.attackCooldown.put(
                p.getUniqueId(),
                DataStorage.attackCooldown.getOrDefault(p.getUniqueId(), 0)-1
            );

            for(int slot = 0; slot<41; slot++) {
                var i = p.getInventory().getItem(slot);
                if(i == null || i.getAmount() < 1 || i.getType() == Material.AIR || i.getItemMeta() == null)
                    continue;
                if(p.getGameMode() != GameMode.CREATIVE
                && !i.getItemMeta().getPersistentDataContainer().has(SkyBlock.key("id"))) {
                    p.getInventory().setItem(slot, new ItemStack(Material.AIR));
                    continue;
                }
                var id = i.getItemMeta().getPersistentDataContainer().getOrDefault(
                    SkyBlock.key("id"),
                    PersistentDataType.STRING,
                    "na"
                );
                var mpdc = i.getItemMeta().getPersistentDataContainer().getOrDefault(
                    SkyBlock.key("modifiers"),
                    PersistentDataType.TAG_CONTAINER,
                    i.getItemMeta().getPersistentDataContainer().getAdapterContext().newPersistentDataContainer()
                );
                if(!id.equals("na")) {
                    var item = ItemDatabase.database.get(id).build(ItemModifiers.from(mpdc));
                    var amount = i.getAmount();
                    item.setAmount(amount);
                    if(!Objects.equals(i.getItemMeta().getLore(), item.getItemMeta().getLore())
                    || !Objects.equals(i.getItemMeta().getDisplayName(), item.getItemMeta().getDisplayName())
                    || !Objects.equals(i.getType(), item.getType()))
                        p.getInventory().setItem(slot, item);
                }
            }

            var chp = DataStorage.currentHealth.getOrDefault(p.getUniqueId(), 9999999.0);
            var cm = DataStorage.currentMana.getOrDefault(p.getUniqueId(), 9999999.0);

            if(time%20 == 0) {
                DataStorage.currentHealth.put(p.getUniqueId(),
                    Math.floor(
                        DataStorage.currentHealth.getOrDefault(p.getUniqueId(), 1000000.0)+(stats.getMaxHealth()/2000*20)
                    )
                );

                DataStorage.currentMana.put(p.getUniqueId(),
                    Math.floor(
                        DataStorage.currentMana.getOrDefault(p.getUniqueId(), 1000000.0)+(stats.getIntelligence()/2000*20)
                    )
                );
            }


            if(chp > stats.getMaxHealth()) {
                DataStorage.currentHealth.put(p.getUniqueId(), stats.getMaxHealth());
                chp = stats.getMaxHealth();
            }
            if(cm > stats.getIntelligence()) {
                DataStorage.currentMana.put(p.getUniqueId(), stats.getIntelligence());
                cm = stats.getIntelligence();
            }

            chp = Math.ceil(chp);
            cm = Math.ceil(cm);

            p.getInventory().setItem(8, InventoryItems.menuItem);

            p.spigot().sendMessage(
                ChatMessageType.ACTION_BAR,
                TextComponent.fromLegacy(
                    (ChatColor.RED + StatsObject.formatStatDecimal(chp) + "/" + StatsObject.formatStatDecimal(stats.getMaxHealth())
                        + "❤     " +
                        ChatColor.GREEN + StatsObject.formatStatDecimal(stats.getDefense())
                        + "❈     " +
                        ChatColor.AQUA + StatsObject.formatStatDecimal(cm) +  "/" + StatsObject.formatStatDecimal(stats.getIntelligence()) + "✎")
                )
            );


            p.removePotionEffect(PotionEffectType.BAD_OMEN);

            p.setFoodLevel(20);
            p.setSaturation(20);

            if(DataStorage.spatialMarks.containsKey(p.getUniqueId())) {
                p.spawnParticle(
                    Particle.WITCH,
                    DataStorage.spatialMarks.get(p.getUniqueId()),
                    1,
                    0.5, 0.5, 0.5
                );
            }
        }
    }
}
