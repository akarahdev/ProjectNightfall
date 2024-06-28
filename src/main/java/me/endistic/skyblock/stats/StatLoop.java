package me.endistic.skyblock.stats;

import me.endistic.skyblock.SkyBlock;
import me.endistic.skyblock.data.DataStorage;
import me.endistic.skyblock.items.ItemDatabase;
import me.endistic.skyblock.items.ItemModifiers;
import me.endistic.skyblock.items.ItemSlot;
import me.endistic.skyblock.powerstones.PowerStone;
import me.endistic.skyblock.mobs.MobUtils;
import me.endistic.skyblock.statuseffects.StatusEffectData;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;

public class StatLoop {
    public static void tickStats() {
        for (var p : Bukkit.getOnlinePlayers()) {
            var playerStats = new StatsObject().getPlayerBaseStats();

            var items = new ItemStack[]{
                p.getInventory().getHelmet(),
                p.getInventory().getChestplate(),
                p.getInventory().getLeggings(),
                p.getInventory().getBoots(),
                p.getInventory().getItemInMainHand(),
                p.getInventory().getItemInOffHand()
            };

            var index = 0;
            for (var i : items) {
                index++;
                if (i == null || i.getAmount() < 1 || i.getType() == Material.AIR)
                    continue;
                var pdc = i.getItemMeta().getPersistentDataContainer();
                if (!pdc.has(SkyBlock.key("id")))
                    continue;


                var id = pdc.get(SkyBlock.key("id"), PersistentDataType.STRING);
                try {
                    if(index == 6) {
                        if(ItemDatabase.citem(id).getData().getType() != ItemSlot.SHIELD)
                            continue;
                    }
                    var mpdc = pdc.get(
                        SkyBlock.key("modifiers"),
                        PersistentDataType.TAG_CONTAINER
                    );
                    var modifiers = ItemModifiers.from(mpdc);
                    playerStats = playerStats.add(ItemDatabase.citem(id).getStats().withModifiers(modifiers));
                } catch (NullPointerException ignored) {}
            }

            var mp = 0;
            var ids = new ArrayList<String>();
            for (var i : DataStorage.accessoryBag.getOrDefault(p.getUniqueId(), new ArrayList<>())) {
                if(ItemDatabase.citem(i).getData().getType() != ItemSlot.ACCESSORY)
                    continue;
                if(ids.contains(i))
                    continue;
                ids.add(i);
                switch (ItemDatabase.citem(i).getData().getRarity()) {
                    case COMMON -> mp += 3;
                    case UNCOMMON -> mp += 5;
                    case RARE -> mp += 8;
                    case EPIC -> mp += 12;
                    case LEGENDARY -> mp += 16;
                    case MYTHIC -> mp += 22;
                    case DIVINE -> mp += 30;
                    case SUPREME -> mp += 38;
                    case ULTIMATE -> mp += 46;
                }
            }

            DataStorage.magicalPower.put(p.getUniqueId(), mp+100);

            var flat = PowerStone.idToStone(DataStorage.powerStones.getOrDefault(p.getUniqueId(), "empty"))
                .getBasePerkStats();
            var muld = PowerStone.idToStone(DataStorage.powerStones.getOrDefault(p.getUniqueId(), "empty"))
                .getMultipliedPerkStats()
                .multiply(PowerStone.mpToMultiplier(mp+100));
            playerStats = playerStats.add(flat);
            playerStats = playerStats.add(muld);


            var i = 0;
            for(StatusEffectData e : DataStorage.statusEffects.getOrDefault(p.getUniqueId(), new ArrayList<>())) {
                if(e.duration() >= 1) {
                    e.setDuration(e.duration() - 5);
                    e.id().stats().accept(playerStats, e.amplifier());
                } else {
                    DataStorage.statusEffects.get(p.getUniqueId()).remove(i);
                    i--;
                }
                i++;
            }

            DataStorage.playerStats.put(p.getUniqueId(), playerStats);

            p.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(
                (playerStats.getWalkSpeed()+50) / 1000
            );
            p.getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(1000);
            p.getAttribute(Attribute.PLAYER_ENTITY_INTERACTION_RANGE).setBaseValue(
                playerStats.getSwingRange()
            );
            p.getAttribute(Attribute.PLAYER_BLOCK_INTERACTION_RANGE).setBaseValue(
                playerStats.getSwingRange()
            );
            p.getAttribute(Attribute.GENERIC_JUMP_STRENGTH).setBaseValue(0.6);
            if (DataStorage.currentHealth.containsKey(p.getUniqueId()) &&
                DataStorage.currentHealth.get(p.getUniqueId()) <= 0) {
                DataStorage.currentHealth.put(p.getUniqueId(), playerStats.getMaxHealth());
                p.teleport(new Location(p.getWorld(), -2, 71, -62));
                p.sendTitle(
                    "",
                    ChatColor.RED + "YOU DIED!",
                    0,
                    20,
                    10
                );
                Bukkit.broadcastMessage(
                    ChatColor.RED + p.getName() + ChatColor.GRAY + " died."
                );
                for(var e : Bukkit.getWorld("world").getLivingEntities()) {
                    if(e.getPersistentDataContainer().has(SkyBlock.key("owner"))) {
                        if(MobUtils.getOwner(e).getUniqueId() == p.getUniqueId()) {
                            e.remove();
                        }
                    }
                }
            }

            if(DataStorage.currentHealth.get(p.getUniqueId())
            >= playerStats.getMaxHealth()) {
                DataStorage.currentHealth.put(p.getUniqueId(), playerStats.getMaxHealth());
            }

            p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(40);

            p.setLevel(
                DataStorage.currentHealth.get(p.getUniqueId()).intValue()
            );

            var pct =
                (DataStorage.currentHealth.get(p.getUniqueId())
                / DataStorage.playerStats.get(p.getUniqueId()).getMaxHealth())
                * 40;

            if(pct <= 2.0)
                pct = 2.0;

            p.setHealth(pct);
            p.setCooldown(Material.SHIELD, 1000);


        }
    }
}
