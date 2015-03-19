package com.talesdev.talesz;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;

/**
 * Main plugin config file
 * Created by MoKunz on 3/5/2015.
 */
public class TalesZMainConfig {
    private static YamlConfiguration config;
    private static String fileName;

    public static void start() {
        config = new YamlConfiguration();
        // load
        load("plugins/TalesZ/config.yml");
    }

    public static void load(String name) {
        fileName = name;
        File file = new File(fileName);
        // create new file if not exist
        if (!file.exists()) {
            try {
                TalesZ.getPlugin().getLogger().log(Level.INFO, "Creating new config file.");
                file.createNewFile();
                TalesZ.getPlugin().getLogger().log(Level.INFO, "Creating new config file success!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // try loading config file
        try {
            TalesZ.getPlugin().getLogger().log(Level.INFO, "Loading config file.");
            getConfig().load(file);
        } catch (IOException | InvalidConfigurationException e) {
            TalesZ.getPlugin().getLogger().log(Level.WARNING, "Error while loading config file.");
            e.printStackTrace();
        }
        // set default
        if (!getConfig().isSet("autosave.enable")) getConfig().set("autosave.enable", true);
        if (!getConfig().isSet("autosave.interval")) getConfig().set("autosave.interval", 300);
        if (!getConfig().isSet("spawnpoint")) getConfig().set("spawnpoint", new ArrayList<>());
        if (!getConfig().isSet("chestmapping")) getConfig().set("chestmapping", new ArrayList<>());
    }

    public static void save() {
        boolean showMessage = TalesZMainConfig.getConfig().getBoolean("autosave.showmessage");
        try {
            if (showMessage) TalesZ.getPlugin().getLogger().log(Level.INFO, "Saving config to disk...");
            getConfig().save(fileName);
        } catch (IOException e) {
            TalesZ.getPlugin().getLogger().log(Level.WARNING, "Error while saving config!");
            e.printStackTrace();
        }
    }

    public static YamlConfiguration getConfig() {
        return config;
    }
}
