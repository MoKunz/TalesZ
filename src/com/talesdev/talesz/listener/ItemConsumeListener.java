package com.talesdev.talesz.listener;

import com.talesdev.talesz.Main;
import com.talesdev.talesz.item.FoodComparator;
import com.talesdev.talesz.itemsystem.MaterialComparator;
import com.talesdev.talesz.itemsystem.TalesZItemUtil;
import com.talesdev.talesz.thirst.Thirst;
import com.talesdev.talesz.thirst.ThirstDamage;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;

/**
 * Item Listener
 * Created by MoKunz on 2/14/2015.
 */
public class ItemConsumeListener implements Listener {
    @EventHandler(priority = EventPriority.LOWEST)
    public void onDrink(PlayerItemConsumeEvent event) {
        Material material = event.getItem().getType();
        if (material.equals(Material.POTION)) {
            if (event.getItem().getDurability() == 0) {
                Bukkit.getScheduler().runTask(Main.getPlugin(), new Runnable() {
                    @Override
                    public void run() {
                        ThirstDamage.removeFromList(event.getPlayer().getName());
                        Thirst.resetThirst(event.getPlayer().getName());
                        Thirst.updateExpBar(event.getPlayer());
                    }
                });
            }
        }
        MaterialComparator comparator = new MaterialComparator(new FoodComparator());
        // is food?
        if (comparator.containThisMaterial(material)) {
            // thirst food
            Thirst.setThirst(
                    event.getPlayer().getName(), Thirst.getThirst(event.getPlayer().getName()) -
                            Thirst.getThirstRule().getFoodRule(material)
            );
            Thirst.updateExpBar(event.getPlayer());
            // except rotten flesh and spider eye
            if (!(material.equals(Material.ROTTEN_FLESH) || material.equals(Material.SPIDER_EYE))) {
                TalesZItemUtil.heal(event.getPlayer(), 1.0, false);
            }
        }
    }
}
