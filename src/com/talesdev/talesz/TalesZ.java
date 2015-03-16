package com.talesdev.talesz;

import com.talesdev.talesz.bleeding.BleedingCommand;
import com.talesdev.talesz.bleeding.BleedingUpdateTask;
import com.talesdev.talesz.exp.ExpCommand;
import com.talesdev.talesz.item.*;
import com.talesdev.talesz.itemsystem.*;
import com.talesdev.talesz.listener.*;
import com.talesdev.talesz.loot.CivilianCommonChest;
import com.talesdev.talesz.mob.MineZCustomZombie;
import com.talesdev.talesz.mobsystem.CustomEntityType;
import com.talesdev.talesz.mobsystem.MobDecorator;
import com.talesdev.talesz.mobsystem.MobRuleManager;
import com.talesdev.talesz.thirst.Thirst;
import com.talesdev.talesz.thirst.ThirstDamageTask;
import com.talesdev.talesz.thirst.ThirstUpdateTask;
import com.talesdev.talesz.world.*;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.util.logging.Level;

/**
 * Plugin main class
 * Created by MoKunz on 17/10/2557.
 */
public class TalesZ extends JavaPlugin {
    // plugin instance
    private static TalesZ plugin;

    @Override
    public void onEnable() {
        // instance
        plugin = this;
        // main config
        TalesZMainConfig.start();
        // register update task
        initTask();
        // cmd listener
        getCommand("xputil").setExecutor(new ExpCommand());
        getCommand("bleeding").setExecutor(new BleedingCommand());
        getCommand("taleszitem").setExecutor(new TalesZItemCommand());
        getCommand("talesz").setExecutor(new TalesZCommand());
        getCommand("taleszspawn").setExecutor(new TalesZSpawnCommand());
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
        // post init item
        setTalesZItemMaxStack();
        // world system
        TalesZWorld.init(new SpawnPointManager(Bukkit.getServer().getWorlds().get(0)));
        TalesZWorld.addLootChest(new CivilianCommonChest());
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
        // last
        TalesZMainConfig.save();
        // disabled
        getLogger().info("TalesZ has been disabled!");
    }

    public static TalesZ getPlugin() {
        return plugin;
    }

    private void initTask() {
        TalesZTask.setTask("thirstTask", getServer().getScheduler().runTaskTimer(this, new ThirstUpdateTask(), 0, 20));
        TalesZTask.setTask("thirstDamageTask", getServer().getScheduler().runTaskTimer(this, new ThirstDamageTask(), 0, 60));
        TalesZTask.setTask("bleedingTask", getServer().getScheduler().runTaskTimer(this, new BleedingUpdateTask(), 0, 20));
        TalesZTask.setTask("ironDoorTask", getServer().getScheduler().runTaskTimer(this, new IronDoorUpdateTask(), 0, 20));
        TalesZTask.setTask("blockRegenerationTask", getServer().getScheduler().runTaskTimer(this, new BlockRegenerationTask(), 0, 20));
        if (TalesZMainConfig.getConfig().getBoolean("autosave.enable")) {
            int interval = TalesZMainConfig.getConfig().getInt("autosave.interval");
            if (interval > 30) {
                TalesZTask.setTask("autoSaveTask", getServer().getScheduler().runTaskTimer(this, new AutoSaveTask(), 0, interval * 20));
            } else {
                getLogger().log(Level.WARNING, "Auto save interval is too small. Disabling it.");
            }
        }
        TalesZTask.setTask("grapplingHookTask", getServer().getScheduler().runTaskTimer(this, GrapplingHookDamageManager::update, 0, 20));
    }

    private void initItem() {
        TalesZItemRegistry.registerTalesZItem(new Bandage());
        TalesZItemRegistry.registerTalesZItem(new Button());
        TalesZItemRegistry.registerTalesZItem(new HealingOintment());
        TalesZItemRegistry.registerTalesZItem(new Sugar());
        TalesZItemRegistry.registerTalesZItem(new Antibiotics());
        TalesZItemRegistry.registerTalesZItem(new Poultice());
        TalesZItemRegistry.registerTalesZItem(new GrapplingHook());
        TalesZItemRegistry.registerTalesZItem(new CocoaBean());
        TalesZItemUtil.setMaxStackSize(Material.APPLE,1);
        TalesZItemUtil.setMaxStackSize(Material.BAKED_POTATO,1);
        TalesZItemUtil.setMaxStackSize(Material.BREAD,3);
        TalesZItemUtil.setMaxStackSize(Material.CARROT,1);
        TalesZItemUtil.setMaxStackSize(Material.GRILLED_PORK,1);
        TalesZItemUtil.setMaxStackSize(Material.PORK,1);
        TalesZItemUtil.setMaxStackSize(Material.COOKIE,8);
        TalesZItemUtil.setMaxStackSize(Material.GOLDEN_APPLE,1);
        TalesZItemUtil.setMaxStackSize(Material.GOLDEN_CARROT,1);
        TalesZItemUtil.setMaxStackSize(Material.MELON,4);
        TalesZItemUtil.setMaxStackSize(Material.RAW_BEEF,1);
        TalesZItemUtil.setMaxStackSize(Material.COOKED_BEEF,1);
        TalesZItemUtil.setMaxStackSize(Material.RAW_FISH,1);
        TalesZItemUtil.setMaxStackSize(Material.COOKED_FISH,1);
        TalesZItemUtil.setMaxStackSize(Material.ROTTEN_FLESH,1);
        TalesZItemUtil.setMaxStackSize(Material.SUGAR,3);
        TalesZItemUtil.setMaxStackSize(Material.BOWL,1);
        TalesZItemUtil.setMaxStackSize(Material.STONE_BUTTON,3);
        TalesZItemUtil.setMaxStackSize(Material.GLASS_BOTTLE,1);
        TalesZItemUtil.setMaxStackSize(Material.SULPHUR,1);
        TalesZItemUtil.setMaxStackSize(Material.WHEAT,3);

    }

    private void setTalesZItemMaxStack() {
        for (TalesZItem item : TalesZItemRegistry.getAllTalesZItem()) {
            if (item instanceof MaxStackableInterface) {
                TalesZItemUtil.setMaxStackSize(item.getType(), ((MaxStackableInterface) item).getMaxStackSize());
            }
        }
    }

    private void cancelTask() {
        TalesZTask.cancelAll();
    }
}
