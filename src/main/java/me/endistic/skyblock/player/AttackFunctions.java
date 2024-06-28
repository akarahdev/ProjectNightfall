package me.endistic.skyblock.player;

import me.endistic.skyblock.SkyBlock;
import me.endistic.skyblock.data.DataStorage;
import me.endistic.skyblock.items.ItemDatabase;
import me.endistic.skyblock.items.ItemSlot;
import me.endistic.skyblock.stats.CalculateDamage;
import org.bukkit.Bukkit;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;

import java.util.Objects;

public class AttackFunctions {
    public static void playerHandleBowAttack(Player p) {
        var eyeLoc = p.getEyeLocation().clone();
        eyeLoc.add(p.getLocation().getDirection().normalize().multiply(0.25));

        var stats = DataStorage.playerStats.get(p.getUniqueId());
        var waves = ((int) stats.getArrowCount() / 3) + 1;
        var finalWaveArrow = Math.ceil(stats.getArrowCount() % 3);

        if(stats.getArrowCount() <= 0
        || stats.getArrowSpeed() <= -100)
            return;

        var basePitch = eyeLoc.getYaw();

        for (var i = 0; i < waves; i++) {
            int finalI = i;
            Bukkit.getScheduler().scheduleSyncDelayedTask(SkyBlock.main, () -> {
                var pitches = new int[]{-3, 0, 3};
                if (finalI == waves - 1) {
                    switch ((int) finalWaveArrow) {
                        case 1 -> pitches = new int[]{0};
                        case 2 -> pitches = new int[]{-1, 1};
                        case 3 -> {}
                        case 0 -> pitches = new int[]{};
                    }
                }
                for (var pitch : pitches) {
                    eyeLoc.setYaw(basePitch + pitch);
                    var dam = CalculateDamage.calculateStandard(
                        DataStorage.playerStats.get(p.getUniqueId()),
                        true,
                        false,
                        true
                    );

                    var arrow = (Arrow) eyeLoc.getWorld().spawnEntity(
                        eyeLoc,
                        EntityType.ARROW
                    );

                    arrow.getPersistentDataContainer().set(
                        SkyBlock.key("damage"),
                        PersistentDataType.DOUBLE,
                        dam.amount
                    );
                    arrow.getPersistentDataContainer().set(
                        SkyBlock.key("damage_type"),
                        PersistentDataType.STRING,
                        dam.type.toString()
                    );
                    arrow.setVelocity(
                        eyeLoc.getDirection().normalize().multiply(
                            2 + (DataStorage.playerStats.get(p.getUniqueId())
                                .getArrowSpeed() / 100)
                        )
                    );
                    arrow.setShooter(p);
                    arrow.setRotation(
                        eyeLoc.getPitch(),
                        eyeLoc.getYaw()
                    );
                    arrow.setPierceLevel(126);
                }
            }, (i * 3L));

        }

    }

    public static void handleAttack(Player p) {
        if (DataStorage.attackCooldown.get(p.getUniqueId()) > 0)
            return;

        DataStorage.attackCooldown.put(p.getUniqueId(),
            (int) Math.ceil(10 - (DataStorage.playerStats.get(p.getUniqueId()).getBonusAttackSpeed() / 20)));

        var item = p.getInventory().getItemInMainHand();
        var pdc = Objects.requireNonNull(item.getItemMeta()).getPersistentDataContainer();
        var id = pdc.get(SkyBlock.key("id"), PersistentDataType.STRING);

        if (ItemDatabase.database.get(id).getData().getType() == ItemSlot.BOW) {
            playerHandleBowAttack(p);
        }
    }
}
