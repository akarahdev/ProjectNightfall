package me.endistic.skyblock.inventories.utils;

import me.endistic.skyblock.utils.text.Text;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class MobAbilityData {
    public String name;
    public ChatColor nameColor;
    public String description;
    public Material icon;
    public String occurance;

    public ChatColor getNameColor() {
        return nameColor;
    }

    public MobAbilityData setNameColor(ChatColor nameColor) {
        this.nameColor = nameColor;
        return this;
    }

    public String getOccurance() {
        return occurance;
    }

    public MobAbilityData setOccurance(String occurance) {
        this.occurance = occurance;
        return this;
    }

    public Material getIcon() {
        return icon;
    }

    public MobAbilityData setIcon(Material icon) {
        this.icon = icon;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public MobAbilityData setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getName() {
        return name;
    }

    public MobAbilityData setName(String name) {
        this.name = name;
        return this;
    }

    public ItemStack getAbilityItem() {
        var item = new ItemStack(this.getIcon());
        var meta = item.getItemMeta();
        var lore = new ArrayList<String>();

        meta.setDisplayName(nameColor + this.name);
        lore.add(ChatColor.DARK_GRAY + this.occurance);
        lore.addAll(new Text(this.description).renderItemLore());

        assert meta != null;
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }




}
