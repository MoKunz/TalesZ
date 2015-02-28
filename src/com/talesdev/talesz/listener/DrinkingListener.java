package com.talesdev.talesz.listener;

import com.talesdev.talesz.itemsystem.TalesZItemUtil;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;

/**
 * Potion Drinking Listener
 * Created by MoKunz on 2/27/2015.
 */
public class DrinkingListener implements Listener {
    @EventHandler
    public void onDrink(PlayerItemConsumeEvent event) {
        ItemStack itemStack = event.getItem();
        if (itemStack.getType().equals(Material.POTION)) {
            if (itemStack.getDurability() != 0) {
                TalesZItemUtil.removeOneItemFromPlayer(event.getPlayer(), itemStack);
            }
        } else if (itemStack.getType().equals(Material.MILK_BUCKET)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onDrinkMilk(PlayerBucketEmptyEvent event) {
        if (event.getBucket().equals(Material.MILK_BUCKET)) {

        }
    }
}
