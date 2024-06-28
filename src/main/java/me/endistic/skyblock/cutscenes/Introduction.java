package me.endistic.skyblock.cutscenes;

import me.endistic.skyblock.SkyBlock;
import me.endistic.skyblock.cutscenes.actions.*;
import me.endistic.skyblock.items.ItemDatabase;
import me.endistic.skyblock.mobs.entities.BallisticDummy;
import org.bukkit.entity.Player;

import java.util.List;

public class Introduction {
    public static CutsceneExecutor executor(Player p) {
        return new CutsceneExecutor(
            List.of(
                new SendNPCDialogue(
                    "???",
                    "It was called Teros."
                ),
                new Repeat(
                    60,
                    new Teleport(SkyBlock.location(5, 205, 24, 45, 45)),
                    new Sleep(1)
                ),
                new SendNPCDialogue(
                    "???",
                    "That was our home. Our elysium."
                ),
                new Repeat(
                    60,
                    new Teleport(SkyBlock.location(-32, 75, -91, 0, 0)),
                    new Sleep(1)
                ),
                new SendNPCDialogue(
                    "???",
                    "It was like no other built before. We lived in towers of silver and diamond, " +
                        "palaces of crystal and gold. We flew the skies and crossed the oceans. And we were not alone."
                ),
                new Repeat(
                    120,
                    new Teleport(SkyBlock.location(62, 143, 43, 45, 22)),
                    new Sleep(1)
                ),
                new SendNPCDialogue(
                    "???",
                    "..."
                ),
                new Teleport(SkyBlock.location(88, 26, 63, -180, 0)),
                new Sleep(100),
                new SendNPCDialogue(
                    "???",
                    "You are a Silent Missionary, and you have dedicated your life to crusade for the Order. " +
                        "Take your weapon and prove your strength."
                ),
                new GiveCustomItem(ItemDatabase.citem("null_blade")),
                new Sleep(60),
                new SendNPCDialogue(
                    "Objective",
                    "Kill your Ballistic Dummy."
                ),
                new Sleep(40),
                new SpawnMob(
                    SkyBlock.location(91, 25, 56, 0, 0),
                    new BallisticDummy()
                ),
                new SendNPCDialogue(
                    "???",
                    "Your mind has been sharpened by the Order’s finest catalysts. " +
                        "You will find within the knowledge of ages past."
                ),
                new SendNPCDialogue(
                    "Objective",
                    "Craft a Conqueror Longsword. Check the Recipe Book in the Crafting Table in your SkyBlock " +
                        "Menu for a reference."
                ),
                new GiveCustomItem(ItemDatabase.citem("conqueror_fragment")),
                new WaitForCustomItem(
                    ItemDatabase.citem("conqueror_longsword")
                ),
                new SendNPCDialogue(
                    "???",
                    "Armor of starforged plate, to absorb external force and enhance your own capabilities. " +
                        "Yours will be a quiet strength."
                ),
                new GiveCustomItem(ItemDatabase.citem("silence_helmet")),
                new GiveCustomItem(ItemDatabase.citem("silence_chestplate")),
                new GiveCustomItem(ItemDatabase.citem("silence_leggings")),
                new GiveCustomItem(ItemDatabase.citem("silence_boots")),
                new Sleep(60),
                new SendNPCDialogue(
                    "???",
                    "You have been trained to master the ways of change. " +
                        "You will evolve and adapt to meet the dangers to come."
                ),
                new Sleep(60),
                new Teleport(SkyBlock.location(-2, 71, -62, 180, 0))
            ), p);
    }
}
