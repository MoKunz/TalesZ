package com.talesdev.talesz.loot;

import com.talesdev.talesz.RandomUtil;
import org.bukkit.inventory.ItemStack;

/**
 * Old loot item
 * Created by MoKunz on 3/19/2015.
 */
public class OldLootItem extends LootItem {
    private int durabilityRange = 0;

    public OldLootItem(ItemStack itemStack, double prob) {
        super(itemStack, prob);
    }

    public OldLootItem(ItemStack itemStack, double prob, int durabilityRange) {
        super(itemStack, prob);
        setDurabilityRange(durabilityRange);
    }

    @Override
    public ItemStack beforeGiveItem(ItemStack itemStack) {
        itemStack.setDurability((short) RandomUtil.randomRange(getDurabilityRange()));
        return itemStack;
    }

    public int getDurabilityRange() {
        return durabilityRange;
    }

    public void setDurabilityRange(int range) {
        if (range >= Short.MIN_VALUE && range <= Short.MAX_VALUE) {
            this.durabilityRange = range;
        }
    }
}
