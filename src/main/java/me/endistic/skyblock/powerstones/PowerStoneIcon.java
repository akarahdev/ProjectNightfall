package me.endistic.skyblock.powerstones;

import me.endistic.skyblock.SkyBlock;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;

public class PowerStoneIcon {
    public String name = "Unnamed";
    public Material material = Material.BARREL;

    public String getId() {
        return id;
    }

    public PowerStoneIcon setId(String id) {
        this.id = id;
        return this;
    }

    public String id = "empty";

    public String getName() {
        return name;
    }

    public PowerStoneIcon setName(String name) {
        this.name = name;
        return this;
    }

    public Material getMaterial() {
        return material;
    }

    public PowerStoneIcon setMaterial(Material material) {
        this.material = material;
        return this;
    }
    public ItemStack build(String idFound, int magicalPower) {
        var i = new ItemStack(this.getMaterial());
        var lore = new ArrayList<String>();
        lore.addAll(PowerStone.idToStone(this.getId())
            .getMultipliedPerkStats()
            .multiply(PowerStone.mpToMultiplier(magicalPower))
            .getCVisualStatsLore());
        lore.add(ChatColor.GRAY + "Bonus effects:");
        lore.addAll(PowerStone.idToStone(this.getId()).getBasePerkStats().getCVisualStatsLore());
        if(idFound.equals(this.getId()))
            lore.add(ChatColor.GREEN + "" + ChatColor.BOLD + "SELECTED!");
            else
                lore.add(ChatColor.YELLOW + "Click to select!");
        var m = i.getItemMeta();
        m.setLore(lore);
        m.setDisplayName(ChatColor.GOLD + this.getName());
        m.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        var pdc = m.getPersistentDataContainer();
        pdc.set(SkyBlock.key("power_stone_set"), PersistentDataType.STRING, this.getId());

        i.setItemMeta(m);

        return i;
    }
}
