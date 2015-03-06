package com.talesdev.talesz;


import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.IOException;

/**
 * Abstract Config
 * Created by MoKunz on 3/5/2015.
 */
public abstract class AbstractConfig implements Config {
    private YamlConfiguration configuration;

    public AbstractConfig() {
        configuration = new YamlConfiguration();
    }

    @Override
    public String getConfigType() {
        return "yml";
    }

    @Override
    public void load() {
        beforeLoad();
        try {
            configuration.load(getConfigOwner().getDataFolder().getPath() + getConfigName() + "." + getConfigType());
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
        afterLoad();
    }

    @Override
    public void save() {
        beforeSave();
        try {
            configuration.save(getConfigOwner().getDataFolder().getPath() + getConfigName() + "." + getConfigType());
        } catch (IOException e) {
            e.printStackTrace();
        }
        afterSave();
    }

    @Override
    public void reload() {
        save();
        load();
    }

    public abstract void beforeSave();

    public abstract void afterSave();

    public abstract void beforeLoad();

    public abstract void afterLoad();

    private String getConfigLocation() {
        return getConfigOwner().getDataFolder().getPath() + getConfigName() + "." + getConfigType();
    }
}
