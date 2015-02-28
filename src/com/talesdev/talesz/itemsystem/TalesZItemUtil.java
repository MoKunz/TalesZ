package com.talesdev.talesz.itemsystem;

import com.talesdev.talesz.bleeding.Bleeding;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
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

    public static boolean isActionRightClick(Action action) {
        return action.equals(Action.RIGHT_CLICK_AIR) || action.equals(Action.RIGHT_CLICK_BLOCK);
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
