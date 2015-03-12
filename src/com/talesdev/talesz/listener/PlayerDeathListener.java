package com.talesdev.talesz.listener;

import com.talesdev.talesz.PlayerTaskManager;
import com.talesdev.talesz.TalesZ;
import com.talesdev.talesz.bleeding.Bleeding;
import com.talesdev.talesz.thirst.Thirst;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

/**
 * Player Death Event
 * Created by MoKunz on 2/25/2015.
 */
public class PlayerDeathListener implements Listener {
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onDeath(PlayerDeathEvent event) {
        PlayerTaskManager.getPlayerTask(event.getEntity()).cancel("Sugar");
        Bukkit.getScheduler().runTask(TalesZ.getPlugin(), () -> {
            Bleeding.removeBleedingPlayer(event.getEntity().getName());
            Thirst.resetThirst(event.getEntity().getName());
            Thirst.updateExpBar(event.getEntity());
        });
    }
}
