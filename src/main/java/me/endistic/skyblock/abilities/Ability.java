package me.endistic.skyblock.abilities;

import me.endistic.skyblock.abilities.player.PlayerAbilityDescription;
import org.bukkit.entity.LivingEntity;

public interface Ability {
    int getDelay();
    void castSpell(LivingEntity e);
    PlayerAbilityDescription getDescription();
}
