package me.endistic.skyblock.player.data;

import me.endistic.skyblock.SkyBlock;
import me.endistic.skyblock.data.DataStorage;
import me.endistic.skyblock.items.ItemDatabase;
import me.endistic.skyblock.items.ItemModifiers;
import me.endistic.skyblock.player.Rank;
import net.querz.nbt.tag.*;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.HashMap;

public class PlayerProfileNBT {
    public CompoundTag base = new CompoundTag();

    public static <T> T objOrNull(T object, T alternative) {
        if (object == null
            || object.equals("")
            || object.equals("null"))
            return alternative;
        return object;
    }

    public PlayerProfileNBT(CompoundTag tag) {
        this.base = tag;
    }

    public void apply(Player p) {
        DataStorage.combatXp.put(
            p.getUniqueId(),
            base.getInt("combat_xp")
        );
        DataStorage.ranks.put(
            p.getUniqueId(),
            Rank.valueOf(objOrNull(base.getString("rank"), "DEFAULT"))
        );
        DataStorage.powerStones.put(
            p.getUniqueId(),
            objOrNull(base.getString("power_stone"), "empty")
        );

        p.getInventory().clear();

        var inventory = objOrNull(base.getCompoundTag("inventory"), new CompoundTag());
        var enderChestInventory = objOrNull(base.getCompoundTag("ender_chest"), new CompoundTag());

        for (var slot = 0; slot < 44; slot++) {
            if (inventory.containsKey(String.valueOf(slot))) {
                var itemCompound = inventory.getCompoundTag(String.valueOf(slot));
                if (itemCompound.containsKey("id")) {
                    var item = ItemDatabase.citem(itemCompound.getString("id"));
                    var count = itemCompound.getInt("count");

                    var modifiers = new ItemModifiers();

                    if (itemCompound.containsKey("stars")) {
                        modifiers.setStars(itemCompound.getInt("stars"));
                    }

                    var is = item.build(modifiers);
                    is.setAmount(count);

                    p.getInventory().setItem(slot, is);
                } else if (itemCompound.containsKey("vanilla_id")) {
                    var item = new ItemStack(Material.valueOf(itemCompound.getString("vanilla_id")));
                    item.setAmount(itemCompound.getInt("count"));
                    p.getInventory().setItem(slot, item);
                }


            }


        }

        for (var slot : enderChestInventory.keySet()) {
            if (enderChestInventory.containsKey(String.valueOf(slot))) {
                var itemCompound = enderChestInventory.getCompoundTag(String.valueOf(slot));
                if (itemCompound.containsKey("id")) {
                    var modifiers = new ItemModifiers();

                    if (itemCompound.containsKey("stars")) {
                        modifiers.setStars(itemCompound.getInt("stars"));
                    }

                    var hm = DataStorage.enderChestItems.getOrDefault(p.getUniqueId(), new HashMap<>());
                    hm.put(Integer.parseInt(slot), itemCompound.getString("id"));
                    DataStorage.enderChestItems.put(p.getUniqueId(), hm);

                    var hm2 = DataStorage.enderChestCounts.getOrDefault(p.getUniqueId(), new HashMap<>());
                    hm2.put(Integer.parseInt(slot), itemCompound.getInt("count"));
                    DataStorage.enderChestCounts.put(p.getUniqueId(), hm2);

                    var hm3 = DataStorage.enderChestMods.getOrDefault(p.getUniqueId(), new HashMap<>());
                    hm3.put(Integer.parseInt(slot), modifiers);
                    DataStorage.enderChestMods.put(p.getUniqueId(), hm3);

                    System.out.println(DataStorage.enderChestItems);
                }
            }
        }

        var accessoryBagInventory = objOrNull(base.getListTag("accessory_bag"), new ListTag<>(StringTag.class));

        if (DataStorage.accessoryBag.containsKey(p.getUniqueId()))
            DataStorage.accessoryBag.get(p.getUniqueId()).clear();
        else
            DataStorage.accessoryBag.put(p.getUniqueId(), new ArrayList<>());

        for (var i : accessoryBagInventory.asStringTagList()) {
            System.out.println("ip: " + i);
            DataStorage.accessoryBag.getOrDefault(p.getUniqueId(), new ArrayList<>())
                .add(i.getValue());
        }
    }

    public PlayerProfileNBT(Player p) {
        base.put("rank", new StringTag(
            DataStorage.ranks.getOrDefault(p.getUniqueId(), Rank.DEFAULT).toString()
        ));
        base.put("combat_xp", new IntTag(
            DataStorage.combatXp.getOrDefault(p.getUniqueId(), 0)
        ));
        base.put("version", new IntTag(2));
        base.put("timestamp", new LongTag(System.currentTimeMillis() / 1000));
        base.put("power_stone", new StringTag(DataStorage.powerStones.getOrDefault(p.getUniqueId(), "empty")));

        var inventoryCompound = new CompoundTag();
        var enderChestInventoryCompound = new CompoundTag();
        var accessoryBagArray = new ListTag<>(StringTag.class);

        for (int slot = 0; slot < 44; slot++) {
            var item = p.getInventory().getItem(slot);

            if (item != null) {
                var encoded = encodeItemStack(item);
                if (item.getType() != Material.AIR && encoded != null)
                    inventoryCompound.put(String.valueOf(slot), encoded);
            }
        }

        for (var slot : DataStorage.enderChestItems.getOrDefault(p.getUniqueId(), new HashMap<>()).keySet()) {
            if (DataStorage.enderChestItems.getOrDefault(p.getUniqueId(), new HashMap<>()).containsKey(slot)) {
                var id = DataStorage.enderChestItems.getOrDefault(p.getUniqueId(), new HashMap<>()).get(slot);
                var count = DataStorage.enderChestCounts.getOrDefault(p.getUniqueId(), new HashMap<>()).get(slot);
                var mods = DataStorage.enderChestMods.getOrDefault(p.getUniqueId(), new HashMap<>()).get(slot);

                var item2 = ItemDatabase.citem(id).build(mods);
                item2.setAmount(count);

                var encoded = encodeItemStack(item2);
                if (encoded != null)
                    enderChestInventoryCompound.put(String.valueOf(slot), encoded);
            }
        }

        for (var i : DataStorage.accessoryBag.getOrDefault(p.getUniqueId(), new ArrayList<>())) {
            System.out.println("i: " + i);
            accessoryBagArray.add(new StringTag(i));
        }

        base.put("ender_chest", enderChestInventoryCompound);
        base.put("inventory", inventoryCompound);
        base.put("accessory_bag", accessoryBagArray);
    }

    public static CompoundTag encodeItemStack(ItemStack i) {
        var itemCompound = new CompoundTag();
        if (i.hasItemMeta()) {
            assert i.getItemMeta() != null;

            var pdc = i.getItemMeta().getPersistentDataContainer();
            if (!pdc.has(SkyBlock.key("id")))
                return null;

            var id = pdc.get(SkyBlock.key("id"), PersistentDataType.STRING);
            itemCompound.put("id", new StringTag(id));

            if (pdc.has(SkyBlock.key("modifiers"))) {
                var modsRaw = pdc.get(SkyBlock.key("modifiers"), PersistentDataType.TAG_CONTAINER);
                assert modsRaw != null;
                var mods = ItemModifiers.from(modsRaw);
                itemCompound.put("stars", new IntTag(mods.getStars()));
            }

            itemCompound.put("count", new IntTag(i.getAmount()));
        } else {
            var mat = i.getType();
            var count = i.getAmount();

            itemCompound.putString("vanilla_id", mat.toString());
            itemCompound.putInt("count", count);
        }

        return itemCompound;
    }
}
