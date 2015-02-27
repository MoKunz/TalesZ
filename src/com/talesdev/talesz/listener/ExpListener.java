package com.talesdev.talesz.listener;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.ItemSpawnEvent;

/**
 * Exp Listener
 * Created by MoKunz on 2/14/2015.
 */
public class ExpListener implements Listener{
    @EventHandler(priority = EventPriority.LOWEST)
    public void onXp(ItemSpawnEvent event){
        if(event.getEntityType().equals(EntityType.EXPERIENCE_ORB)){
            event.setCancelled(true);
        }
    }
    @EventHandler(priority = EventPriority.LOWEST)
    public void onDeath(EntityDeathEvent event){
        event.setDroppedExp(0);
    }
}
