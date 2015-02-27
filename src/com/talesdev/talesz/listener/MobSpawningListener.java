package com.talesdev.talesz.listener;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

/**
 * Mob Spawning Listener
 * Created by MoKunz on 2/26/2015.
 */
public class MobSpawningListener implements Listener {
    @EventHandler
    public void onMobSpawn(CreatureSpawnEvent event){
        LivingEntity entity = event.getEntity();
    }
}
