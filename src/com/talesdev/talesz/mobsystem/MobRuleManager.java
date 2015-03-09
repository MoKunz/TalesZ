package com.talesdev.talesz.mobsystem;

import com.talesdev.talesz.Main;
import com.talesdev.talesz.Rule;
import com.talesdev.talesz.itemsystem.TalesZItemUtil;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;


/**
 * Mob rule manager
 * Created by MoKunz on 3/5/2015.
 */
public class MobRuleManager {
    // mob rule hash map
    private static HashMap<EntityType, Rule> mobRule = new HashMap<>();
    // mob rule config file
    private static YamlConfiguration configuration = new YamlConfiguration();
    // config file location
    private static File file = new File("plugins/TalesZ/mob.yml");

    public static void setRule(EntityType type, Rule rule) {
        mobRule.put(type, rule);
    }

    public static Rule getRule(EntityType type) {
        if (!mobRule.containsKey(type)) {
            setRule(type, readMobRule(type));
        }
        return mobRule.get(type);
    }

    public static void start() {
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            configuration.load(file);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
        loadRule();
    }

    public static void loadRule() {
        // scan for valid mob rule in the config file
        for (EntityType type : EntityType.values()) {
            if (configuration.getString("Mob" + "." + type.toString()) != null) {
                if (TalesZItemUtil.isValidRuleString(configuration.getString("Mob" + "." + type.toString()).toUpperCase())) {
                    //System.out.println(Rule.getRule(configuration.getString("Mob" + "." + type.toString())).toString());
                    setRule(type, Rule.getRule(configuration.getString("Mob" + "." + type.toString())));
                }
            }
        }
    }

    public static Rule readMobRule(EntityType mob) {
        if (configuration.getString("Mob" + "." + mob.toString()) != null) {
            return Rule.getRule(configuration.getString("Mob" + "." + mob.toString()).toUpperCase());
        } else {
            return Rule.DENY;
        }
    }

    public static void saveRule() {
        // save to config object
        for (EntityType type : mobRule.keySet()) {
            configuration.set("Mob" + "." + type.toString(), getRule(type).toString());
        }
    }

    public static void saveConfigFile() {
        saveRule();
        // save to file
        try {
            configuration.save(file);
        } catch (IOException e) {
            Main.getPlugin().getLogger().log(Level.WARNING, "Error while saving config file!");
            e.printStackTrace();
        }
    }

    public static boolean isAllowedToSpawn(Entity entity) {
        return getRule(entity.getType()).getResult();
    }
}