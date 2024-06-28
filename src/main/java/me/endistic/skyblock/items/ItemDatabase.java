package me.endistic.skyblock.items;

import me.endistic.skyblock.items.accessories.*;
import me.endistic.skyblock.items.armor.floral.FloralBoots;
import me.endistic.skyblock.items.armor.floral.FloralChestplate;
import me.endistic.skyblock.items.armor.floral.FloralHelmet;
import me.endistic.skyblock.items.armor.floral.FloralLeggings;
import me.endistic.skyblock.items.armor.reaver.ReaverBoots;
import me.endistic.skyblock.items.armor.reaver.ReaverChestplate;
import me.endistic.skyblock.items.armor.reaver.ReaverHelmet;
import me.endistic.skyblock.items.armor.reaver.ReaverLeggings;
import me.endistic.skyblock.items.gear.ascendant.*;
import me.endistic.skyblock.items.gear.conqueror.*;
import me.endistic.skyblock.items.gear.entropy.*;
import me.endistic.skyblock.items.gear.gravitational.GravitationalBoots;
import me.endistic.skyblock.items.gear.gravitational.GravitationalChestplate;
import me.endistic.skyblock.items.gear.gravitational.GravitationalHelmet;
import me.endistic.skyblock.items.gear.gravitational.GravitationalLeggings;
import me.endistic.skyblock.items.gear.silence.*;
import me.endistic.skyblock.items.armor.warden.WardenBoots;
import me.endistic.skyblock.items.armor.warden.WardenChestplate;
import me.endistic.skyblock.items.armor.warden.WardenHelmet;
import me.endistic.skyblock.items.armor.warden.WardenLeggings;
import me.endistic.skyblock.items.gear.spells.GravitationalBend;
import me.endistic.skyblock.items.gear.spells.SpacetimeWarper;
import me.endistic.skyblock.items.gear.spells.SpatialWarp;
import me.endistic.skyblock.items.gear.spells.UniversalMarker;
import me.endistic.skyblock.items.resources.*;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ItemDatabase {
    public static HashMap<String, CustomItem> database = new HashMap<>();
    public static List<String> idsWithRecipes = new ArrayList<>();
    public static List<ItemStack> builtItems = new ArrayList<>();

    public static ItemStack item(String id, ItemModifiers modifiers) {
        return database.get(id).build(modifiers);
    }
    public static ItemStack item(String id) {
        return database.get(id).build(new ItemModifiers());
    }
    public static CustomItem citem(String id) { return database.get(id); }

    public static void init() {
        new FloralTalisman();
        new ReaverTalisman();
        new WardenTalisman();

        new HexTalisman();
        new BlueCornTalisman();

        new CorruptedTreeBeacon();
        new FloralFragment();
        new FloralCore();
        new FloralFlux();

        new ReaverFragment();
        new ReaverCore();
        new ReaverFlux();

        new WardenFragment();
        new WardenCore();
        new WardenFlux();

        new BlazingFragment();
        new BlazingCore();
        new BlazingFlux();

        new SilenceHelmet();
        new SilenceChestplate();
        new SilenceLeggings();
        new SilenceBoots();
        new NullBlade();
        new NullShield();

        new ConquerorLongsword();
        new ConquerorShield();
        new ConquerorHelmet();
        new ConquerorChestplate();
        new ConquerorLeggings();
        new ConquerorBoots();

        new EntropicShortbow();
        new EntropicShield();
        new EntropicHelmet();
        new EntropicChestplate();
        new EntropicLeggings();
        new EntropicBoots();

        new GravitationalHelmet();
        new GravitationalChestplate();
        new GravitationalLeggings();
        new GravitationalBoots();

        new SpatialWarp();
        new GravitationalBend();
        new UniversalMarker();
        new SpacetimeWarper();

        new EntropicFragment();
        new ConquerorFragment();

        new AscendantHelmet();
        new AscendantChestplate();
        new AscendantLeggings();
        new AscendantBoots();
        new AscendantStaff();

        new FloralHelmet();
        new FloralChestplate();
        new FloralLeggings();
        new FloralBoots();

        new ReaverHelmet();
        new ReaverChestplate();
        new ReaverLeggings();
        new ReaverBoots();

        new WardenHelmet();
        new WardenChestplate();
        new WardenLeggings();
        new WardenBoots();

        new GlacialScytheTalisman();
    }
}
