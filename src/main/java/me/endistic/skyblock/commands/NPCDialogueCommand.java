package me.endistic.skyblock.commands;

import me.endistic.skyblock.cutscenes.Introduction;
import me.endistic.skyblock.inventories.AccessoryBagInventory;
import me.endistic.skyblock.inventories.EnderChestInventory;
import me.endistic.skyblock.inventories.PowerStonesInventory;
import me.endistic.skyblock.inventories.UpgradeInventory;
import me.endistic.skyblock.items.ItemDatabase;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

public class NPCDialogueCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player p) {
            var npcId = strings[0];
            var textId = strings[1];
            switch (npcId) {
                case "THE_HANDLER" -> {
                    switch(textId) {
                        case "1" -> {
                            PowerStonesInventory.openFor(p);
                        }
                        case "2" -> {
                            AccessoryBagInventory.openFor(p);
                        }
                    }
                }
                case "ECHEST" -> {
                    EnderChestInventory.openFor(p, 1);
                }
                case "INTRODUCTION" -> {
                    if(Objects.equals(textId, "GO"))
                        Introduction.executor(p).nextStep();
                }
                case "UPGRADE" -> {
                    UpgradeInventory.openFor(p);
                }
            }
        }
        return false;
    }
}
