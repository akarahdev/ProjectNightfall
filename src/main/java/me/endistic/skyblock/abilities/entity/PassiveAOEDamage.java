package me.endistic.skyblock.abilities.entity;

import me.endistic.skyblock.abilities.Ability;
import me.endistic.skyblock.abilities.player.PlayerAbilityDescription;
import me.endistic.skyblock.mobs.MobUtils;
import org.bukkit.entity.LivingEntity;

public class PassiveAOEDamage implements Ability {

    public double damage;
    public double radius;

    public PassiveAOEDamage(double damage, double radius) {
        super();
        this.damage = damage;
        this.radius = Math.pow(radius, 2);
    }

    @Override
    public int getDelay() {
        return 20;
    }

    @Override
    public void castSpell(LivingEntity e) {
        var center = e.getLocation();
        var owner = MobUtils.getOwner(e);

        if (owner.getLocation().distanceSquared(center) < this.radius) {
            owner.damage(damage);
        }

    }

    @Override
    public PlayerAbilityDescription getDescription() {
        return null;
    }
}
