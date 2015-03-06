package com.talesdev.talesz.listener;

import com.talesdev.talesz.Main;
import com.talesdev.talesz.PlayerTaskManager;
import com.talesdev.talesz.thirst.Thirst;
import com.talesdev.talesz.thirst.ThirstDamage;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.io.IOException;
import java.util.logging.Level;

/**
 * Player join listener
 * Created by MoKunz on 2/14/2015.
 */
public class PlayerJoinListener implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onJoin(PlayerJoinEvent event){
        event.getPlayer().setGameMode(GameMode.SURVIVAL);
        Thirst.registerNewPlayer(event.getPlayer().getName());
        if (Thirst.thirstDataExist(event.getPlayer().getName())) {
            Thirst.loadData(event.getPlayer().getName());
        }
        Thirst.updateExpBar(event.getPlayer());
    }
    @EventHandler
    public void onDisconnect(PlayerQuitEvent event){
        // cancel queued effect
        PlayerTaskManager.getPlayerTask(event.getPlayer()).cancel("Sugar");
        // save to disk
        try {
            Thirst.saveDataToDisk(event.getPlayer().getName());
        } catch (IOException e) {
            Main.getPlugin().getLogger().log(Level.WARNING,"Unable to save data to disk!");
            e.printStackTrace();
        }
        ThirstDamage.removeFromList(event.getPlayer().getName());
        Thirst.unregisterPlayer(event.getPlayer().getName());
    }
}
