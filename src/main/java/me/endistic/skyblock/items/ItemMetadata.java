package me.endistic.skyblock.items;

import org.bukkit.Color;
import org.bukkit.Material;

public class ItemMetadata {
    public String name;
    public String description;
    public Rarity rarity;
    public ItemSlot type;
    public Material material;
    public ItemTexture texture;

    public Color getArmorColor() {
        return armorColor;
    }

    public ItemMetadata setArmorColor(Color armorColor) {
        this.armorColor = armorColor;
        return this;
    }

    public Color armorColor;


    public String getName() {
        return name;
    }

    public ItemMetadata setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ItemMetadata setDescription(String description) {
        this.description = description;
        return this;
    }

    public Rarity getRarity() {
        return rarity;
    }

    public ItemMetadata setRarity(Rarity rarity) {
        this.rarity = rarity;
        return this;
    }

    public ItemSlot getType() {
        return type;
    }

    public ItemMetadata setType(ItemSlot type) {
        this.type = type;
        return this;
    }

    public Material getMaterial() {
        return material;
    }

    public ItemMetadata setMaterial(Material material) {
        this.material = material;
        return this;
    }

    public ItemTexture getTexture() {
        return texture;
    }

    public ItemMetadata setTexture(ItemTexture texture) {
        this.texture = texture;
        return this;
    }


}
