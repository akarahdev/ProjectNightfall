package me.endistic.skyblock.mobs;

import me.endistic.skyblock.SkyBlock;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.event.Listener;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.function.Consumer;

/**
 * MobTrigger allows you to register certain events to happen, and to only
 * apply to certain mobs.
 */
public class MobTrigger implements Listener {
    public static HashMap<String, Runnable> deathTriggerDb = new HashMap<>();
    public static int globalTrigger = 0;

    /**
     * Triggers an event when an entity dies.
     *
     * @param le      The entity to track
     * @param trigger The code to run
     */
    public static void runOnDeath(Entity le, Runnable trigger) {
        var id = String.valueOf(globalTrigger++);
        deathTriggerDb.put(id, trigger);
        le.getPersistentDataContainer().set(SkyBlock.key("trigger_on_death"), PersistentDataType.STRING, id);
    }

    /**
     * Runs a loop every tick.
     *
     * @param trigger The loop to run.
     */
    public static void runOnLoop(Consumer<BukkitRunnable> trigger) {
        new BukkitRunnable() {
            @Override
            public void run() {
                trigger.accept(this);
            }
        }.runTaskTimer(SkyBlock.main, 0, 1);
    }

    /**
     * Handles the Death Trigger.
     */
    public static void runDeathTrigger(Entity e) {
        var pdc = e.getPersistentDataContainer();

        if (pdc.has(SkyBlock.key("trigger_on_death"))) {
            var triggerI = pdc.get(SkyBlock.key("trigger_on_death"), PersistentDataType.STRING);
            Bukkit.broadcastMessage(ChatColor.DARK_GRAY + "[DEBUG] >> " + e.getUniqueId() + " has death trigger id " + triggerI);
            deathTriggerDb.get(triggerI).run();
            deathTriggerDb.remove(triggerI);
        }
    }
}
