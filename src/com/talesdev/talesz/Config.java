package com.talesdev.talesz;

import org.bukkit.plugin.Plugin;

/**
 * Config interface
 * Created by MoKunz on 3/5/2015.
 */
public interface Config {
    public Plugin getConfigOwner();

    public String getConfigName();

    public String getConfigType();

    public void load();

    public void save();

    public void reload();
}
