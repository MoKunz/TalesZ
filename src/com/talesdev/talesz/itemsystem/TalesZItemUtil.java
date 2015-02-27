package com.talesdev.talesz.itemsystem;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * Item Util
 * Created by MoKunz on 2/27/2015.
 */
public class TalesZItemUtil {
    public static void removeOneItemFromPlayer(Player p, ItemStack itemStack) {
        if (itemStack.getAmount() > 1) {
            itemStack.setAmount(itemStack.getAmount() - 1);
        } else {
            p.getInventory().remove(itemStack);
        }
    }
}
