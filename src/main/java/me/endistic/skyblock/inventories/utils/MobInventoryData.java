package me.endistic.skyblock.inventories.utils;

import me.endistic.skyblock.SkyBlock;
import me.endistic.skyblock.mobs.CustomMob;
import me.endistic.skyblock.quests.SlayerQuest;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;

public class MobInventoryData {
    public CustomMob mobBase;
    public Material spawnEgg;
    public SlayerQuest slayerQuest;
    public List<MobAbilityData> abilities;


    public CustomMob getMobBase() {
        return mobBase;
    }

    public MobInventoryData setMobBase(CustomMob mobBase) {
        this.mobBase = mobBase;
        return this;
    }

    public Material getSpawnEgg() {
        return spawnEgg;
    }

    public MobInventoryData setSpawnEgg(Material spawnEgg) {
        this.spawnEgg = spawnEgg;
        return this;
    }

    public SlayerQuest getSlayerQuest() {
        return slayerQuest;
    }

    public MobInventoryData setSlayerQuest(SlayerQuest slayerQuest) {
        this.slayerQuest = slayerQuest;
        return this;
    }

    public List<MobAbilityData> getAbilities() {
        return abilities;
    }

    public MobInventoryData setAbilities(List<MobAbilityData> abilities) {
        this.abilities = abilities;
        return this;
    }

    public ItemStack toIcon() {
        var item = new ItemStack(this.getSpawnEgg());
        var meta = item.getItemMeta();
        var lore = new ArrayList<String>();

        meta.setDisplayName(
            ChatColor.DARK_GRAY + "[" + ChatColor.GRAY + "Lv" + this.getMobBase().getLevel() + ChatColor.DARK_GRAY + "] "
            + ChatColor.GRAY + this.getMobBase().getName()
        );

        lore.add(ChatColor.GRAY + "Health: " + ChatColor.RED + this.getMobBase().getHealth() + "HP");
        lore.add(ChatColor.GRAY + "XP: " + ChatColor.YELLOW + this.getMobBase().getCombatXp() + "XP");
        lore.add("");
        lore.add(ChatColor.YELLOW + "Click for more information!");

        var pdc = meta.getPersistentDataContainer();
        pdc.set(SkyBlock.key("bestiary.open_mob"), PersistentDataType.STRING, this.getMobBase().getId().toString());
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }
}
