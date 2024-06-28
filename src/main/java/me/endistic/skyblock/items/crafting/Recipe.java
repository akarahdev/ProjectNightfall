package me.endistic.skyblock.items.crafting;

import me.endistic.skyblock.SkyBlock;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Recipe {
    public List<String> ids = new ArrayList<>();
    public List<Integer> counts = new ArrayList<>();

    public int getModifierCloneSlot() {
        return modifierCloneSlot;
    }

    public Recipe setModifierCloneSlot(int modifierCloneSlot) {
        this.modifierCloneSlot = modifierCloneSlot;
        return this;
    }

    public int modifierCloneSlot = -1;

    public Recipe() {
        for(var i = 0; i<9; i++) {
            this.ids.add("air");
            this.counts.add(0);
        }
    }

    public Recipe setSlot(int slot, String id, int count) {
        this.counts.set(slot-1, count);
        this.ids.set(slot-1, id);
        return this;
    }

    public RecipeCompareResult compareRecipe(ItemStack[] applicableItems) {
        if(applicableItems.length != 9)
            return new RecipeCompareResult()
                .setResultAmount(0)
                .setValid(false);

        var i = 0;
        var famount = 0;
        for(var item : applicableItems) {
            if((item == null || item.getType() == Material.AIR || item.getAmount() <= 0)
            && (this.ids.get(i).equals("air"))) {
                i++;
                continue;
            } else if(item == null || item.getType() == Material.AIR || item.getAmount() <= 0) {
                return new RecipeCompareResult()
                    .setResultAmount(0)
                    .setValid(false);
            }

            if(!item.hasItemMeta())
                return new RecipeCompareResult()
                    .setResultAmount(0)
                    .setValid(false);

            assert item.getItemMeta() != null;
            if(!item.getItemMeta().getPersistentDataContainer().has(SkyBlock.key("id"))) {
                return new RecipeCompareResult()
                    .setResultAmount(0)
                    .setValid(false);
            }

            var id = item.getItemMeta().getPersistentDataContainer().get(SkyBlock.key("id"), PersistentDataType.STRING);

            if(!Objects.equals(id, this.ids.get(i)))
                return new RecipeCompareResult()
                    .setResultAmount(0)
                    .setValid(false);

            if(item.getAmount() % this.counts.get(i) != 0)
                return new RecipeCompareResult()
                    .setResultAmount(0)
                    .setValid(false);


            if(item.getAmount() / this.counts.get(i) != famount
            && famount != 0)
                return new RecipeCompareResult()
                    .setResultAmount(0)
                    .setValid(false);

            if(famount == 0)
                famount = item.getAmount() / this.counts.get(i);

            i++;
        }

        return new RecipeCompareResult()
            .setResultAmount(famount)
            .setValid(true);
    }
}
