package com.talesdev.talesz.listener;

import com.talesdev.talesz.mobsystem.MobDecorator;
import com.talesdev.talesz.mobsystem.MobRuleManager;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityCombustEvent;

/**
 * CreatureSpawnListener
 * Created by MoKunz on 3/5/2015.
 */
public class CreatureSpawnListener implements Listener {
    @EventHandler
    public void onCreatureSpawn(CreatureSpawnEvent event) {
        if (MobRuleManager.isAllowedToSpawn(event.getEntity()) || event.getSpawnReason().equals(CreatureSpawnEvent.SpawnReason.EGG)) {
            MobDecorator.decorate(event.getEntity());
        } else {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onEntityCombust(EntityCombustEvent event) {
        if (event.getEntity() instanceof Zombie) {
            Zombie zombie = (Zombie) event.getEntity();
            if (zombie.hasMetadata("MineZCustomZombie")) {
                event.setCancelled(true);
            }
        }
    }
}
