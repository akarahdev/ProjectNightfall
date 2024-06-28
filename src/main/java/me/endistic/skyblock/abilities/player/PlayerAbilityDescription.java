package me.endistic.skyblock.abilities.player;

public class PlayerAbilityDescription {
    public String name;

    public double getManaCost() {
        return manaCost;
    }

    public PlayerAbilityDescription setManaCost(double manaCost) {
        this.manaCost = manaCost;
        return this;
    }

    public double manaCost;

    public String getName() {
        return name;
    }

    public PlayerAbilityDescription setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public PlayerAbilityDescription setDescription(String description) {
        this.description = description;
        return this;
    }

    public AbilityTrigger getTrigger() {
        return trigger;
    }

    public PlayerAbilityDescription setTrigger(AbilityTrigger trigger) {
        this.trigger = trigger;
        return this;
    }

    public String description;
    public AbilityTrigger trigger;
}
