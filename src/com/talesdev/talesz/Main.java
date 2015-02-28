package com.talesdev.talesz;

import com.talesdev.talesz.bleeding.BleedingCommand;
import com.talesdev.talesz.bleeding.BleedingUpdateTask;
import com.talesdev.talesz.exp.ExpCommand;
import com.talesdev.talesz.item.*;
import com.talesdev.talesz.itemsystem.TalesZItemCommand;
import com.talesdev.talesz.itemsystem.TalesZItemListener;
import com.talesdev.talesz.itemsystem.TalesZItemRegistry;
import com.talesdev.talesz.listener.*;
import com.talesdev.talesz.thirst.Thirst;
import com.talesdev.talesz.thirst.ThirstDamageTask;
import com.talesdev.talesz.thirst.ThirstUpdateTask;
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
        getServer().getPluginManager().registerEvents(new PotionDrinkingListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerRespawnListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerDeathListener(), this);
        getServer().getPluginManager().registerEvents(new BlockBreakListener(), this);
        getServer().getPluginManager().registerEvents(new DrinkingListener(), this);
        // init item
        initItem();
        // enabled
        getLogger().info("TalesZ has been enabled!");
    }
    @Override
    public void onDisable(){
        // close all door
        IronDoorManager.forceProcessIronDoor();
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
    private void initTask(){
        TalesZTask.setTask("thirstTask",getServer().getScheduler().runTaskTimer(this,new ThirstUpdateTask(),0,180));
        TalesZTask.setTask("thirstDamageTask", getServer().getScheduler().runTaskTimer(this, new ThirstDamageTask(), 0, 60));
        TalesZTask.setTask("bleedingTask",getServer().getScheduler().runTaskTimer(this,new BleedingUpdateTask(),0,20));
        TalesZTask.setTask("ironDoorTask", getServer().getScheduler().runTaskTimer(this, new IronDoorUpdateTask(), 0, 20));
    }
    private void initItem(){
        TalesZItemRegistry.registerTalesZItem(new Bandage());
        TalesZItemRegistry.registerTalesZItem(new Button());
        TalesZItemRegistry.registerTalesZItem(new HealingOintment());
        TalesZItemRegistry.registerTalesZItem(new Sugar());
        TalesZItemRegistry.registerTalesZItem(new Antibiotics());
    }
    private void cancelTask(){
        TalesZTask.cancelAll();
    }
}
