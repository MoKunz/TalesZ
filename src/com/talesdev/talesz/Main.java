package com.talesdev.talesz;

import com.talesdev.talesz.bleeding.BleedingCommand;
import com.talesdev.talesz.bleeding.BleedingUpdateTask;
import com.talesdev.talesz.exp.ExpCommand;
import com.talesdev.talesz.item.*;
import com.talesdev.talesz.itemsystem.TalesZItemCommand;
import com.talesdev.talesz.itemsystem.TalesZItemListener;
import com.talesdev.talesz.itemsystem.TalesZItemRegistry;
import com.talesdev.talesz.listener.*;
import com.talesdev.talesz.mob.MineZCustomZombie;
import com.talesdev.talesz.mobsystem.CustomEntityType;
import com.talesdev.talesz.mobsystem.MobDecorator;
import com.talesdev.talesz.mobsystem.MobRuleManager;
import com.talesdev.talesz.thirst.Thirst;
import com.talesdev.talesz.thirst.ThirstDamageTask;
import com.talesdev.talesz.thirst.ThirstUpdateTask;
import com.talesdev.talesz.world.BlockRegenerationTask;
import com.talesdev.talesz.world.BlockRegenerator;
import com.talesdev.talesz.world.BlockRuleManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.util.logging.Level;

/**
 * Plugin main class
 * Created by MoKunz on 17/10/2557.
 */
public class Main extends JavaPlugin {
    // plugin instance
    private static Main plugin;

    @Override
    public void onEnable() {
        // instance
        plugin = this;
        // register update task
        initTask();
        // cmd listener
        getCommand("xputil").setExecutor(new ExpCommand());
        getCommand("bleeding").setExecutor(new BleedingCommand());
        getCommand("taleszitem").setExecutor(new TalesZItemCommand());
        getCommand("talesz").setExecutor(new TalesZCommand());
        getServer().getPluginManager().registerEvents(new TalesZItemListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        getServer().getPluginManager().registerEvents(new ExpListener(), this);
        getServer().getPluginManager().registerEvents(new ItemConsumeListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerRespawnListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerDeathListener(), this);
        getServer().getPluginManager().registerEvents(new BlockBreakListener(), this);
        getServer().getPluginManager().registerEvents(new BlockPlaceListener(), this);
        getServer().getPluginManager().registerEvents(new DrinkingListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerExpChangeListener(), this);
        getServer().getPluginManager().registerEvents(new CreatureSpawnListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerDamageListener(), this);
        // init item
        initItem();
        // world system
        BlockRuleManager.start();
        BlockRuleManager.readConfigFile();
        BlockRegenerator.getDatabase().readFromFile();
        // thirst , thirst rule
        Thirst.start();
        // mob
        MobRuleManager.start();
        MobDecorator.addMobDecorator(new MineZCustomZombie());
        // nms entity override
        CustomEntityType.registerEntities();
        getLogger().info("TalesZ has been enabled!");
    }

    @Override
    public void onDisable() {
        // close all door
        IronDoorManager.forceProcessIronDoor();
        // cancel task
        cancelTask();
        // unregister nms override
        CustomEntityType.unregisterEntities();
        // save data to disk
        // *** thirst rule save method MUST BE CALLED BEFORE Thirst.saveAll() ***
        Thirst.getThirstRule().saveRule();
        try {
            Thirst.saveAll();
        } catch (IOException e) {
            getLogger().log(Level.SEVERE, "Unable to save thirst data to disk!");
            e.printStackTrace();
        }
        // save block rule
        BlockRuleManager.saveConfigFile();
        // save mob rule
        MobRuleManager.saveConfigFile();
        // disabled
        getLogger().info("TalesZ has been disabled!");
    }

    public static Main getPlugin() {
        return plugin;
    }

    private void initTask() {
        TalesZTask.setTask("thirstTask", getServer().getScheduler().runTaskTimer(this, new ThirstUpdateTask(), 0, 180));
        TalesZTask.setTask("thirstDamageTask", getServer().getScheduler().runTaskTimer(this, new ThirstDamageTask(), 0, 60));
        TalesZTask.setTask("bleedingTask", getServer().getScheduler().runTaskTimer(this, new BleedingUpdateTask(), 0, 20));
        TalesZTask.setTask("ironDoorTask", getServer().getScheduler().runTaskTimer(this, new IronDoorUpdateTask(), 0, 20));
        TalesZTask.setTask("blockRegenerationTask", getServer().getScheduler().runTaskTimer(this, new BlockRegenerationTask(), 0, 20));
    }

    private void initItem() {
        TalesZItemRegistry.registerTalesZItem(new Bandage());
        TalesZItemRegistry.registerTalesZItem(new Button());
        TalesZItemRegistry.registerTalesZItem(new HealingOintment());
        TalesZItemRegistry.registerTalesZItem(new Sugar());
        TalesZItemRegistry.registerTalesZItem(new Antibiotics());
    }

    private void cancelTask() {
        TalesZTask.cancelAll();
    }
}
