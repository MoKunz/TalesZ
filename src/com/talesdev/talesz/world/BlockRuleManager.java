package com.talesdev.talesz.world;

import com.talesdev.talesz.Main;
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
    private static BlockRule breaking = BlockRule.DENY;
    // default rule for placing.
    private static BlockRule placing = BlockRule.DENY;
    // HashMap for storing breaking rule.
    private static HashMap<Material, BlockRule> breakingRuleHashMap = new HashMap<>();
    // HashMap for storing breaking rule.
    private static HashMap<Material, BlockRule> placingRuleHashMap = new HashMap<>();
    // Config file
    private static YamlConfiguration configuration = new YamlConfiguration();
    // Config file location
    private static File file;

    public static void start() {
        file = new File("plugins/TalesZ/blockrule.yml");
        if (!file.exists()) {
            try {
                file.createNewFile();
                Main.getPlugin().getLogger().log(Level.INFO, "Creating BlockRule config file success!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            configuration.load(file);
        } catch (IOException | InvalidConfigurationException e) {
            Main.getPlugin().getLogger().log(Level.WARNING, "Error while loading BlockRule config file");
            e.printStackTrace();
        }
    }

    public static void readConfigFile() {
        if (configuration.isSet("DefaultPlacing")) {
            try {
                setDefaultPlacingRule(BlockRule.valueOf(configuration.getString("DefaultPlacing")));
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }

        }
        if (configuration.isSet("DefaultBreaking")) {
            try {
                setDefaultPlacingRule(BlockRule.valueOf(configuration.getString("DefaultPlacing")));
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
    }

    private static BlockRule readBlockRule(String sectionName, Material blockMaterial) {
        String value = configuration.getString(sectionName + "." + blockMaterial.toString());
        BlockRule rule;
        try {
            rule = BlockRule.valueOf(value);
            return rule;
        } catch (IllegalArgumentException e) {
            if (sectionName.equalsIgnoreCase("breaking")) {
                return breaking;
            } else if (sectionName.equalsIgnoreCase("placing")) {
                return placing;
            } else {
                return BlockRule.DENY;
            }
        }
    }

    private static void saveSection(String sectionName, HashMap<Material, BlockRule> section) {
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
            Main.getPlugin().getLogger().log(Level.WARNING, "Error while saving BlockRule config file");
            e.printStackTrace();
        }
    }

    public static YamlConfiguration getConfiguration() {
        return configuration;
    }

    public static void setDefaultBreakingRule(BlockRule blockRule) {
        breaking = blockRule;
    }

    public static void setDefaultPlacingRule(BlockRule blockRule) {
        placing = blockRule;
    }

    public static void setBreakingRule(Material blockMaterial, BlockRule blockRule) {
        breakingRuleHashMap.put(blockMaterial, blockRule);
    }

    public static void setPlacingRule(Material blockMaterial, BlockRule blockRule) {
        placingRuleHashMap.put(blockMaterial, blockRule);
    }

    public static BlockRule getBreakingRule(Material material) {
        if (breakingRuleHashMap.containsKey(material)) {
            return breakingRuleHashMap.get(material);
        } else {
            setBreakingRule(material, readBlockRule("Breaking", material));
            return breakingRuleHashMap.get(material);
        }
    }

    public static BlockRule getPlacingRule(Material material) {
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