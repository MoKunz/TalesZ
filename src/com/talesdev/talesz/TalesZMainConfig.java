package com.talesdev.talesz;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
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
                Main.getPlugin().getLogger().log(Level.INFO, "Creating new config file.");
                file.createNewFile();
                Main.getPlugin().getLogger().log(Level.INFO, "Creating new config file success!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // try loading config file
        try {
            Main.getPlugin().getLogger().log(Level.INFO, "Loading config file.");
            getConfig().load(file);
        } catch (IOException | InvalidConfigurationException e) {
            Main.getPlugin().getLogger().log(Level.WARNING, "Error while loading config file.");
            e.printStackTrace();
        }
    }

    public static void save() {
        try {
            Main.getPlugin().getLogger().log(Level.INFO, "Saving config to disk...");
            getConfig().save(fileName);
        } catch (IOException e) {
            Main.getPlugin().getLogger().log(Level.WARNING, "Error while saving config!");
            e.printStackTrace();
        }
    }

    public static YamlConfiguration getConfig() {
        return config;
    }
}
