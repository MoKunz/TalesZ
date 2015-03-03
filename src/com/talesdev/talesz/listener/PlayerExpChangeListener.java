package com.talesdev.talesz.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerExpChangeEvent;

/**
 * Exp changing listener
 * Created by MoKunz on 3/3/2015.
 */
public class PlayerExpChangeListener implements Listener {
    @EventHandler(priority = EventPriority.LOWEST)
    public void onExpChange(PlayerExpChangeEvent expChangeEvent) {
        expChangeEvent.setAmount(0);
    }
}
