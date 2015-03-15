package com.talesdev.talesz;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * Main class
 * Created by MoKunz on 3/12/2015.
 */
public class GrapplingHookPlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new FishHookListener(), this);
    }

    @Override
    public void onDisable() {

    }
}
