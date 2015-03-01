package com.talesdev.talesz.thirst;

import org.bukkit.block.Biome;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * Thirst Rule
 * Created by MoKunz on 3/1/2015.
 */
public class ThirstRule {
    private final String THIRST_RULE = "ThirstRule";
    private final String DOT = ".";
    private final String DEFAULT_BIOME = "DEFAULT";
    private YamlConfiguration configuration;
    private HashMap<Biome, Integer> ruleList = new HashMap<>();
    private int defaultRule = 9;

    public void setRule(Biome biome, int value) {
        ruleList.put(biome, value);
    }

    public void setDefaultRule(int value) {
        defaultRule = value;
    }

    public int getRule(Biome biome) {
        if (ruleList.containsKey(biome)) {
            return ruleList.get(biome);
        } else {
            return defaultRule;
        }
    }

    public void loadRule(String ruleFileName) {
        // load file
        System.out.println("[ThirstSystem] Loading thirst rule from " + ruleFileName);
        configuration = new YamlConfiguration();
        try {
            File file = new File("plugins/TalesZ/" + ruleFileName);
            if (!file.exists()) {
                file.createNewFile();
            }
            configuration.load(file);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
        // set default if not exist
        if (!configuration.isSet(THIRST_RULE)) {
            configuration.set(THIRST_RULE, null);
        }
        if (!configuration.isSet(THIRST_RULE + DOT + DEFAULT_BIOME)) {
            configuration.set(THIRST_RULE + DOT + DEFAULT_BIOME, 9);
        }
        List<String> biomeList = configuration.getStringList(THIRST_RULE);
        // begin reading
        System.out.println("[ThirstSystem] Reading thirst rule from " + ruleFileName);
        if (biomeList != null) {
            for (String biomeName : biomeList) {
                if (!biomeName.equals(DEFAULT_BIOME)) {
                    setRule(Biome.valueOf(biomeName.toUpperCase()), configuration.getInt(THIRST_RULE + DOT + Biome.valueOf(biomeName.toUpperCase())));
                } else {
                    defaultRule = configuration.getInt(THIRST_RULE + DOT + DEFAULT_BIOME);
                }
            }
        }
        System.out.println("[ThirstSystem] Completed!");
    }

    public void saveRule(String ruleFileName) {
        // save to configuration object
        for (Biome biome : ruleList.keySet()) {
            configuration.set(THIRST_RULE + DOT + biome.toString(), ruleList.get(biome));
        }
        configuration.set(THIRST_RULE + DOT + DEFAULT_BIOME, defaultRule);
        // save to file
        try {
            configuration.save(new File("plugins/TalesZ/" + ruleFileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadRule() {
        loadRule("thirst.yml");
    }

    public void saveRule() {
        saveRule("thirst.yml");
    }
}
