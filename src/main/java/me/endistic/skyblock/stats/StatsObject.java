package me.endistic.skyblock.stats;

import me.endistic.skyblock.items.ItemModifiers;
import me.endistic.skyblock.utils.text.Text;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class StatsObject implements Cloneable {
    public static List<Field> fields;
    static {
        fields = new ArrayList<>();
        fields.addAll(
            List.of(StatsObject.class.getDeclaredFields())
        );
        try {
            fields.remove(StatsObject.class.getDeclaredField("fields"));
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }


    }
    public double maxHealth = 0;
    public double defense = 0;
    public double intelligence = 0;

    public double damage = 0;
    public double strength = 0;
    public double criticalDamage = 0;
    public double criticalChance = 0;
    public double swingRange = 0;
    public double walkSpeed = 0;
    public double magicFind = 0;
    public double ferocity = 0;
    public double healthRegen = 0;
    public double mending = 0;
    public double vitality = 0;
    public double lifesteal = 0;
    public double bonusAttackSpeed = 0;
    public double arrowCount = 0;
    public double arrowSpeed = 0;
    public double meleeDamagePct = 0;
    public double rangedDamagePct = 0;
    public double abilityDamagePct = 0;

    public double getBaseAbilityDamage() {
        return baseAbilityDamage;
    }

    public StatsObject setBaseAbilityDamage(double baseAbilityDamage) {
        this.baseAbilityDamage = baseAbilityDamage;
        return this;
    }

    public double getAbilityDamageScaling() {
        return abilityDamageScaling;
    }

    public StatsObject setAbilityDamageScaling(double abilityDamageScaling) {
        this.abilityDamageScaling = abilityDamageScaling;
        return this;
    }

    public double baseAbilityDamage = 0;
    public double abilityDamageScaling = 0;

    public double getMeleeDamagePct() {
        return meleeDamagePct;
    }

    public StatsObject setMeleeDamagePct(double meleeDamagePct) {
        this.meleeDamagePct = meleeDamagePct;
        return this;
    }

    public double getRangedDamagePct() {
        return rangedDamagePct;
    }

    public StatsObject setRangedDamagePct(double rangedDamagePct) {
        this.rangedDamagePct = rangedDamagePct;
        return this;
    }

    public double getAbilityDamagePct() {
        return abilityDamagePct;
    }

    public StatsObject setAbilityDamagePct(double abilityDamagePct) {
        this.abilityDamagePct = abilityDamagePct;
        return this;
    }

    public double getWalkSpeed() {
        return walkSpeed;
    }

    public StatsObject setWalkSpeed(double walkSpeed) {
        this.walkSpeed = walkSpeed;
        return this;
    }

    public double getMagicFind() {
        return magicFind;
    }

    public StatsObject setMagicFind(double magicFind) {
        this.magicFind = magicFind;
        return this;
    }

    public double getFerocity() {
        return ferocity;
    }

    public StatsObject setFerocity(double ferocity) {
        this.ferocity = ferocity;
        return this;
    }

    public double getHealthRegen() {
        return healthRegen;
    }

    public StatsObject setHealthRegen(double healthRegen) {
        this.healthRegen = healthRegen;
        return this;
    }

    public double getMending() {
        return mending;
    }

    public StatsObject setMending(double mending) {
        this.mending = mending;
        return this;
    }

    public double getVitality() {
        return vitality;
    }

    public StatsObject setVitality(double vitality) {
        this.vitality = vitality;
        return this;
    }



    public double getLifesteal() {
        return lifesteal;
    }

    public StatsObject setLifesteal(double lifesteal) {
        this.lifesteal = lifesteal;
        return this;
    }



    public double getBonusAttackSpeed() {
        return bonusAttackSpeed;
    }

    public StatsObject setBonusAttackSpeed(double bonusAttackSpeed) {
        this.bonusAttackSpeed = bonusAttackSpeed;
        return this;
    }



    public double getArrowCount() {
        return arrowCount;
    }

    public StatsObject setArrowCount(double arrowCount) {
        this.arrowCount = arrowCount;
        return this;
    }

    public double getArrowSpeed() {
        return arrowSpeed;
    }

    public StatsObject setArrowSpeed(double arrowSpeed) {
        this.arrowSpeed = arrowSpeed;
        return this;
    }

    public StatsObject getPlayerBaseStats() {
        this.setMaxHealth(100 + 2200);
        this.setDefense(220);
        this.setIntelligence(1000);
        this.setDamage(5);
        this.setCriticalChance(30);
        this.setSwingRange(3);
        this.setArrowCount(1);
        this.setWalkSpeed(100);
        this.setMending(100);
        this.setVitality(100);
        return this;
    }

    public double getMaxHealth() {
        return maxHealth;
    }

    public StatsObject setMaxHealth(double maxHealth) {
        this.maxHealth = maxHealth;
        return this;
    }

    public double getDefense() {
        return defense;
    }

    public StatsObject setDefense(double defense) {
        this.defense = defense;
        return this;
    }

    public double getIntelligence() {
        return intelligence;
    }

    public StatsObject setIntelligence(double intelligence) {
        this.intelligence = intelligence;
        return this;
    }

    public double getDamage() {
        return damage;
    }

    public StatsObject setDamage(double damage) {
        this.damage = damage;
        return this;
    }

    public double getStrength() {
        return strength;
    }

    public StatsObject setStrength(double strength) {
        this.strength = strength;
        return this;
    }

    public double getCriticalDamage() {
        return criticalDamage;
    }

    public StatsObject setCriticalDamage(double criticalDamage) {
        this.criticalDamage = criticalDamage;
        return this;
    }

    public double getCriticalChance() {
        return criticalChance;
    }

    public StatsObject setCriticalChance(double criticalChance) {
        this.criticalChance = criticalChance;
        return this;
    }

    public double getSwingRange() {
        return swingRange;
    }

    public StatsObject setSwingRange(double swingRange) {
        this.swingRange = swingRange;
        return this;
    }


    public StatsObject add(StatsObject other) {
        var newStats = new StatsObject();
        try {
            for(var f : fields) {
                f.set(newStats, (Double) f.get(this) + (Double)  f.get(other));
            }
        } catch(IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return newStats;
    }

    public StatsObject multiply(double amount) {
        try {
            for(var f : fields) {
                f.set(this, (Double) f.get(this) * amount);
            }
        } catch(IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return this;
    }

    public StatsObject floor() {
        try {
            for(var f : fields) {
                if(!f.getName().equals("swingRange")
                    && !f.getName().equals("arrowCount")
                    && !f.getName().equals("rangedDamagePct")
                    && !f.getName().equals("meleeDamagePct")
                    && !f.getName().equals("criticalChance"))

                    f.set(this, Math.floor((Double) f.get(this)));

            }
        } catch(IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return this;
    }

    @Override
    public StatsObject clone() throws CloneNotSupportedException {
        StatsObject cloned = (StatsObject) super.clone();
        try {
            for(var f : fields) {
                f.set(cloned, f.get(this));
            }
        } catch(IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return cloned;
    }

    public static String formatStatDecimal(double stat) {
        var decimalFormat = new DecimalFormat("###,###,###,###.##");
        return decimalFormat.format(stat);
    }
    public static String getLoreEntry(
        String statName,
        double value,
        ChatColor color
    ) {
        if (value > 0)
            return (ChatColor.GRAY + statName + ": " + color + "+" + StatsObject.formatStatDecimal(value));
        else if (value < 0)
            return (ChatColor.GRAY + statName + ": " + color + "-" + StatsObject.formatStatDecimal(Math.abs(value)));
        else
            return ChatColor.DARK_GRAY + "Null Line: Report this to Endistic!";
    }

    public static String getLoreEntry(
        String statName,
        double value,
        ChatColor color,
        String post
    ) {
        if (value > 0)
            return (ChatColor.GRAY + statName + ": " + color + "+" + StatsObject.formatStatDecimal(value) + post);
        else if (value < 0)
            return (ChatColor.GRAY + statName + ": " + color + "-" + StatsObject.formatStatDecimal(Math.abs(value)) + post);
        else
            return null;
    }

    public List<String> getStatsLore() {
        var lore = new ArrayList<String>();


        if (this.getMeleeDamagePct() != 0)
            lore.add(getLoreEntry("Melee Damage", this.getMeleeDamagePct(), ChatColor.GOLD, "%"));
        if (this.getRangedDamagePct() != 0)
            lore.add(getLoreEntry("Ranged Damage", this.getRangedDamagePct(), ChatColor.GOLD, "%"));
        if (this.getAbilityDamagePct() != 0)
            lore.add(getLoreEntry("Ability Damage Damage", this.getAbilityDamagePct(), ChatColor.GOLD, "%"));

        if (this.getDamage() != 0)
            lore.add(getLoreEntry("Damage", this.getDamage(), ChatColor.RED));
        if (this.getStrength() != 0)
            lore.add(getLoreEntry("Strength", this.getStrength(), ChatColor.RED));
        if (this.getCriticalChance() != 0)
            lore.add(getLoreEntry("Critical Chance", this.getCriticalChance(), ChatColor.RED, "%"));
        if (this.getCriticalDamage() != 0)
            lore.add(getLoreEntry("Critical Damage", this.getCriticalDamage(), ChatColor.RED, "%"));
        if (this.getBonusAttackSpeed() != 0)
            lore.add(getLoreEntry("Bonus Attack Speed", this.getBonusAttackSpeed(), ChatColor.RED, "%"));
        if (this.getBaseAbilityDamage() != 0)
            lore.add(getLoreEntry("Ability Damage", this.getBaseAbilityDamage(), ChatColor.RED));
        if (this.getAbilityDamageScaling() != 0)
            lore.add(getLoreEntry("Ability Damage Scale", this.getAbilityDamageScaling(), ChatColor.RED));
        if (this.getArrowSpeed() != 0)
            lore.add(getLoreEntry("Arrow Speed", this.getArrowSpeed(), ChatColor.GREEN, "%"));
        if (this.getArrowCount() != 0)
            lore.add(getLoreEntry("Arrows Shot", this.getArrowCount(), ChatColor.GREEN));
        if (this.getSwingRange() != 0)
            lore.add(getLoreEntry("Swing Range", this.getSwingRange(), ChatColor.GREEN));

        if (this.getMaxHealth() != 0)
            lore.add(getLoreEntry("Health", this.getMaxHealth(), ChatColor.GREEN));
        if (this.getIntelligence() != 0)
            lore.add(getLoreEntry("Intelligence", this.getIntelligence(), ChatColor.GREEN));
        if (this.getDefense() != 0)
            lore.add(getLoreEntry("Defense", this.getDefense(), ChatColor.GREEN));
        if (this.getWalkSpeed() != 0)
            lore.add(getLoreEntry("Speed", this.getWalkSpeed(), ChatColor.GREEN));
        if (this.getMagicFind() != 0)
            lore.add(getLoreEntry("Magic Find", this.getMagicFind(), ChatColor.GREEN));
        if (this.getFerocity() != 0)
            lore.add(getLoreEntry("Ferocity", this.getFerocity(), ChatColor.GREEN));
        if (this.getHealthRegen() != 0)
            lore.add(getLoreEntry("Health Regen", this.getHealthRegen(), ChatColor.GREEN));
        if (this.getMending() != 0)
            lore.add(getLoreEntry("Mending", this.getMending(), ChatColor.GREEN));
        if (this.getVitality() != 0)
            lore.add(getLoreEntry("Vitality", this.getVitality(), ChatColor.GREEN));
        if (this.getLifesteal() != 0)
            lore.add(getLoreEntry("Life Steal", this.getLifesteal(), ChatColor.GREEN));


        return lore;
    }

    public List<String> getCVisualStatsLore() {
        var lore = new ArrayList<String>();

        if (this.getDamage() != 0)
            lore.add(new Text("${stat.damage:" + StatsObject.formatStatDecimal(this.getDamage()) + "}").render());
        if (this.getStrength() != 0)
            lore.add(new Text("${stat.strength:" + StatsObject.formatStatDecimal(this.getStrength()) + "}").render());
        if (this.getCriticalChance() != 0)
            lore.add(new Text("${stat.crit_chance:" + StatsObject.formatStatDecimal(this.getCriticalChance()) + "}").render());
        if (this.getCriticalDamage() != 0)
            lore.add(new Text("${stat.crit_damage:" + StatsObject.formatStatDecimal(this.getCriticalDamage()) + "}").render());
        if (this.getBonusAttackSpeed() != 0)
            lore.add(new Text("${stat.bonus_attack_speed:" + StatsObject.formatStatDecimal(this.getBonusAttackSpeed()) + "}").render());
        if (this.getArrowSpeed() != 0)
            lore.add(new Text("${stat.arrow_speed:" + StatsObject.formatStatDecimal(this.getArrowSpeed()) + "}").render());
        if (this.getArrowCount() != 0)
            lore.add(new Text("${stat.arrow_count:" + StatsObject.formatStatDecimal(this.getArrowCount()) + "}").render());
        if (this.getSwingRange() != 0)
            lore.add(new Text("${stat.swing_range:" + StatsObject.formatStatDecimal(this.swingRange) + "}").render());

        if (this.getMaxHealth() != 0)
            lore.add(new Text("${stat.health:" + StatsObject.formatStatDecimal(this.getMaxHealth()) + "}").render());
        if (this.getIntelligence() != 0)
            lore.add(new Text("${stat.intelligence:" + StatsObject.formatStatDecimal(this.getIntelligence()) + "}").render());
        if (this.getDefense() != 0)
            lore.add(new Text("${stat.defense:" + StatsObject.formatStatDecimal(this.getDefense()) + "}").render());
        if (this.getWalkSpeed() != 0)
            lore.add(new Text("${stat.speed:" + StatsObject.formatStatDecimal(this.getWalkSpeed()) + "}").render());
        if (this.getMagicFind() != 0)
            lore.add(new Text("${stat.magic_find:" + StatsObject.formatStatDecimal(this.getMagicFind()) + "}").render());
        if (this.getFerocity() != 0)
            lore.add(new Text("${stat.ferocity:" + StatsObject.formatStatDecimal(this.getFerocity()) + "}").render());
        if (this.getHealthRegen() != 0)
            lore.add(new Text("${stat.health_regen:" + StatsObject.formatStatDecimal(this.getHealthRegen()) + "}").render());
        if (this.getMending() != 0)
            lore.add(new Text("${stat.mending:" + StatsObject.formatStatDecimal(this.getMending()) + "}").render());
        if (this.getVitality() != 0)
            lore.add(new Text("${stat.vitality:" + StatsObject.formatStatDecimal(this.getVitality()) + "}").render());
        if (this.getLifesteal() != 0)
            lore.add(new Text("${stat.lifesteal:" + StatsObject.formatStatDecimal(this.getLifesteal()) + "}").render());

        if (this.getMeleeDamagePct() != 0)
            lore.add(new Text("${stat.melee_dmg_pct:" + this.getDamage() + "}").render());
        if (this.getRangedDamagePct() != 0)
            lore.add(new Text("${stat.ranged_dmg_pct:" + this.getDamage() + "}").render());
        if (this.getAbilityDamagePct() != 0)
            lore.add(new Text("${stat.ability_dmg_pct:" + this.getDamage() + "}").render());

        return lore;
    }

    public int calculateGearScore() {
        return
            (int) ((1 * this.getDamage())
                + (1 * this.getMaxHealth())
                + (2 * this.getDefense())
                + (0.5 * this.getIntelligence())
                + (1 * this.getCriticalDamage())
                + (1 * this.getCriticalChance())
                + (1 * this.getStrength())
                + (1 * this.getBonusAttackSpeed())
                + (30 * this.getSwingRange())
                + (30 * this.getArrowCount())
                + (1 * this.getArrowSpeed())
                + (1 * this.getFerocity()));
    }

    public StatsObject withModifiers(ItemModifiers modifiers) {
        try {
            var returned = this.clone();
            var starCounter = modifiers.getStars();
            while(starCounter >= 11) {
                returned = returned.multiply(1.22);
                starCounter -= 11;
                returned = returned.floor();
            }
            returned = returned.multiply(1 + (starCounter * 0.02));
            return returned;
        } catch(CloneNotSupportedException e) {
            return null;
        }
    }
}
