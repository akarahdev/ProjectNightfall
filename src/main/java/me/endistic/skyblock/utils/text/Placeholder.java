package me.endistic.skyblock.utils.text;

import org.bukkit.ChatColor;

public class Placeholder implements TextElement {
    public String internalPlaceholder;
    public String key = "n/a";
    public String value = "n/a";
    public Placeholder(String internalPlaceholder) {
        this.internalPlaceholder = internalPlaceholder
            .replace("${", "")
            .replace("}", "");

        var split = this.internalPlaceholder.split(":");

        key = split[0];
        if(split.length == 2)
            value = split[1];

        if(value.startsWith("calc_ability_damage;")) {
            var vals = value.split(";");
            value = String.valueOf(Integer.parseInt(vals[1]) + (Integer.parseInt(vals[2]) * Integer.parseInt(vals[3])));
        }
    }

    @Override
    public String render() {
        switch(key) {
            case "stat.health" -> { return ChatColor.RED + value + "❤ Health"; }
            case "stat.defense" -> { return ChatColor.GREEN + value + "❈ Defense"; }
            case "stat.intelligence" -> { return ChatColor.AQUA + value + "✎ Intelligence"; }
            case "stat.damage" -> { return ChatColor.RED + value + "❁ Damage"; }
            case "stat.strength" -> { return ChatColor.RED + value + "❁ Strength"; }
            case "stat.crit_damage" -> { return ChatColor.BLUE + value + "☠ Critical Damage"; }
            case "stat.crit_chance" -> { return ChatColor.BLUE + value + "☣ Critical Chance"; }
            case "stat.bonus_attack_speed" -> { return ChatColor.YELLOW + value + "⚔ Bonus Attack Speed"; }
            case "stat.ferocity" -> { return ChatColor.RED + value + "⫽ Ferocity"; }
            case "stat.mending" -> { return ChatColor.GREEN + value + "☄ Mending"; }
            case "stat.vitality" -> { return ChatColor.DARK_RED + value + "♨ Vitality"; }
            case "stat.swing_range" -> { return ChatColor.YELLOW + value + "Ⓢ Swing Range"; }
            case "stat.arrow_speed" -> { return ChatColor.GREEN + value + "≈ Arrow Speed"; }
            case "stat.arrow_count" -> { return ChatColor.AQUA + value + "⊽ Arrow Count"; }
            case "stat.speed" -> { return ChatColor.WHITE + value + "✦ Speed"; }
            case "stat.magic_find" -> { return ChatColor.AQUA + value + "✯ Magic Find"; }
            case "stat.lifesteal" -> { return ChatColor.GREEN + value + "? Lifesteal"; }
            case "stat.melee_dmg_pct" -> { return ChatColor.YELLOW + value + "? Melee Damage"; }
            case "stat.ranged_dmg_pct" -> { return ChatColor.YELLOW + value + "? Ranged Damage"; }
            case "stat.ability_dmg_pct" -> { return ChatColor.YELLOW + value + "? Ability Damage"; }

            case "red" -> { return ChatColor.RED.toString(); }
            case "gray" -> { return ChatColor.GRAY.toString(); }
            case "white" -> { return ChatColor.WHITE.toString(); }
        }
        return ChatColor.RED + "" + ChatColor.BOLD + "ERROR IN RENDERING ${" + this.key + ":" + this.value + "} ! ";
    }
}
