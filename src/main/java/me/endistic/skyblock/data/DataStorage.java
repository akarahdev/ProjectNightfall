package me.endistic.skyblock.data;

import me.endistic.skyblock.items.ItemModifiers;
import me.endistic.skyblock.player.Rank;
import me.endistic.skyblock.quests.SlayerQuest;
import me.endistic.skyblock.stats.StatsObject;
import me.endistic.skyblock.statuseffects.StatusEffectData;
import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class DataStorage {
    /**
     * Holds the StatsObject that determines each players' total stats.
     */
    public static HashMap<UUID, StatsObject> playerStats = new HashMap<>();
    /**
     * Holds each players' IDs of their power stones.
     */
    public static HashMap<UUID, String> powerStones = new HashMap<>();
    /**
     * Holds the attack cooldown (in ticks) of players.
     */
    public static HashMap<UUID, Integer> attackCooldown = new HashMap<>();
    public static HashMap<UUID, Double> currentHealth = new HashMap<>();
    public static HashMap<UUID, Double> currentMana = new HashMap<>();
    public static HashMap<UUID, List<String>> accessoryBag = new HashMap<>();
    public static HashMap<UUID, HashMap<Integer, String>> enderChestItems = new HashMap<>();
    public static HashMap<UUID, HashMap<Integer, Integer>> enderChestCounts = new HashMap<>();
    public static HashMap<UUID, HashMap<Integer, ItemModifiers>> enderChestMods = new HashMap<>();
    public static HashMap<UUID, Integer> openedEnderchestPage = new HashMap<>();
    public static HashMap<UUID, Integer> magicalPower = new HashMap<>();
    public static HashMap<UUID, Location> spatialMarks = new HashMap<>();
    public static HashMap<UUID, Double> cold = new HashMap<>();
    public static HashMap<UUID, Rank> ranks = new HashMap<>();
    public static HashMap<UUID, Integer> combatXp = new HashMap<>();
    public static HashMap<UUID, SlayerQuest> quest = new HashMap<>();
    public static HashMap<UUID, List<StatusEffectData>> statusEffects = new HashMap<>();
}
