package me.endistic.skyblock.player;

import me.endistic.skyblock.data.DataStorage;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.scoreboard.Criteria;
import org.bukkit.scoreboard.DisplaySlot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class DisplayChatAndScoreboard implements Listener {
    public static List<String> tablist = new ArrayList<String>();

    public static int[] xpReqs = new int[]{
        50, 175, 375, 675, 1175, 1925, 2925, 4425, 6425, 9925, 14925, 22425, 32425, 47425, 67425, 97425, 147425,
        222425, 322425, 522425, 822425, 1222425, 1722425, 2322425, 3022425, 3822425, 4722425, 5722425, 6822425,
        8022425, 9322425, 10722425, 12222425, 13822425, 15522425, 17322425, 19222425, 21222425, 23322425, 25522425,
        27822425, 30222425, 32722425, 35072425, 38072425, 40972425, 44072425, 47472425, 51172425, 55172425, 59472425,
        64072425, 68972425, 74172425, 79672425, 85472425, 91572425, 97972425, 104672425, 111672425
    };

    public static int getLevel(Player p) {
        var xp = DataStorage.combatXp.getOrDefault(p.getUniqueId(), 0);

        var lvl = 1;
        for (var xps : xpReqs) {
            if (xp > xps)
                lvl++;
            else
                return lvl;
        }
        return lvl;
    }

    public static String getPlayerTag(Player p) {
        var builder = new StringBuilder();
        var level = getLevel(p);
        builder.append(ChatColor.DARK_GRAY);
        builder.append("[");
        builder.append(ChatColor.GRAY);
        if (level >= 5)
            builder.append(ChatColor.BLUE);
        if (level >= 10)
            builder.append(ChatColor.DARK_AQUA);
        if (level >= 15)
            builder.append(ChatColor.DARK_GREEN);
        if (level >= 20)
            builder.append(ChatColor.GREEN);
        if (level >= 25)
            builder.append(ChatColor.YELLOW);
        if (level >= 30)
            builder.append(ChatColor.GOLD);
        if (level >= 35)
            builder.append(ChatColor.RED);
        if (level >= 40)
            builder.append(ChatColor.DARK_RED);
        if (level >= 45)
            builder.append(ChatColor.DARK_PURPLE);
        if (level >= 50)
            builder.append(ChatColor.LIGHT_PURPLE);
        if (level >= 55)
            builder.append(ChatColor.WHITE);
        if (level >= 60)
            builder.append(ChatColor.AQUA);

        builder.append(Integer.toString(level).replace(".0", ""));
        builder.append(ChatColor.DARK_GRAY);
        builder.append("] ");

        var rank = DataStorage.ranks.getOrDefault(p.getUniqueId(), Rank.DEFAULT);
        builder.append(rank.color);
        builder.append(rank.tag);
        return builder.toString();
    }

    public static void updateTablist() {
        var list = new ArrayList<String>();
        list.add("");
        list.add(ChatColor.AQUA + "" + ChatColor.BOLD + "PROJECT: NIGHTFALL");
        list.add("");

        for (var p : Bukkit.getOnlinePlayers()) {
            var stats = DataStorage.playerStats.get(p.getUniqueId());
            var hp = DataStorage.currentHealth.get(p.getUniqueId());
            var tag = getPlayerTag(p);
            list.add(tag + " " + p.getName());
            list.add(ChatColor.DARK_GRAY + "➥ " + ChatColor.GRAY + "Health: " + ChatColor.RED +
                Math.floor(hp) + ChatColor.DARK_RED + "/" + Math.floor(stats.getMaxHealth()));
            list.add(ChatColor.DARK_GRAY + "➥ " + ChatColor.GRAY + "XP: " + ChatColor.YELLOW +
                DataStorage.combatXp.get(p.getUniqueId()));
            list.add("");
        }
        list.add("");
        list.add(ChatColor.GRAY + "Players: " + ChatColor.AQUA + Bukkit.getOnlinePlayers().size());
        list.add(ChatColor.GRAY + "Memory: " + ChatColor.AQUA +
            (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1000000000.0 + "GB");
        list.add(" ".repeat(50));
        for (var i = 0; i < 100; i++)
            list.add("");
        DisplayChatAndScoreboard.tablist = list;
    }

    public static void givePlayerScoreboard(Player p) {
        var scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        scoreboard.registerNewObjective("h", Criteria.LEVEL, "health");


        var team = scoreboard.registerNewTeam("t");
        team.addEntry(p.getDisplayName());
        team.setPrefix(getPlayerTag(p));

        var healthObjective = scoreboard.getObjective("h");
        healthObjective.setDisplaySlot(DisplaySlot.BELOW_NAME);

        var scoreboardObjective = scoreboard.registerNewObjective("s", Criteria.DUMMY, "s1");
        scoreboardObjective.setDisplaySlot(DisplaySlot.SIDEBAR);
        scoreboardObjective.setDisplayName(ChatColor.YELLOW + ChatColor.BOLD.toString() + "PROJECT: NIGHTFALL");

        var scoreboardLore = new ArrayList<String>();
        scoreboardLore.add(ChatColor.GRAY +
            new SimpleDateFormat("MM/dd/yy").format(new Date()));

        scoreboardLore.add(" ");
//        scoreboardLore.add(ChatColor.WHITE + "Essence: " + ChatColor.DARK_PURPLE + "0");
//        scoreboardLore.add(ChatColor.WHITE + "Cold: " + ChatColor.AQUA + "-" +
//            Double.toString(Math.floor(DataStorage.cold.get(p.getUniqueId()))).replace(".0", ""));
        scoreboardLore.add(ChatColor.WHITE + "XP: " + ChatColor.YELLOW +
            DataStorage.combatXp.getOrDefault(p.getUniqueId(), 0));

        scoreboardLore.add("");
        scoreboardLore.add(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Active Effects");

        var effects = DataStorage.statusEffects.getOrDefault(p.getUniqueId(), new ArrayList<>());
        if (effects.isEmpty())
            scoreboardLore.add(ChatColor.RED + "None");
        else
            for (var e : effects) {
                var render = new SimpleDateFormat("mm:ss").format(new Date(e.duration() * 1000 / 20));
                scoreboardLore.add(e.id().color() + e.id().symbol() + " " + e.id().displayName() + " " + e.amplifier() + " " + render);
            }

        if (DataStorage.quest.containsKey(p.getUniqueId())) {
            scoreboardLore.add("");
            var quest = DataStorage.quest.get(p.getUniqueId());
            scoreboardLore.add(ChatColor.DARK_PURPLE + quest.mobToSpawn.getName());
            scoreboardLore.add(
                ChatColor.YELLOW + "" + quest.xpGot + ChatColor.DARK_GRAY + "/" +
                    ChatColor.GREEN + quest.xpRequired + ChatColor.YELLOW + " XP");
        }
        scoreboardLore.add("   ");
        scoreboardLore.add(ChatColor.YELLOW + "127.0.0.1");

        var idx = scoreboardLore.size();
        for (var s : scoreboardLore) {
            idx--;
            scoreboardObjective.getScore(s).setScore(idx);
        }

        p.setScoreboard(scoreboard);
        p.setPlayerListHeader(
            String.join("\n", tablist)
        );
    }

    @EventHandler
    public void chat(AsyncPlayerChatEvent e) {
        e.setCancelled(true);
        var tag = getPlayerTag(e.getPlayer());
        Bukkit.broadcastMessage(tag + e.getPlayer().getName() + ChatColor.WHITE + ": " + e.getMessage());
    }
}
