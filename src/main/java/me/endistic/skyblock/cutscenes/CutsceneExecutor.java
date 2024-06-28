package me.endistic.skyblock.cutscenes;

import me.endistic.skyblock.cutscenes.actions.CutsceneAction;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;


public class CutsceneExecutor {
    public static HashMap<UUID, Boolean> inCutscene = new HashMap<>();
    public static HashMap<UUID, Location> savedLocation = new HashMap<>();
    public static HashMap<UUID, CutsceneExecutor> currentExecutor = new HashMap<>();
    public int index = 0;
    public List<CutsceneAction> actions;
    public Player p;

    public CutsceneExecutor(List<CutsceneAction> actions, Player p) {
        this.actions = actions;
        currentExecutor.put(p.getUniqueId(), this);
        savedLocation.put(p.getUniqueId(), p.getLocation());
        this.p = p;
    }

    public void nextStep() {
        inCutscene.put(p.getUniqueId(), true);
        if(index > actions.size()-1) {
            inCutscene.put(p.getUniqueId(), false);
            p.teleport(savedLocation.get(p.getUniqueId()));
            currentExecutor.remove(p.getUniqueId());
            return;
        }
        actions.get(index++).execute(p, this);
    }


}
