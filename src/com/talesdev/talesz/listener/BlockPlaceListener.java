package com.talesdev.talesz.listener;

import com.talesdev.talesz.world.BlockRuleManager;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

/**
 * Block Place Listener
 * Created by MoKunz on 3/7/2015.
 */
public class BlockPlaceListener implements Listener {
    @EventHandler
    public void onPlaceBlock(BlockPlaceEvent event) {
        if (event.getPlayer().getGameMode().equals(GameMode.CREATIVE)) {
            return;
        }
        if (!BlockRuleManager.isPlaceable(event.getBlock().getType())) {
            event.setCancelled(true);
        }
    }
}
