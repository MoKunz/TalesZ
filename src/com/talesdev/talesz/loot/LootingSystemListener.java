package com.talesdev.talesz.loot;

import com.talesdev.talesz.event.TalesZBlockInfoCreatedEvent;
import com.talesdev.talesz.event.TalesZBlockRegenerateEvent;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

/**
 * Chest regeneration listener
 * Created by MoKunz on 3/18/2015.
 */
public class LootingSystemListener implements Listener {
    @EventHandler
    public void onChestRegen(TalesZBlockRegenerateEvent event) {
        System.out.println("Block regenerated!");
        if (event.getBlock().getType().equals(Material.CHEST)) {
            Chest chest = (Chest) event.getBlock().getState();
        }
    }

    @EventHandler
    public void onBlockInfoCreated(TalesZBlockInfoCreatedEvent event) {
        System.out.println("Block info created!");
    }
}
