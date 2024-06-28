package me.endistic.skyblock.mobs;

import me.endistic.skyblock.mobs.entities.*;

public enum MobId {
    BALLISTIC_DUMMY(new BallisticDummy()),
    EMPYREAL_ASPECT(new EmpyrealAspect()),
    ENDBLIGHT_AVATAR(new EndblightAvatar()),
    ENDBLIGHT_SUBSOUL(new EndblightSubsoul()),
    GODLESS_PRESERVER(new GodlessPreserver()),
    LORD_CALAHANN(new LordCalahann()),
    OLD_GUARD_REVENANT(new OldGuardRevenant()),
    REAVER_COURTIER(new ReaverCourtier()),
    THE_EXIFLAME(new TheExiflame()),
    SILVERSOUL(new Silversoul()),
    THE_DAMNED(new TheDamned()),
    THE_DHEIREADH(new TheDheireadh()),
    THE_LOST(new TheLost()),
    WRAITHWOOD(new Wraithwood());

    public final CustomMob mob;

    MobId(CustomMob mob) {
        this.mob = mob;
    }
}
