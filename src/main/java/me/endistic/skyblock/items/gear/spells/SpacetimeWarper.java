package me.endistic.skyblock.items.gear.spells;

import me.endistic.skyblock.abilities.Ability;
import me.endistic.skyblock.abilities.player.AbilityTrigger;
import me.endistic.skyblock.abilities.shared.InlineAbility;
import me.endistic.skyblock.items.CustomItemTemplate;
import me.endistic.skyblock.items.ItemMetadata;
import me.endistic.skyblock.items.Rarity;
import me.endistic.skyblock.items.ItemSlot;
import me.endistic.skyblock.stats.StatsObject;
import org.bukkit.FluidCollisionMode;
import org.bukkit.Location;
import org.bukkit.Material;

public class SpacetimeWarper extends CustomItemTemplate {
    @Override
    public ItemMetadata getItemData() {
        return new ItemMetadata()
            .setMaterial(Material.WOODEN_SHOVEL)
            .setName("Spacetime Warper")
            .setType(ItemSlot.SWORD)
            .setRarity(Rarity.LEGENDARY);
    }

    @Override
    public StatsObject getStats() {
        return new StatsObject();
    }

    @Override
    public String getId() {
        return "spacetime_warper";
    }

    @Override
    public Ability getAbility() {
        return new InlineAbility(
            20,
            (le) -> {
                var p = le.getEyeLocation().getPitch();
                var y = le.getEyeLocation().getYaw();
                var ray = le.getWorld().rayTraceBlocks(
                    le.getLocation(),
                    le.getEyeLocation().getDirection(),
                    6,
                    FluidCollisionMode.NEVER,
                    true
                );
                if (ray != null) {
                    var hit = ray.getHitPosition();
                    var leLoc = le.getEyeLocation();
                    var tl = new Location(
                        le.getWorld(),
                        hit.getX(),
                        hit.getY(),
                        hit.getZ(),
                        p,
                        y
                    );
                    tl.subtract(leLoc.getDirection().multiply(1));
                    tl.setPitch(p);
                    tl.setYaw(y);
                    le.teleport(
                        tl
                    );

                } else {
                    var dir = le.getLocation().getDirection().clone();
                    dir.normalize();
                    dir.multiply(5);
                    le.teleport(
                        le
                            .getEyeLocation()
                            .add(dir)
                    );
                }
            },
            AbilityTrigger.RIGHT_CLICK,
            "Universal Marker",
            "Teleport 5 blocks forward."
        );
    }
}
