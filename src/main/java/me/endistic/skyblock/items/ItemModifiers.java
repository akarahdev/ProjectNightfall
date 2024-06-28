package me.endistic.skyblock.items;

import me.endistic.skyblock.SkyBlock;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class ItemModifiers {
    public int getStars() {
        return stars;
    }

    public ItemModifiers setStars(int stars) {
        this.stars = stars;
        return this;
    }

    public int stars = 0;

    public static ItemModifiers from(PersistentDataContainer pdc) {
        return new ItemModifiers()
            .setStars(pdc.getOrDefault(SkyBlock.key("stars"), PersistentDataType.INTEGER, 0));
    }

}
