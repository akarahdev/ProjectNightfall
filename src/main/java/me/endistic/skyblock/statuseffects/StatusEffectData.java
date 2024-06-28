package me.endistic.skyblock.statuseffects;

public class StatusEffectData {
    public StatusEffectID id() {
        return id;
    }

    public StatusEffectData setId(StatusEffectID id) {
        this.id = id;
        return this;
    }

    public int amplifier() {
        return amplifier;
    }

    public StatusEffectData setAmplifier(int amplifier) {
        this.amplifier = amplifier;
        return this;
    }

    public int duration() {
        return duration;
    }

    public StatusEffectData setDuration(int duration) {
        this.duration = duration;
        return this;
    }

    StatusEffectID id;
    int amplifier;
    int duration;

    public StatusEffectData(
        StatusEffectID id,
        int amplifier,
        int duration
    ) {
        this.id = id;
        this.amplifier = amplifier;
        this.duration = duration;
    }
}
