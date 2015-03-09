package com.talesdev.talesz.listener;

import com.talesdev.talesz.mobsystem.MobDecorator;
import com.talesdev.talesz.mobsystem.MobRuleManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

/**
 * CreatureSpawnListener
 * Created by MoKunz on 3/5/2015.
 */
public class CreatureSpawnListener implements Listener {
    @EventHandler
    public void onCreatureSpawn(CreatureSpawnEvent event) {
        if (MobRuleManager.isAllowedToSpawn(event.getEntity())) {
            MobDecorator.decorate(event.getEntity());
        } else {
            event.setCancelled(true);
        }
    }
}
