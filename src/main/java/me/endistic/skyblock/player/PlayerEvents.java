package me.endistic.skyblock.player;

import me.endistic.skyblock.SkyBlock;
import me.endistic.skyblock.cutscenes.Introduction;
import me.endistic.skyblock.data.DataStorage;
import me.endistic.skyblock.inventories.CraftingInventory;
import me.endistic.skyblock.inventories.MenuInventory;
import me.endistic.skyblock.items.ItemDatabase;
import me.endistic.skyblock.items.ItemSlot;
import me.endistic.skyblock.mobs.DamageType;
import me.endistic.skyblock.mobs.MobDatabase;
import me.endistic.skyblock.mobs.MobId;
import me.endistic.skyblock.player.data.PlayerProfileNBT;
import me.endistic.skyblock.stats.CalculateDamage;
import me.endistic.skyblock.stats.StatsObject;
import me.endistic.skyblock.mobs.MobUtils;
import net.querz.nbt.io.NBTUtil;
import net.querz.nbt.tag.CompoundTag;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.*;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.persistence.PersistentDataType;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class PlayerEvents implements Listener {


    @EventHandler(priority = EventPriority.LOWEST)
    public void pdmg(EntityDamageEvent e) {
        if (e instanceof Player)
            e.setDamage(0.0);
    }

    @EventHandler
    public void join(PlayerJoinEvent e) {
        DataStorage.cold.put(e.getPlayer().getUniqueId(), 0.0);

        Bukkit.getScheduler().scheduleSyncDelayedTask(SkyBlock.main, () -> {
            var player = e.getPlayer();
            player.getInventory().clear();
            player.teleport(new Location(Bukkit.getWorld("world"), -2.5, 71.5, -72.5));
            player.getActivePotionEffects().clear();

            Bukkit.getScheduler().scheduleSyncDelayedTask(SkyBlock.main, () -> {
                try {
                    var profile = new PlayerProfileNBT(
                        (CompoundTag) NBTUtil.read(Path.of(
                            SkyBlock.profileFolder.getPath() + "/" + player.getUniqueId() + ".nbt").toFile()).getTag()
                    );
                    profile.apply(player);
                } catch (IOException e2) {
                    DataStorage.playerStats.put(player.getUniqueId(), new StatsObject());
                    DataStorage.combatXp.put(player.getUniqueId(), 0);
                    DataStorage.cold.put(player.getUniqueId(), 0.0);
                    DataStorage.currentHealth.put(player.getUniqueId(), 9999.0);
                    DataStorage.currentMana.put(player.getUniqueId(), 9999.0);

                    Bukkit.getScheduler().scheduleSyncDelayedTask(SkyBlock.main, () -> {
                        Introduction.executor(player).nextStep();
                    }, 10);
                }
            }, 10);
        }, 10);


    }

    @EventHandler
    public void quit(PlayerQuitEvent e) {
        var profile = new PlayerProfileNBT(e.getPlayer());
        var path = Path.of(SkyBlock.profileFolder.getPath() + "/" + e.getPlayer().getUniqueId() + ".nbt");
        try {
            if(!Files.exists(path))
                Files.createFile(path);
            NBTUtil.write(profile.base, path.toFile());
        } catch (IOException e2) {
            e2.printStackTrace();
        }

        for (var e2 : Bukkit.getWorld("world").getLivingEntities()) {
            var pdc = e2.getPersistentDataContainer();
            if (pdc.has(SkyBlock.key("owner"))) {
                var owner = MobUtils.getOwner(e2).getUniqueId();
                if (owner == e.getPlayer().getUniqueId()) {
                    e2.remove();
                }
            }
        }
    }

    @EventHandler
    public void clickInv(InventoryClickEvent e) {
        if (e.getClickedInventory() == null)
            return;
        if (e.getClickedInventory().getType() == InventoryType.PLAYER
            && e.getSlot() == 8)
            MenuInventory.openFor(Bukkit.getPlayer(e.getWhoClicked().getUniqueId()));

    }

    @EventHandler
    public void rightClick(PlayerInteractEvent e) {
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK
            || e.getAction() == Action.RIGHT_CLICK_AIR) {
            if (e.getPlayer().getInventory().getHeldItemSlot() == 8) {
                MenuInventory.openFor(e.getPlayer());
                e.setCancelled(true);
                return;
            }
        }

        try {
            if (e.getAction() == Action.RIGHT_CLICK_BLOCK
                && e.getClickedBlock().getType() == Material.CRAFTING_TABLE) {
                e.setCancelled(true);
                CraftingInventory.openFor(e.getPlayer());
                return;
            }
        } catch (Exception ignored) {
        }

        if (!(e.getPlayer().getInventory().getItemInMainHand().getType() != Material.AIR
            && e.getPlayer().getInventory().getItemInMainHand().getItemMeta() != null))
            return;


        var pdc = e.getPlayer().getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer();
        var id = pdc.getOrDefault(SkyBlock.key("id"), PersistentDataType.STRING, "none");
        if (!id.equals("none")) {
            if (e.getAction() == Action.RIGHT_CLICK_BLOCK
                || e.getAction() == Action.RIGHT_CLICK_AIR) {


                if (ItemDatabase.database.get(id).getData().getType() == ItemSlot.BOW) {
                    AttackFunctions.handleAttack(e.getPlayer());
                    return;
                }

                if (pdc.has(SkyBlock.key("id"))) {
                    e.setCancelled(true);
                    var item = ItemDatabase.database.get(
                        pdc.get(SkyBlock.key("id"), PersistentDataType.STRING)
                    );
                    if (item.getAbility() != null
                        && DataStorage.currentMana.get(e.getPlayer().getUniqueId())
                        >= item.getAbility().getDescription().getManaCost()) {
                        DataStorage.currentMana.put(e.getPlayer().getUniqueId(),
                            DataStorage.currentMana.get(e.getPlayer().getUniqueId())
                                - item.getAbility().getDescription().getManaCost()
                        );
                        item.getAbility().castSpell(e.getPlayer());
                    }

                }
                if (e.getPlayer().getGameMode() == GameMode.SURVIVAL)
                    e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void leftClick(PlayerInteractEvent e) {

        if (e.getClickedBlock() != null
        && e.getAction() == Action.RIGHT_CLICK_BLOCK)
            if (e.getClickedBlock().getType() == Material.ANVIL
                || e.getClickedBlock().getType() == Material.CHEST
                || e.getClickedBlock().getType() == Material.BARREL
                || e.getClickedBlock().getType() == Material.HOPPER
                || e.getClickedBlock().getType() == Material.ENCHANTING_TABLE
                || e.getClickedBlock().getType() == Material.ENDER_CHEST
                || e.getClickedBlock().getType() == Material.ACACIA_SIGN
                || e.getClickedBlock().getType() == Material.BEACON
                || e.getClickedBlock().getType().toString().contains("_BED")
                || e.getClickedBlock().getType().toString().contains("SIGN")
                || e.getClickedBlock().getType().toString().contains("POTTED_")
                || e.getClickedBlock().getType().toString().contains("FENCE")
                || e.getClickedBlock().getType().toString().contains("DOOR")
            ) {
                if(e.getPlayer().getGameMode() == GameMode.CREATIVE)
                    return;
                e.setCancelled(true);
                return;
            }

        if (!(e.getPlayer().getInventory().getItemInMainHand().getType() != Material.AIR
            && e.getPlayer().getInventory().getItemInMainHand().getItemMeta() != null))
            return;


        var pdc = e.getPlayer().getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer();
        var id = pdc.getOrDefault(SkyBlock.key("id"), PersistentDataType.STRING, "none");
        if (!id.equals("none")) {
            if (e.getAction() == Action.LEFT_CLICK_AIR
                || e.getAction() == Action.LEFT_CLICK_BLOCK) {
                if (ItemDatabase.database.get(id).getData().getType() == ItemSlot.BOW) {
                    AttackFunctions.handleAttack(e.getPlayer());
                }
            }
        }

    }

    @EventHandler(priority = EventPriority.HIGH)
    public void attack(EntityDamageByEntityEvent e) {
        if (e.getCause() == EntityDamageEvent.DamageCause.PROJECTILE
            && ((Projectile) e.getDamager()).getShooter() instanceof Player p) {
            e.setCancelled(true);


            if (!(e.getEntity() instanceof Player)
                && e.getEntity() instanceof LivingEntity le
                ) {
                if (le.isDead() || !le.isValid())
                    return;

                var damage = (int) Math.floor(e.getDamager().getPersistentDataContainer().getOrDefault(
                    SkyBlock.key("damage"),
                    PersistentDataType.DOUBLE,
                    0.0
                ));

                var damageType = DamageType.valueOf(e.getDamager().getPersistentDataContainer().getOrDefault(
                    SkyBlock.key("damage_type"),
                    PersistentDataType.STRING,
                    "PLAYER_NORMAL_MELEE"
                ));

                var dist = (int) p.getLocation().distance(le.getLocation());

                damage = damage / Math.clamp(dist-5, 1, 10000);

                le.setNoDamageTicks(0);
                le.setMaximumNoDamageTicks(0);

                MobUtils.damageMobWithType(
                    le,
                    damage,
                    damageType,
                    p
                );

            }

        }
        if (e.getDamager() instanceof Player p
            && e.getEntity() instanceof LivingEntity le
            && e.getCause() == EntityDamageEvent.DamageCause.ENTITY_ATTACK) {
            if (DataStorage.attackCooldown.getOrDefault(p.getUniqueId(), 0) <= 0) {
                var fero = DataStorage.playerStats.get(p.getUniqueId()).getFerocity() + 100;

                var feroIndex = 0;
                while (fero > 0) {
                    feroIndex += 1;
                    double finalFero = fero;

                    Bukkit.getScheduler().scheduleSyncDelayedTask(SkyBlock.main, () -> {
                        if (le.isDead() || !le.isValid())
                            return;
                        if (Math.random() <= finalFero / 100) {
                            p.playSound(
                                e.getEntity().getLocation(),
                                Sound.ENTITY_ZOMBIE_ATTACK_WOODEN_DOOR,
                                1,
                                1
                            );
                            var calc2 = CalculateDamage.calculateStandard(
                                DataStorage.playerStats.get(p.getUniqueId()),
                                true,
                                true,
                                false
                            );
                            le.setNoDamageTicks(0);
                            le.setMaximumNoDamageTicks(0);
                            MobUtils.damageMobWithType(
                                le,
                                (int) calc2.amount,
                                calc2.type,
                                p
                            );
                            le.setNoDamageTicks(0);
                            le.setMaximumNoDamageTicks(0);

                            DataStorage.currentHealth.put(
                                p.getUniqueId(),
                                DataStorage.currentHealth.get(p.getUniqueId()) +
                                    (DataStorage.playerStats.get(p.getUniqueId()).getLifesteal())
                            );
                        }
                    }, feroIndex * 3L);
                    fero -= 100;
                }

                e.setCancelled(true);

                DataStorage.attackCooldown.put(p.getUniqueId(),
                    (int) Math.ceil(10 - (DataStorage.playerStats.get(p.getUniqueId()).getBonusAttackSpeed() / 20))
                );

                return;
            }

            e.setCancelled(true);
            return;
        }
        if (e.getDamager().getPersistentDataContainer().has(SkyBlock.key("id"))) {
            var id = e.getDamager().getPersistentDataContainer().get(
                SkyBlock.key("id"),
                PersistentDataType.STRING
            );
            var dmg = MobDatabase.get(MobId.valueOf(id)).getMeleeDamage();
            e.setDamage(dmg);
        }
    }

    @EventHandler
    public void playerDropItem(PlayerDropItemEvent e) {
        e.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void playerTakeDamage(EntityDamageEvent e) {
        if (e.getEntity() instanceof LivingEntity &&
            MobUtils.isInvulnerable((LivingEntity) e.getEntity())) {
            e.setCancelled(true);
            return;
        }

        if(e.getCause() == EntityDamageEvent.DamageCause.FALL)
            e.setCancelled(true);
        if (e.getCause() == EntityDamageEvent.DamageCause.WITHER)
            e.setDamage(e.getDamage() * 200);
        if (e.getCause() == EntityDamageEvent.DamageCause.FIRE)
            e.setDamage(e.getDamage() * 5000);
        if (e.getEntity() instanceof Player p) {
            var velocity = e.getEntity().getVelocity();
            var dmg = e.getDamage();
            var rawDmg = dmg;
            var stats = DataStorage.playerStats.getOrDefault(p.getUniqueId(), new StatsObject());
            dmg *= (1 - (stats.getDefense() / (stats.getDefense() + 100)));
            DataStorage.currentHealth.put(p.getUniqueId(),
                DataStorage.currentHealth.getOrDefault(p.getUniqueId(), 10000.0) - dmg
            );
            DataStorage.cold.put(
                p.getUniqueId(),
                DataStorage.cold.getOrDefault(p.getUniqueId(), 0.0)
                    + (rawDmg / 10000)
            );
            e.setDamage(0.0001);
            Bukkit.getScheduler().scheduleSyncDelayedTask(SkyBlock.main, () -> {
                p.setVelocity(velocity);
            }, 0);
        }
        if (e.getEntity() instanceof LivingEntity le
            && !(e.getEntity() instanceof Player)
            && e.getDamage() > 5) {

            le.setNoDamageTicks(0);
            le.setMaximumNoDamageTicks(0);


            var vel = le.getVelocity().multiply(0.5);
            if(e.getCause() == EntityDamageEvent.DamageCause.PROJECTILE)
                Bukkit.getScheduler().scheduleSyncDelayedTask(SkyBlock.main, () -> le.setVelocity(vel));
        }
    }

    @EventHandler
    public void place(BlockPlaceEvent e) {
        if (e.getPlayer().getGameMode() != GameMode.CREATIVE)
            e.setCancelled(true);
    }

    @EventHandler
    public void place(BlockBreakEvent e) {
        if (e.getPlayer().getGameMode() != GameMode.CREATIVE)
            e.setCancelled(true);
    }


    @EventHandler
    public void drop(EntityDropItemEvent e) {
        if (e.getEntity() instanceof Player p) {
            if (p.getInventory().getHeldItemSlot() == 8)
                e.setCancelled(true);
        }
    }

    @EventHandler
    public void craft(PrepareItemCraftEvent e) {
        e.getRecipe().getResult().setType(Material.AIR);
    }

    @EventHandler
    public void onSpawn(EntitySpawnEvent e) {
        Bukkit.getScheduler().scheduleSyncDelayedTask(SkyBlock.main, () -> MobUtils.tryRename(e.getEntity()), 5);
    }
}
