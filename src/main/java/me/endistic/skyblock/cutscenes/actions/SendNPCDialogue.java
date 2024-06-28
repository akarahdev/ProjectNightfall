package me.endistic.skyblock.cutscenes.actions;

import me.endistic.skyblock.cutscenes.CutsceneExecutor;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class SendNPCDialogue implements CutsceneAction {
    public String npc;
    public String dialogue;
    public SendNPCDialogue(String npc, String dialogue) {
        this.npc = npc;
        this.dialogue = dialogue;
    }

    @Override
    public void execute(Player p, CutsceneExecutor executor) {
        p.sendMessage(
            ChatColor.YELLOW + "[NPC] " + npc
            + ChatColor.WHITE + ": " + dialogue
        );
        p.playSound(p, Sound.ENTITY_VILLAGER_YES, 1, 1);
        executor.nextStep();
    }
}
