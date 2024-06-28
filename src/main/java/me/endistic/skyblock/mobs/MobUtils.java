package me.endistic.skyblock.mobs;

import me.endistic.skyblock.SkyBlock;
import me.endistic.skyblock.data.DataStorage;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.*;
import org.bukkit.persistence.PersistentDataType;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class MobUtils {
    /**
     * Set's a tag on an entity.
     * @param e The entity to set the tag on.
     * @param key The key for the tag.
     * @param value The string value for the tag.
     */
    public static void setValue(Entity e, String key, String value) {
        e.getPersistentDataContainer().set(SkyBlock.key(key), PersistentDataType.STRING, value);
    }

    /**
     * Marks an entity as invulnerable. This makes them unable to take damage and gives them the INVULNERABLE
     * tag.
     * @param e The entity to affect.
     */
    public static void addInvulnerable(Entity e) {
        MobUtils.setValue(e, "is_invulnerable", 1);
        MobUtils.tryRename(e);
    }

    /**
     * Marks an entity as vulnerable. This makes them able to take damage and removes the INVULNERABLE
     * tag.
     * @param e The entity to affect.
     */
    public static void removeInvulnerable(Entity e) {
        e.getPersistentDataContainer().remove(SkyBlock.key("is_invulnerable"));
        MobUtils.tryRename(e);
    }

    /**
     * Marks an entity as locked. This makes them unable to take damage.
     * @param e The entity to affect.
     */
    public static void addLock(Entity e) {
        MobUtils.setValue(e, "locked", 1);
    }

    /**
     * Marks an entity as unlocked. This makes them able to take damage.
     * @param e The entity to affect.
     */
    public static void removeLock(Entity e) {
        MobUtils.setValue(e, "locked", 0);
    }

    /**
     * Checks if an entity is invulnerable.
     * @param e The entity to check.
     * @return Returns true if the entity is invulnerable.
     */
    public static boolean isInvulnerable(Entity e) {
        return MobUtils.getIntegerValue(e, "is_invulnerable") == 1;
    }

    public static void setValue(Entity e, String key, int value) {
        e.getPersistentDataContainer().set(SkyBlock.key(key), PersistentDataType.INTEGER, value);
    }

    public static void setValue(Entity e, String key, boolean value) {
        e.getPersistentDataContainer().set(SkyBlock.key(key), PersistentDataType.BOOLEAN, value);
    }

    public static void setDefense(Entity e, int value) {
        e.getPersistentDataContainer().set(SkyBlock.key("mob_defense"), PersistentDataType.INTEGER, value);
    }

    /**
     * Get's a mob's defense. Use the same formula as Player Defense.
     * @param e The entity to check.
     * @return The mob's defense.
     */
    public static Integer getDefense(Entity e) {
        if(!e.getPersistentDataContainer().has(SkyBlock.key("mob_defense")))
            return 0;
        return e.getPersistentDataContainer().get(SkyBlock.key("mob_defense"), PersistentDataType.INTEGER);
    }

    /**
     * Gets the owner of a mob.
     * @param e The entity to check
     * @return The UUID of the player owner.
     * @throws NullPointerException If the mob has no owner.
     */
    public static Player getOwner(Entity e) throws NullPointerException {
        return Bukkit.getPlayer(UUID.fromString(e.getPersistentDataContainer().get(SkyBlock.key("owner"), PersistentDataType.STRING)));
    }

    /**
     * Sets the owner of a mob.
     * @param e The entity to set.
     * @param uuid The UUID of the player owner.
     */
    public static void setOwner(Entity e, Player pl) {
        e.getPersistentDataContainer().set(SkyBlock.key("owner"), PersistentDataType.STRING, pl.getUniqueId().toString());
    }

    public static String getStringValue(Entity e, String key) {
        if(!e.getPersistentDataContainer().has(SkyBlock.key(key)))
            return "";
        return e.getPersistentDataContainer().get(SkyBlock.key(key), PersistentDataType.STRING);
    }
    public static int getIntegerValue(Entity e, String key) {
        if(!e.getPersistentDataContainer().has(SkyBlock.key(key)))
            return 0;
        return e.getPersistentDataContainer().get(SkyBlock.key(key), PersistentDataType.INTEGER);
    }

    public static boolean compareId(Entity e, String expected) {
        if(!e.getPersistentDataContainer().has(SkyBlock.key("id")))
            return false;

        return Objects.equals(e.getPersistentDataContainer().get(SkyBlock.key("id"), PersistentDataType.STRING), expected);
    }

    public static void trySpawn(CustomMob mob, List<Location> locs) {
        for(int i = 0; i<locs.size(); i++) {
            var loc = locs.get(i);
            loc.getChunk().load(true);
            int finalI = i;
            if(
                Objects.requireNonNull(loc.getWorld())
                    .getLivingEntities()
                    .stream()
                    .filter(it -> MobUtils.compareId(it, mob.getId().toString()))
                    .noneMatch(it -> MobUtils.getIntegerValue(it, "spawn_id") == finalI)
            ) {
                MobDatabase.spawnCustomMob(mob.getId(), loc, new SpawningMetadata()
                    .setSpawnId(finalI));
            }
        }
    }

    public static void tryRename(Entity e) {
        if (e instanceof LivingEntity le) {
            var hp = le.getHealth();
            var id = e.getPersistentDataContainer().get(SkyBlock.key("id"), PersistentDataType.STRING);
            var mob = MobDatabase.get(MobId.valueOf(id));
            var mhp = le.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();

            e.setCustomNameVisible(true);
            e.setCustomName(
                ChatColor.DARK_GRAY + "[" + ChatColor.GRAY + "Lv" + mob.getLevel() + ChatColor.DARK_GRAY + "]"
                    + " " + ChatColor.RED + mob.getName() + " "
                    + ChatColor.GREEN + Math.ceil(hp) + ChatColor.DARK_GRAY + "/" + ChatColor.GREEN + Math.floor(mhp)
            );

            if (MobUtils.getIntegerValue(le, "is_invulnerable") == 1) {
                e.setCustomName(
                    ChatColor.DARK_GRAY + "[" + ChatColor.GRAY + "Lv" + mob.getLevel() + ChatColor.DARK_GRAY + "]"
                        + " " + ChatColor.RED + mob.getName() + " "
                        + ChatColor.AQUA + ChatColor.BOLD + "IMMUNE"
                );
                le.getAttribute(Attribute.GENERIC_FOLLOW_RANGE).setBaseValue(0);
            } else {
                le.getAttribute(Attribute.GENERIC_FOLLOW_RANGE).setBaseValue(10);
            }
        }
    }

    public static void damageMobWithType(
        LivingEntity le,
        int damage,
        DamageType damageType,
        Player p
    ) {




        if ((le.getHealth() - damage) <= 1
            && le.isValid()) {

            var id = le.getPersistentDataContainer().get(SkyBlock.key("id"), PersistentDataType.STRING);
            var mob = MobDatabase.get(MobId.valueOf(id));
            if (DataStorage.quest.containsKey(p.getUniqueId())) {
                DataStorage.quest.get(p.getUniqueId()).addXp(
                    mob.getCombatXp(),
                    p
                );
            }
            DataStorage.combatXp.put(
                p.getUniqueId(),
                DataStorage.combatXp.getOrDefault(p.getUniqueId(), 0) + mob.getCombatXp()
            );
            mob.getDropTable().execute(p, le.getEyeLocation());
            MobTrigger.runDeathTrigger(le);
            le.remove();

        } else le.damage(damage);

        var loc = le.getLocation();

        loc.add(
            (Math.random() * 2) - 1,
            (Math.random() * 2) + 1,
            (Math.random() * 2) - 1
        );
        var disp = (TextDisplay) loc.getWorld().spawnEntity(
            loc,
            EntityType.TEXT_DISPLAY
        );

        disp.setText(ChatColor.GRAY + String.valueOf(damage));
        if(damageType == DamageType.PLAYER_CRITICAL_MELEE) {
            disp.setText(ChatColor.RED + "\uD83D\uDDE1 ✧" + ChatColor.YELLOW +
                damage + ChatColor.WHITE + "✧");
        }
        if(damageType == DamageType.PLAYER_CRITICAL_RANGED) {
            disp.setText(ChatColor.RED + "\uD83C\uDFF9 ✧" + ChatColor.YELLOW +
                damage + ChatColor.WHITE + "✧");
        }
        disp.setBillboard(Display.Billboard.CENTER);
        disp.setAlignment(TextDisplay.TextAlignment.CENTER);
        disp.setSeeThrough(true);
        disp.setBackgroundColor(Color.fromARGB(0, 0, 0, 0));
        disp.setBrightness(new Display.Brightness(15, 15));


        Bukkit.getScheduler().scheduleSyncDelayedTask(SkyBlock.main, disp::remove, 15);
        Bukkit.getScheduler().scheduleSyncDelayedTask(SkyBlock.main, () -> MobUtils.tryRename(le), 1);
    }
}
