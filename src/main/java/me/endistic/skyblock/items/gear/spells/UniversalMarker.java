package me.endistic.skyblock.items.gear.spells;

import me.endistic.skyblock.abilities.Ability;
import me.endistic.skyblock.abilities.player.AbilityTrigger;
import me.endistic.skyblock.abilities.shared.InlineAbility;
import me.endistic.skyblock.data.DataStorage;
import me.endistic.skyblock.items.CustomItemTemplate;
import me.endistic.skyblock.items.ItemMetadata;
import me.endistic.skyblock.items.Rarity;
import me.endistic.skyblock.items.ItemSlot;
import me.endistic.skyblock.stats.StatsObject;
import org.bukkit.FluidCollisionMode;
import org.bukkit.Location;
import org.bukkit.Material;

public class UniversalMarker extends CustomItemTemplate {
    @Override
    public ItemMetadata getItemData() {
        return new ItemMetadata()
            .setMaterial(Material.BLAZE_ROD)
            .setName("Universal Marker")
            .setType(ItemSlot.SWORD)
            .setRarity(Rarity.LEGENDARY);
    }

    @Override
    public StatsObject getStats() {
        return new StatsObject();
    }

    @Override
    public String getId() {
        return "universal_marker";
    }

    @Override
    public Ability getAbility() {
        return new InlineAbility(
            0,
            (le) -> {
                var ray = le.getWorld().rayTraceBlocks(
                    le.getEyeLocation(),
                    le.getEyeLocation().getDirection(),
                    8,
                    FluidCollisionMode.NEVER,
                    true
                );
                if (ray != null) {
                    var hit = ray.getHitPosition();
                    DataStorage.spatialMarks.put(
                        le.getUniqueId(),
                        new Location(
                            le.getWorld(),
                            hit.getX(),
                            hit.getY(),
                            hit.getZ()
                        )
                    );

                } else {
                    var dir = le.getLocation().getDirection().clone();
                    dir.normalize();
                    dir.multiply(8);
                    DataStorage.spatialMarks.put(
                        le.getUniqueId(),
                        le
                            .getEyeLocation()
                            .add(dir)
                    );
                }
            },
            AbilityTrigger.RIGHT_CLICK,
            "Universal Marker",
            "Mark a position 8 blocks infront of you."
        );
    }
}
