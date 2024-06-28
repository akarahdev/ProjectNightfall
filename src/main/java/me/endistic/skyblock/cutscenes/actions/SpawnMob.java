package me.endistic.skyblock.cutscenes.actions;

import me.endistic.skyblock.cutscenes.CutsceneExecutor;
import me.endistic.skyblock.mobs.CustomMob;
import me.endistic.skyblock.mobs.MobDatabase;
import me.endistic.skyblock.mobs.MobTrigger;
import me.endistic.skyblock.mobs.SpawningMetadata;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class SpawnMob implements CutsceneAction {
    public Location loc;
    public CustomMob mob;

    public SpawnMob(Location loc, CustomMob mob) {
        this.loc = loc;
        this.mob = mob;
    }

    @Override
    public void execute(Player p, CutsceneExecutor executor) {
        var e = MobDatabase.spawnCustomMob(
            mob.getId(),
            loc,
            new SpawningMetadata()
                .setSpawnId(-1)
                .setOwner(p)
                .setSummonedBy(null)
        );
        MobTrigger.runOnDeath(e, executor::nextStep);
    }
}
