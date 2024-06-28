package me.endistic.skyblock.quests;

import me.endistic.skyblock.mobs.entities.EndblightAvatar;
import me.endistic.skyblock.mobs.entities.TheDheireadh;
import me.endistic.skyblock.mobs.entities.LordCalahann;
import me.endistic.skyblock.mobs.entities.TheExiflame;

public class SlayerQuestFactory {
    public static SlayerQuest corruptedTreeQuest() {
        var q = new SlayerQuest();
        q.xpRequired = 100;
        q.xpGot = 0;
        q.mobToSpawn = new TheDheireadh();
        return q;
    }

    public static SlayerQuest reaverKingQuest() {
        var q = new SlayerQuest();
        q.xpRequired = 200;
        q.xpGot = 0;
        q.mobToSpawn = new LordCalahann();
        return q;
    }

    public static SlayerQuest wardenQuest() {
        var q = new SlayerQuest();
        q.xpRequired = 400;
        q.xpGot = 0;
        q.mobToSpawn = new EndblightAvatar();
        return q;
    }

    public static SlayerQuest blazeQuest() {
        var q = new SlayerQuest();
        q.xpRequired = 500;
        q.xpGot = 0;
        q.mobToSpawn = new TheExiflame();
        return q;
    }
}
