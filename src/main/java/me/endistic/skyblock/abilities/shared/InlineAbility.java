package me.endistic.skyblock.abilities.shared;

import me.endistic.skyblock.abilities.Ability;
import me.endistic.skyblock.abilities.player.AbilityTrigger;
import me.endistic.skyblock.abilities.player.PlayerAbilityDescription;
import org.bukkit.entity.LivingEntity;

import java.util.function.Consumer;

public class InlineAbility implements Ability {
    public double cost = 0;
    public Consumer<LivingEntity> runnable;
    public String name;
    public String description;
    public AbilityTrigger trigger;
    public InlineAbility(
        double cost,
        Consumer<LivingEntity> runnable,
        AbilityTrigger trigger,
        String name,
        String description

    ) {
        this.runnable = runnable;
        this.cost = cost;
        this.name = name;
        this.description = description;
        this.trigger = trigger;
    }

    @Override
    public int getDelay() {
        return 0;
    }

    @Override
    public void castSpell(LivingEntity e) {
        runnable.accept(e);
    }

    @Override
    public PlayerAbilityDescription getDescription() {
        return new PlayerAbilityDescription()
            .setName(this.name)
            .setDescription(this.description)
            .setTrigger(this.trigger)
            .setManaCost(this.cost);
    }
}
