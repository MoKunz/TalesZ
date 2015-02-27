package com.talesdev.talesz.config;

import com.talesdev.talesz.TalesZPlayerConfig;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

/**
 * Config File System
 * Created by MoKunz on 17/10/2557.
 */
public class ConfigFile {
    protected static File file;

    protected static String configFilePath = "plugins/TalesZ/";
    protected static YamlConfiguration configuration;
    static{
        // create dir
        File dir = new File(configFilePath);
        if(!dir.exists()){
            dir.mkdirs();
        }
        // create file
        file = new File(configFilePath+"config.yml");
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // create yaml instance
        reload();
    }
    // start
    public static void create(String playerName){

    }
    public static TalesZPlayerConfig load(String playerName){
        TalesZPlayerConfig config = new TalesZPlayerConfig(playerName);
        return config;
    }
    public static void exist(String playerName){

    }
    public static void save(TalesZPlayerConfig playerConfig){
        // save
    }
    public static void remove(String playerName){
        // remove
    }
    public static void reload(){
        // reload
        configuration = new YamlConfiguration();
        try {
            configuration.load(file);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }
}
