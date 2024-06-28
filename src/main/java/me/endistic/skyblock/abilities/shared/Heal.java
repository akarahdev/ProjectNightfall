package me.endistic.skyblock.abilities.shared;

import me.endistic.skyblock.SkyBlock;
import me.endistic.skyblock.data.DataStorage;
import me.endistic.skyblock.abilities.Ability;
import me.endistic.skyblock.abilities.player.AbilityTrigger;
import me.endistic.skyblock.abilities.player.PlayerAbilityDescription;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Particle;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class Heal implements Ability {

    public double amount = 0;
    public double times = 0;
    public double cost = 0;
    public long delay = 20;
    public Heal(double amount, double times, double cost) {
        this.amount = amount/times;
        this.times = times;
        this.cost = cost;
    }
    public Heal(double amount, double times, double cost, long delay) {
        this.amount = amount/times;
        this.times = times;
        this.cost = cost;
        this.delay = delay;
    }
    @Override
    public int getDelay() {
        return 200;
    }

    @Override
    public void castSpell(LivingEntity e) {
        final int[] i = {0};
        new BukkitRunnable() {
            @Override
            public void run() {
                i[0]++;
                if(i[0] > times)
                    cancel();

                if(e instanceof Player p) {
                    DataStorage.currentHealth.put(
                        p.getUniqueId(),
                        DataStorage.currentHealth.get(p.getUniqueId())+amount
                    );
                } else {
                    e.setHealth(e.getHealth() + amount);
                }


                for(var p : Bukkit.getOnlinePlayers()) {
                    p.spawnParticle(
                        Particle.HEART,
                        e.getLocation(),
                        10,
                        0.75, 2, 0.75,
                        0
                    );
                }
            }
        }.runTaskTimer(SkyBlock.main, 0, (long) delay);
    }

    @Override
    public PlayerAbilityDescription getDescription() {
        return new PlayerAbilityDescription()
            .setName("Heal")
            .setDescription("Heal "
                + ChatColor.GREEN + amount*times + ChatColor.GREEN + " HP over "
                + ChatColor.GREEN + times + ChatColor.GRAY + " seconds.")
            .setTrigger(AbilityTrigger.RIGHT_CLICK)
            .setManaCost(this.cost);
    }
}
