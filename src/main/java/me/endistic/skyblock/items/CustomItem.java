package me.endistic.skyblock.items;

import me.endistic.skyblock.SkyBlock;
import me.endistic.skyblock.abilities.Ability;
import me.endistic.skyblock.items.crafting.Recipe;
import me.endistic.skyblock.stats.StatsObject;
import me.endistic.skyblock.utils.text.Text;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.persistence.PersistentDataType;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Stores the backing data in the CustomItemTemplate class.
 */
public class CustomItem {
    public StatsObject stats;
    public ItemMetadata data;
    public String id;
    public Ability ability;

    public UpgradingCost getUpgradingCost() {
        return upgradingCost;
    }

    public CustomItem setUpgradingCost(UpgradingCost upgradingCost) {
        this.upgradingCost = upgradingCost;
        return this;
    }

    public UpgradingCost upgradingCost;

    public List<Recipe> getRecipes() {
        return recipe;
    }

    public CustomItem setRecipes(List<Recipe> recipe) {
        this.recipe = recipe;
        return this;
    }

    public List<Recipe> recipe;

    public Ability getAbility() {
        return ability;
    }

    public CustomItem setAbility(Ability ability) {
        this.ability = ability;
        return this;
    }

    public StatsObject getStats() {
        return stats;
    }


    public CustomItem setStats(StatsObject stats) {
        this.stats = stats;
        return this;
    }

    public ItemMetadata getData() {
        return data;
    }

    public CustomItem setData(ItemMetadata data) {
        this.data = data;
        return this;
    }

    public String getId() {
        return id;
    }

    public CustomItem setId(String id) {
        this.id = id;
        return this;
    }

    public ItemStack build(ItemModifiers modifiers) {
        var name = this.getData().getRarity().getColor() + this.getData().getName();
        if(modifiers.getStars() > 0)
            name += " " + CustomItem.formatStars(modifiers.getStars());
        var lore = new ArrayList<String>();



        if (this.getStats() != null) {
            var newStats = this.getStats().withModifiers(modifiers);

            if (newStats.calculateGearScore() > 0)
                lore.add(ChatColor.GRAY + "Gear Score: " + ChatColor.LIGHT_PURPLE + newStats.calculateGearScore());
            lore.addAll(newStats.getStatsLore());

            if (newStats.getDamage() != 0
                || newStats.getStrength() != 0
                || newStats.getCriticalDamage() != 0
                || newStats.getCriticalChance() != 0
                || newStats.getSwingRange() != 0
                || newStats.getMaxHealth() != 0
                || newStats.getIntelligence() != 0
                || newStats.getDefense() != 0)
                lore.add("");
        }

        if (this.getAbility() != null) {
            lore.add(ChatColor.GOLD + "Ability: "
                + this.getAbility().getDescription().getName() +
                ChatColor.YELLOW + ChatColor.BOLD + " RIGHT CLICK");
            lore.addAll(
                new Text(this.getAbility().getDescription().getDescription()).renderItemLore()
            );
            lore.add(ChatColor.DARK_GRAY + "Cost: " + ChatColor.DARK_AQUA + this.getAbility().getDescription().getManaCost());
            lore.add("");
        }


        lore.add(this.getData().getRarity().getColor()
            + "" + ChatColor.BOLD + this.getData().getRarity().getName()
            + " " + this.getData().getType().toString());


        var i = new ItemStack(this.getData().getMaterial());
        var meta = i.getItemMeta();
        assert meta != null;
        meta.setDisplayName(name);
        meta.setLore(lore);
        meta.addItemFlags(
            ItemFlag.HIDE_ATTRIBUTES,
            ItemFlag.HIDE_DYE,
            ItemFlag.HIDE_UNBREAKABLE
        );
        meta.setUnbreakable(true);
        for (var slot : EquipmentSlot.values()) {
            try {
                meta.addAttributeModifier(
                    Attribute.GENERIC_ATTACK_SPEED,
                    new AttributeModifier(
                        new UUID(493, 81),
                        "generic.attack_speed",
                        1000,
                        AttributeModifier.Operation.ADD_NUMBER,
                        slot
                    )
                );
            } catch (IllegalArgumentException ignored) {
            }

        }

        var pdc = meta.getPersistentDataContainer();
        pdc.set(SkyBlock.key("id"), PersistentDataType.STRING, this.getId());
        if (!pdc.has(SkyBlock.key("modifiers")))
            pdc.set(
                SkyBlock.key("modifiers"),
                PersistentDataType.TAG_CONTAINER,
                i.getItemMeta().getPersistentDataContainer().getAdapterContext().newPersistentDataContainer()
            );
        var modifierPdc = pdc.get(
            SkyBlock.key("modifiers"),
            PersistentDataType.TAG_CONTAINER
        );
        assert modifierPdc != null;
        modifierPdc.set(
            SkyBlock.key("stars"),
            PersistentDataType.INTEGER,
            modifiers.getStars()
        );
        pdc.set(
            SkyBlock.key("modifiers"),
            PersistentDataType.TAG_CONTAINER,
            modifierPdc
        );
        i.setItemMeta(meta);

        if (this.getData().getArmorColor() != null) {
            var li = (LeatherArmorMeta) i.getItemMeta();
            li.setColor(this.getData().getArmorColor());
            i.setItemMeta(li);
        }

        if (this.getData().getMaterial() == Material.PLAYER_HEAD
            && this.getData().getTexture() != null) {
            var texture = this.getData().getTexture();
            var skullMeta = (SkullMeta) i.getItemMeta();
            var profile = Bukkit.createPlayerProfile(UUID.randomUUID());
            var textures = profile.getTextures();
            textures.setSkin(
                minecraftHead(texture.texture)
            );
            profile.setTextures(textures);
            skullMeta.setOwnerProfile(profile);
            i.setItemMeta(skullMeta);
        }




        return i;
    }

    public static URL minecraftHead(String minecraftValue) {
        try {
            return new URL("http://textures.minecraft.net/texture/" + minecraftValue);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public static String formatStars(int stars) {
        var starIndex = stars % 11;
        var moonIndex = ((int) Math.floor((double) stars / 11)) % 11;

        var nameFormats = new String[]{
            "",
            ChatColor.GOLD + "# ",
            ChatColor.GOLD + "## ",
            ChatColor.GOLD + "### ",
            ChatColor.GOLD + "#### ",
            ChatColor.GOLD + "##### ",
            ChatColor.LIGHT_PURPLE + "#" + ChatColor.GOLD + "#### ",
            ChatColor.LIGHT_PURPLE + "##" + ChatColor.GOLD + "### ",
            ChatColor.LIGHT_PURPLE + "###" + ChatColor.GOLD + "## ",
            ChatColor.LIGHT_PURPLE + "####" + ChatColor.GOLD + "# ",
            ChatColor.LIGHT_PURPLE + "##### ",
            ChatColor.AQUA + "#" + ChatColor.LIGHT_PURPLE + "#### ",
            ChatColor.AQUA + "##" + ChatColor.LIGHT_PURPLE + "### ",
            ChatColor.AQUA + "###" + ChatColor.LIGHT_PURPLE + "## ",
            ChatColor.AQUA + "####" + ChatColor.LIGHT_PURPLE + "# ",
            ChatColor.AQUA + "##### ",
            ChatColor.WHITE + "#" + ChatColor.AQUA + "#### ",
            ChatColor.WHITE + "##" + ChatColor.AQUA + "### ",
            ChatColor.WHITE + "###" + ChatColor.AQUA + "## ",
            ChatColor.WHITE + "####" + ChatColor.AQUA + "# ",
            ChatColor.WHITE + "##### "
        };

        var fmt = new StringBuilder();
        if(starIndex > 0)
            fmt.append(nameFormats[starIndex].replace("#", "✪"));
        if(moonIndex > 0)
            fmt.append(nameFormats[moonIndex].replace("#", "☽"));

        return fmt.toString();
    }

    public static ItemStack headWithTexture(ItemStack i, ItemTexture texture) {
        var skullMeta = (SkullMeta) i.getItemMeta();
        var profile = Bukkit.createPlayerProfile(UUID.randomUUID());
        var textures = profile.getTextures();
        textures.setSkin(
            minecraftHead(texture.texture)
        );
        profile.setTextures(textures);
        skullMeta.setOwnerProfile(profile);
        i.setItemMeta(skullMeta);
        return i;
    }

    public static ItemStack headWithTexture(ItemStack i, UUID uuid) {
        var skullMeta = (SkullMeta) i.getItemMeta();
        var profile = Bukkit.createPlayerProfile(uuid);
        skullMeta.setOwnerProfile(profile);
        i.setItemMeta(skullMeta);
        return i;
    }


}
