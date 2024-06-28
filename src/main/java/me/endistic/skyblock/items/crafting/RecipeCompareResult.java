package me.endistic.skyblock.items.crafting;

public class RecipeCompareResult {
    public boolean isValid;

    public boolean isValid() {
        return isValid;
    }

    public RecipeCompareResult setValid(boolean valid) {
        isValid = valid;
        return this;
    }

    public int getResultAmount() {
        return resultAmount;
    }

    public RecipeCompareResult setResultAmount(int resultAmount) {
        this.resultAmount = resultAmount;
        return this;
    }

    public int resultAmount;
}
