package com.talesdev.talesz.listener;

import com.talesdev.talesz.exp.ExpBarUtil;
import com.talesdev.talesz.thirst.Thirst;
import com.talesdev.talesz.thirst.ThirstDamage;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;

/**
 * Item Listener
 * Created by MoKunz on 2/14/2015.
 */
public class ItemListener implements Listener {
    @EventHandler(priority = EventPriority.HIGH)
    public void onDrink(PlayerItemConsumeEvent event){
        Material material = event.getItem().getType();
        if(material.equals(Material.POTION)) {
            if(event.getItem().hasItemMeta()){
                if(event.getItem().getItemMeta().hasDisplayName()){
                    if(ChatColor.stripColor(event.getItem().getItemMeta().getDisplayName()).equals("Synergy")){
                        Thirst.setThirst(event.getPlayer().getName(), Thirst.getThirst(event.getPlayer().getName()) - 20);
                        Thirst.updateExpBar(event.getPlayer());
                    }
                }
            }
            else{
                ThirstDamage.removeFromList(event.getPlayer().getName());
                Thirst.resetThirst(event.getPlayer().getName());
                Thirst.updateExpBar(event.getPlayer());
            }
        }
    }
}
