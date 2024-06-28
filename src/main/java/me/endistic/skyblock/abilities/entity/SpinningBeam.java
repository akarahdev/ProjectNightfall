package me.endistic.skyblock.abilities.entity;

import me.endistic.skyblock.abilities.Ability;
import me.endistic.skyblock.abilities.player.PlayerAbilityDescription;
import me.endistic.skyblock.mobs.MobUtils;
import me.endistic.skyblock.utils.PlayerHitbox;
import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.LivingEntity;

public class SpinningBeam implements Ability {
    public int angle = 0;
    public int damage = 5000;
    @Override
    public int getDelay() {
        return 3;
    }

    public SpinningBeam() {

    }

    public SpinningBeam(int angle) {
        this.angle = angle;
    }

    public SpinningBeam(int angle, int damage) {
        this.angle = angle;
        this.damage = damage;
    }

    @Override
    public void castSpell(LivingEntity e) {
        var loc = e.getLocation();
        loc.setPitch(0);
        loc.setYaw(this.angle);
        this.angle += 5;

        var dir = loc.getDirection().normalize().multiply(0.5);

        for(var i = 0; i<20; i++) {
            loc.add(dir);
            for(var p : Bukkit.getOnlinePlayers()) {
                p.spawnParticle(
                    Particle.BUBBLE,
                    loc,
                    1,
                    0, 0, 0,
                    0
                );

                p.playSound(
                    loc,
                    Sound.BLOCK_END_PORTAL_FRAME_FILL,
                    0.1f,
                    0.6f
                );
            }
            var owner = MobUtils.getOwner(e);
            if(owner != null && owner.isOnline()
                && PlayerHitbox.isInHitbox(loc, owner.getLocation())) {
                owner.damage(this.damage);
            }
        }
        for(var p : Bukkit.getOnlinePlayers()) {

        }
    }

    @Override
    public PlayerAbilityDescription getDescription() {
        return null;
    }
}
