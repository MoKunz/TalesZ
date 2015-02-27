package com.talesdev.talesz;

import com.sk89q.worldguard.bukkit.WGBukkit;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.talesdev.talesz.bleeding.BleedingCommand;
import com.talesdev.talesz.bleeding.BleedingUpdateTask;
import com.talesdev.talesz.exp.ExpCommand;
import com.talesdev.talesz.item.Bandage;
import com.talesdev.talesz.itemsystem.TalesZItemCommand;
import com.talesdev.talesz.itemsystem.TalesZItemFactory;
import com.talesdev.talesz.itemsystem.TalesZItemListener;
import com.talesdev.talesz.itemsystem.TalesZItemRegistry;
import com.talesdev.talesz.listener.ExpListener;
import com.talesdev.talesz.listener.ItemListener;
import com.talesdev.talesz.listener.PlayerJoinListener;
import com.talesdev.talesz.listener.PlayerRespawnListener;
import com.talesdev.talesz.thirst.*;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.util.logging.Level;

/**
 * Plugin main class
 * Created by MoKunz on 17/10/2557.
 */
public class Main extends JavaPlugin{
    // plugin instance
    private static Main plugin;
    @Override
    public void onEnable(){
        // instance
        plugin = this;
        // register update task
        initTask();
        // cmd listener
        getCommand("xputil").setExecutor(new ExpCommand());
        getCommand("bleeding").setExecutor(new BleedingCommand());
        getCommand("taleszitem").setExecutor(new TalesZItemCommand());
        getServer().getPluginManager().registerEvents(new TalesZItemListener(),this);
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        getServer().getPluginManager().registerEvents(new ExpListener(),this);
        getServer().getPluginManager().registerEvents(new ItemListener(),this);
        getServer().getPluginManager().registerEvents(new PlayerRespawnListener(), this);
        // init item
        initItem();
        // enabled
        getLogger().info("TalesZ has been enabled!");
    }
    @Override
    public void onDisable(){
        // cancel task
        cancelTask();
        // save data to disk
        try {
            Thirst.saveAll();
        } catch (IOException e) {
            getLogger().log(Level.SEVERE,"Unable to save thirst data to disk!");
            e.printStackTrace();
        }
        // disabled
        getLogger().info("TalesZ has been disabled!");
    }
    public static Main getPlugin(){
        return plugin;
    }
    public static boolean isWorldGuardLoaded(){
        Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("WorldGuard");
        // WorldGuard may not be loaded
        if (plugin == null || !(plugin instanceof WorldGuardPlugin)) {
            return false;
        }
        else{
            return true;
        }
    }
    public static WorldGuardPlugin getWorldGuard(){
        return WGBukkit.getPlugin();
    }
    private void initTask(){
        TalesZTask.setTask("thirstTask",getServer().getScheduler().runTaskTimer(this,new ThirstUpdateTask(),0,180));
        TalesZTask.setTask("thirstDamageTask",getServer().getScheduler().runTaskTimer(this,new ThirstDamageTask(),0,30));
        TalesZTask.setTask("bleedingTask",getServer().getScheduler().runTaskTimer(this,new BleedingUpdateTask(),0,20));
    }
    private void initItem(){
        TalesZItemRegistry.registerTalesZItem(new Bandage());
    }
    private void cancelTask(){
        TalesZTask.cancelAll();
    }
}
