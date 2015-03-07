package com.talesdev.talesz.world;

import com.talesdev.talesz.Main;
import com.talesdev.talesz.Rule;
import org.bukkit.Material;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;

/**
 * Block Rule Manager
 * Created by MoKunz on 3/6/2015.
 */
public class BlockRuleManager {
    // default rule for breaking.
    private static Rule breaking = Rule.DENY;
    // default rule for placing.
    private static Rule placing = Rule.DENY;
    // HashMap for storing breaking rule.
    private static HashMap<Material, Rule> breakingRuleHashMap = new HashMap<>();
    // HashMap for storing breaking rule.
    private static HashMap<Material, Rule> placingRuleHashMap = new HashMap<>();
    // Config file
    private static YamlConfiguration configuration = new YamlConfiguration();
    // Config file location
    private static File file;

    public static void start() {
        file = new File("plugins/TalesZ/blockrule.yml");
        if (!file.exists()) {
            try {
                file.createNewFile();
                Main.getPlugin().getLogger().log(Level.INFO, "Creating Rule config file success!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            configuration.load(file);
        } catch (IOException | InvalidConfigurationException e) {
            Main.getPlugin().getLogger().log(Level.WARNING, "Error while loading Rule config file");
            e.printStackTrace();
        }
    }

    public static void readConfigFile() {
        if (configuration.isSet("DefaultPlacing")) {
            try {
                setDefaultPlacingRule(Rule.valueOf(configuration.getString("DefaultPlacing")));
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }

        }
        if (configuration.isSet("DefaultBreaking")) {
            try {
                setDefaultPlacingRule(Rule.valueOf(configuration.getString("DefaultPlacing")));
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
        // replace existing config in memory with new one
        readSection("Breaking", breakingRuleHashMap);
        readSection("Placing", placingRuleHashMap);
    }

    private static void readSection(String sectionName, HashMap<Material, Rule> rule) {
        for (Material material : rule.keySet()) {
            String blockRule = configuration.getString(sectionName + "." + material.toString());
            if (blockRule != null) {
                try {
                    rule.put(material, Rule.valueOf(blockRule));
                } catch (IllegalArgumentException ignored) {
                    Main.getPlugin().getLogger().log(Level.WARNING, "Invalid config value for Material \"" + material.toString() + "\"!");
                }
            }

        }
    }

    private static Rule readBlockRule(String sectionName, Material blockMaterial) {
        String value = configuration.getString(sectionName + "." + blockMaterial.toString());
        if (value != null) {
            Rule rule;
            try {
                rule = Rule.valueOf(value);
                return rule;
            } catch (Exception e) {
                Main.getPlugin().getLogger().log(Level.WARNING, "Error while reading config value for Material \"" + blockMaterial.toString() + "\"!");
                Main.getPlugin().getLogger().log(Level.WARNING, "Plugin will use default value instead!");
                if (sectionName.equalsIgnoreCase("breaking")) {
                    return breaking;
                } else if (sectionName.equalsIgnoreCase("placing")) {
                    return placing;
                } else {
                    return Rule.DENY;
                }
            }
        } else {
            if (sectionName.equalsIgnoreCase("breaking")) {
                return breaking;
            } else if (sectionName.equalsIgnoreCase("placing")) {
                return placing;
            } else {
                return Rule.DENY;
            }
        }
    }

    private static void saveSection(String sectionName, HashMap<Material, Rule> section) {
        if (section != null) {
            for (Material material : section.keySet()) {
                configuration.set(sectionName + "." + material.toString(), section.get(material).toString());
            }
        }
    }

    public static void saveConfigFile() {
        // save to configuration object
        // default
        configuration.set("DefaultBreaking", breaking.toString());
        configuration.set("DefaultPlacing", placing.toString());
        // section
        saveSection("Breaking", breakingRuleHashMap);
        saveSection("Placing", placingRuleHashMap);
        // save to file
        try {
            configuration.save(file);
        } catch (IOException e) {
            Main.getPlugin().getLogger().log(Level.WARNING, "Error while saving Rule config file");
            e.printStackTrace();
        }
    }

    public static YamlConfiguration getConfiguration() {
        return configuration;
    }

    public static void setDefaultBreakingRule(Rule rule) {
        breaking = rule;
    }

    public static void setDefaultPlacingRule(Rule rule) {
        placing = rule;
    }

    public static void setBreakingRule(Material blockMaterial, Rule rule) {
        breakingRuleHashMap.put(blockMaterial, rule);
    }

    public static void setPlacingRule(Material blockMaterial, Rule rule) {
        placingRuleHashMap.put(blockMaterial, rule);
    }

    public static Rule getBreakingRule(Material material) {
        if (breakingRuleHashMap.containsKey(material)) {
            return breakingRuleHashMap.get(material);
        } else {
            setBreakingRule(material, readBlockRule("Breaking", material));
            return breakingRuleHashMap.get(material);
        }
    }

    public static Rule getPlacingRule(Material material) {
        if (placingRuleHashMap.containsKey(material)) {
            return placingRuleHashMap.get(material);
        } else {
            setPlacingRule(material, readBlockRule("Placing", material));
            return placingRuleHashMap.get(material);
        }
    }

    public static boolean isBreakable(Material material) {
        return getBreakingRule(material).getResult();
    }

    public static boolean isPlaceable(Material material) {
        return getPlacingRule(material).getResult();
    }
}
