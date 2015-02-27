package com.talesdev.talesz.itemsystem;

import com.talesdev.talesz.bleeding.Bleeding;
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

    public static void heal(Player player, double amount) {
        Bleeding.removeBleedingPlayer(player.getName());
        if (player.getHealth() + amount <= player.getMaxHealth()) {
            player.setHealth(player.getHealth() + amount);
        } else {
            player.setHealth(player.getMaxHealth());
        }

    }
}
