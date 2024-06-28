package me.endistic.skyblock.items;

import me.endistic.skyblock.utils.Range;

import java.util.HashMap;
import java.util.List;

public class UpgradingCost {


    public HashMap<Integer, List<SingleUpgrade>> upgradeMap = new HashMap<>();

    public UpgradingCost put(Integer star, List<SingleUpgrade> costs) {
        upgradeMap.put(star, costs);
        return this;
    }

    public UpgradingCost put(Range stars, List<SingleUpgrade> costs) {
        for(var i : stars) {
            upgradeMap.put(i, costs);
        }
        return this;
    }
}
