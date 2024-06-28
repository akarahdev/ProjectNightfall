package me.endistic.skyblock.stats;

import me.endistic.skyblock.mobs.DamageType;

public class CalculateDamage {
    public static DamageResult calculateStandard(StatsObject stats, boolean canCrit, boolean melee, boolean ranged) {
        var result = new DamageResult();
        result.type = DamageType.PLAYER_NORMAL_MELEE;
        result.amount = (
            stats.damage * ((stats.getStrength()/100)+1)
        );
        if(canCrit && Math.random() < stats.getCriticalChance()/100) {
            result.amount *= (stats.getCriticalDamage()/100)+1;
            result.type = DamageType.PLAYER_CRITICAL_MELEE;
        }
        if(melee)
            result.amount *= (stats.getMeleeDamagePct()/100)+1;

        if(ranged) {
            result.amount *= (stats.getRangedDamagePct()/100)+1;
            if(result.type == DamageType.PLAYER_CRITICAL_MELEE)
                result.type = DamageType.PLAYER_CRITICAL_RANGED;
        }

        return result;
    }

    public static DamageResult calculateMagic(StatsObject stats) {
        var result = new DamageResult();
        result.type = DamageType.PLAYER_MAGIC;
        result.amount = (
            stats.getBaseAbilityDamage() + (stats.getIntelligence() * stats.getAbilityDamageScaling())
        );
        return result;
    }
}
