package me.endistic.skyblock.mobs;

import me.endistic.skyblock.abilities.Ability;
import me.endistic.skyblock.inventories.utils.BestiaryFilter;
import me.endistic.skyblock.inventories.utils.MobInventoryData;
import me.endistic.skyblock.mobs.drops.DropTable;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.EntityEquipment;

import java.util.List;

/**
 * Represents a Custom Mob in Nightfall.
 */
public interface CustomMob {
    /**
     * @return The type of this entity
     */
    EntityType getEntityType();

    /**
     * @return The abilities this entity will cast.
     */
    default List<Ability> getAbilities() {
        return List.of();
    }

    /**
     * Static code to execute on spawning.
     * @param le The spawned entity.
     */
    default void runOnSpawn(LivingEntity le) {};

    /**
     * @return The level displayed.
     */
    int getLevel();

    /**
     * @return The name of the mob.
     */
    String getName();

    /**
     * @return The mob's base melee damage.
     */
    double getMeleeDamage();

    /**
     * @return The ID of the mob.
     */
    MobId getId();

    /**
     * @return The mob's drop table.
     */
    DropTable getDropTable();

    /**
     * @return The health of the mob.
     */
    double getHealth();
    /**
     * @return The Combat XP yielded by the mob/
     */
    int getCombatXp();
    /**
     * @return The bestiary entry associated with this mob.
     */
    public MobInventoryData getBestiaryData();

    /**
     * @return The mob type.
     */
    public BestiaryFilter getBestiaryFilter();

    /**
     * @return The equipment of the entity.
     */
    default EntityEquipment getEquipment(EntityEquipment equipment) {
        return equipment;
    }
}
