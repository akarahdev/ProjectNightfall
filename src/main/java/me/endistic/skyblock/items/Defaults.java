package me.endistic.skyblock.items;

import me.endistic.skyblock.utils.Range;

import java.util.List;

public class Defaults {
    public static UpgradingCost getUpgradeCost() {
        return new UpgradingCost()
            .put(new Range(1, 3), List.of(new SingleUpgrade("floral_fragment", 1)))
            .put(new Range(4, 6), List.of(new SingleUpgrade("floral_fragment", 2)))
            .put(new Range(7, 9), List.of(
                new SingleUpgrade("floral_fragment", 3),
                new SingleUpgrade("floral_core", 1)
            ))
            .put(10, List.of(
                new SingleUpgrade("floral_fragment", 4),
                new SingleUpgrade("floral_core", 2)
            ))
            .put(11, List.of(new SingleUpgrade("floral_flux", 1)))
            .put(new Range(12, 14), List.of(new SingleUpgrade("reaver_fragment", 1)))
            .put(new Range(15, 17), List.of(new SingleUpgrade("reaver_fragment", 2)))
            .put(new Range(18, 20), List.of(
                new SingleUpgrade("reaver_fragment", 3),
                new SingleUpgrade("reaver_core", 1)
            ))
            .put(21, List.of(
                new SingleUpgrade("reaver_fragment", 4),
                new SingleUpgrade("reaver_core", 2)
            ))
            .put(22, List.of(new SingleUpgrade("reaver_flux", 1)))
            .put(new Range(23, 25), List.of(new SingleUpgrade("warden_fragment", 1)))
            .put(new Range(26, 28), List.of(new SingleUpgrade("warden_fragment", 2)))
            .put(new Range(29, 31), List.of(
                new SingleUpgrade("warden_fragment", 3),
                new SingleUpgrade("warden_core", 1)
            ))
            .put(32, List.of(
                new SingleUpgrade("warden_fragment", 4),
                new SingleUpgrade("warden_core", 2)
            ))
            .put(33, List.of(new SingleUpgrade("warden_flux", 1)))
            .put(new Range(34, 36), List.of(new SingleUpgrade("blazing_fragment", 1)))
            .put(new Range(37, 39), List.of(new SingleUpgrade("blazing_fragment", 2)))
            .put(new Range(40, 42), List.of(
                new SingleUpgrade("blazing_fragment", 3),
                new SingleUpgrade("blazing_core", 1)
            ))
            .put(43, List.of(
                new SingleUpgrade("blazing_fragment", 4),
                new SingleUpgrade("blazing_core", 2)
            ))
            .put(44, List.of(new SingleUpgrade("blazing_flux", 1)));
    }
}
