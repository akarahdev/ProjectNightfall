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
import org.bukkit.Material;

public class SpatialWarp extends CustomItemTemplate {
    @Override
    public ItemMetadata getItemData() {
        return new ItemMetadata()
            .setMaterial(Material.ENDER_EYE)
            .setName("Spatial Warp")
            .setType(ItemSlot.SWORD)
            .setRarity(Rarity.LEGENDARY);
    }

    @Override
    public StatsObject getStats() {
        return new StatsObject();
    }

    @Override
    public String getId() {
        return "spatial_warp";
    }

    @Override
    public Ability getAbility() {
        return new InlineAbility(
            50,
            (le) -> {
                if(DataStorage.spatialMarks.containsKey(le.getUniqueId())) {
                    var l = DataStorage.spatialMarks.get(le.getUniqueId()).clone();
                    l.setPitch(le.getLocation().getPitch());
                    l.setYaw(le.getLocation().getYaw());
                    le.teleport(l);
                    DataStorage.spatialMarks.remove(le.getUniqueId());
                }
            },
            AbilityTrigger.RIGHT_CLICK,
            "Spatial Warp",
            "Mark a position 8 blocks infront of you. Upon casting again, teleport to the mark."
        );
    }
}
