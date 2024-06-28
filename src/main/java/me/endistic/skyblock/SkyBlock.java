package me.endistic.skyblock;

import me.endistic.skyblock.commands.*;
import me.endistic.skyblock.inventories.*;
import me.endistic.skyblock.items.ItemDatabase;
import me.endistic.skyblock.mobs.*;
import me.endistic.skyblock.player.DisplayChatAndScoreboard;
import me.endistic.skyblock.player.PlayerEvents;
import me.endistic.skyblock.player.PlayerLoop;
import me.endistic.skyblock.player.data.PlayerProfileNBT;
import me.endistic.skyblock.stats.StatLoop;
import net.querz.nbt.io.NBTDeserializer;
import net.querz.nbt.io.NBTUtil;
import net.querz.nbt.tag.CompoundTag;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public final class SkyBlock extends JavaPlugin {
    public static SkyBlock main;
    public static File profileFolder;

    @Override
    public void onEnable() {

        if (!this.getDataFolder().exists())
            this.getDataFolder().mkdirs();

        SkyBlock.profileFolder = new File(this.getDataFolder().getPath() + "/profiles");

        if (!SkyBlock.profileFolder.exists())
            SkyBlock.profileFolder.mkdirs();

        System.out.println("Hello world!");

        SkyBlock.main = this;

        Bukkit.getScheduler().scheduleSyncRepeatingTask(SkyBlock.main, StatLoop::tickStats, 5, 5);
        Bukkit.getScheduler().scheduleSyncRepeatingTask(SkyBlock.main, PlayerLoop::tickLoop, 1, 1);
        Bukkit.getScheduler().scheduleSyncRepeatingTask(SkyBlock.main, MobLoop::tickLoop, 10, 10);
        Bukkit.getScheduler().scheduleSyncRepeatingTask(SkyBlock.main, MobSpawnLoop::tickLoop, 40, 200);
        Bukkit.getScheduler().scheduleSyncRepeatingTask(SkyBlock.main, () -> {
            DisplayChatAndScoreboard.updateTablist();
            for (var p : Bukkit.getOnlinePlayers())
                DisplayChatAndScoreboard.givePlayerScoreboard(p);
        }, 0, 10);


        Bukkit.getServer().getPluginManager().registerEvents(new PlayerEvents(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new MobEvents(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new MobTrigger(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new DisplayChatAndScoreboard(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new PowerStonesInventory(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new AccessoryBagInventory(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new CraftingInventory(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new RecipesInventory(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new ViewRecipeInventory(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new MenuInventory(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new FastTravelInventory(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new BestiaryMenu(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new MobInfoInventory(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new UpgradeInventory(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new EnderChestInventory(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new EnderChestPageInventory(), this);

        Bukkit.getServer().getPluginManager().registerEvents(new ProfileViewerInventory(), this);


        // Register commands
        this.getCommand("sbi").setExecutor(new ItemCommand());
        this.getCommand("sbil").setExecutor(new ItemListCommand());
        this.getCommand("spawnmob").setExecutor(new SummonCommand());
        this.getCommand("sps").setExecutor(new PowerStoneCommand());
        this.getCommand("npcdialogue").setExecutor(new NPCDialogueCommand());
        this.getCommand("reload").setExecutor(new ReloadCommand());
        this.getCommand("setrank").setExecutor(new SetRankCommand());
        this.getCommand("setxp").setExecutor(new SetXPCommand());
        this.getCommand("viewprofile").setExecutor(new PVCommand());
        this.getCommand("se").setExecutor(new EffectCommand());

        this.getCommand("kick").setExecutor(new KickCommand());

        // Initialize databases
        ItemDatabase.init();
        MobDatabase.init();


        Bukkit.getScheduler().scheduleSyncDelayedTask(SkyBlock.main, () -> {
            for (var e : Bukkit.getWorld("world").getLivingEntities()) {
                e.getLocation().getChunk().load(true);
                if (e.getPersistentDataContainer().has(SkyBlock.key("is_c_mob"))) {
                    e.setHealth(1);
                    e.damage(1000);
                    e.remove();
                }
                if (!(e instanceof Player))
                    e.remove();
            }

            for (var p : Bukkit.getOnlinePlayers()) {
                var path = Path.of(SkyBlock.profileFolder.getPath() + "/" + p.getUniqueId() + ".nbt");
                tryApplyProfileAfterReload(p, path);
            }
            Bukkit.getServer().clearRecipes();
        }, 20);

    }

    private void tryApplyProfileAfterReload(Player p, Path path) {
        try {
            if (!Files.exists(path)) {
                try {
                    Files.createFile(path);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            byte[] bytes = null;
            try {
                bytes = Files.readAllBytes(path);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            CompoundTag nbt = null;
            try {
                nbt = (CompoundTag) new NBTDeserializer().fromBytes(bytes).getTag();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            new PlayerProfileNBT(nbt).apply(p);
        } catch (RuntimeException ex) {
            Bukkit.broadcastMessage(ChatColor.RED + "" + ChatColor.BOLD + "UH OH! " + ChatColor.GRAY
                + "A problem occurred while reloading profiles! It looks like the user " + ChatColor.YELLOW
                + p.getName() + ChatColor.GRAY + " got wiped by the EOFException.");
            p.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "HEY! " + ChatColor.GRAY + "It looks like you"
                + " got wiped. Please send the debug information above to " + ChatColor.YELLOW + "Endistic " + ChatColor.GRAY
                + " in Project: Nightfall discord, discord DMs, or Hypixel forum conversation. Sorry for the inconvience.");
            p.sendMessage(ChatColor.DARK_GRAY + "Attempting to automatically fix your profile...");
            Bukkit.getScheduler().scheduleSyncDelayedTask(SkyBlock.main, () -> tryApplyProfileAfterReload(p, path), 20);
        }
    }

    @Override
    public void onDisable() {
        for(var p : Bukkit.getOnlinePlayers()) {
            p.closeInventory();
        }
        for (var p : Bukkit.getOnlinePlayers()) {
            var profile = new PlayerProfileNBT(p);
            var path = Path.of(SkyBlock.profileFolder.getPath() + "/" + p.getUniqueId() + ".nbt");
            try {
                p.sendMessage(ChatColor.DARK_PURPLE + "DEBUG! " + ChatColor.GRAY + "If your profile gets wiped, please send the following information:");
                p.sendMessage(profile.base.toString());
                if (!Files.exists(path))
                    Files.createFile(path);
                NBTUtil.write(profile.base, path.toFile());
            } catch (IOException e2) {
                Bukkit.broadcastMessage(ChatColor.RED + "WARNING: An error occured during saving affecting " + p.getName());
                Bukkit.broadcastMessage(ChatColor.DARK_GRAY + "Check console for more information.");
                e2.printStackTrace();
            }
        }
    }

    public static NamespacedKey key(String key) {
        return new NamespacedKey(SkyBlock.main, key);
    }

    public static Location location(
        double x,
        double y,
        double z,
        float pitch,
        float yaw
    ) {
        return new Location(
            Bukkit.getWorld("world"),
            x, y, z,
            pitch, yaw
        );
    }

}
