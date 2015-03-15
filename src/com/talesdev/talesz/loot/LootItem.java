package com.talesdev.talesz.loot;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

/**
 * Loot item
 * Created by MoKunz on 3/15/2015.
 */
public class LootItem {
    private ItemStack itemStack;
    private double probability = 0;

    public LootItem(ItemStack itemStack, double prob) {
        setItem(itemStack);
        setProbability(prob);
    }

    public ItemStack getItem() {
        return itemStack;
    }

    public void setItem(ItemStack itemStack) {
        if (itemStack != null) {
            this.itemStack = itemStack;
        } else {
            this.itemStack = new ItemStack(Material.AIR);
        }
    }

    public double getProbability() {
        return probability;
    }

    public void setProbability(double prob) {
        if (prob > 0) {
            this.probability = prob;
        }
    }
}
