package com.talesdev.talesz.thirst;

import org.bukkit.Material;
import org.bukkit.block.Biome;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * Thirst Rule
 * Created by MoKunz on 3/1/2015.
 */
public class ThirstRule {
    // constant
    private final String THIRST_RULE = "ThirstRule";
    private final String FOOD_RULE = "FoodRule";
    private final String DOT = ".";
    private final String DEFAULT_BIOME = "DEFAULT";
    private final String DEFAULT_FOOD = "DefaultFoodRule";
    // config file
    private YamlConfiguration configuration;
    // Biome
    private HashMap<Biome, Integer> biomeRuleList = new HashMap<>();
    private int defaultBiomeRule = 1;
    // Food
    private int defaultFoodRule = 0;
    private HashMap<Material, Integer> foodRuleList = new HashMap<>();

    public void setBiomeRule(Biome biome, int value) {
        biomeRuleList.put(biome, value);
    }

    public void setDefaultBiomeRule(int value) {
        defaultBiomeRule = value;
    }

    public int getDefaultBiomeRule() {
        return defaultBiomeRule;
    }

    public int getBiomeRule(Biome biome) {
        if (biomeRuleList.containsKey(biome)) {
            return biomeRuleList.get(biome);
        } else {
            return defaultBiomeRule;
        }
    }

    public int getDefaultFoodRule() {
        return defaultFoodRule;
    }

    public void setDefaultFoodRule(int value) {
        defaultFoodRule = value;
    }

    public int getFoodRule(Material food) {
        if (!foodRuleList.containsKey(food)) {
            foodRuleList.put(food, readFoodRule(food));
        }
        return foodRuleList.get(food);
    }

    public void setFoodRule(Material food, int value) {
        foodRuleList.put(food, value);
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
        if (!configuration.isSet(THIRST_RULE + DOT + DEFAULT_BIOME)) {
            configuration.set(THIRST_RULE + DOT + DEFAULT_BIOME, defaultBiomeRule);
        }
        Biome[] biomeList = Biome.values();
        // begin reading
        System.out.println("[ThirstSystem] Reading thirst rule from " + ruleFileName);
        if (biomeList != null) {
            for (Biome biomeName : biomeList) {
                if (!biomeName.toString().equals(DEFAULT_BIOME)) {
                    String path = THIRST_RULE + DOT + Biome.valueOf(biomeName.toString().toUpperCase());
                    if (configuration.getInt(path) > 0) {
                        setBiomeRule(Biome.valueOf(biomeName.toString().toUpperCase()), configuration.getInt(path));
                    } else {
                        setBiomeRule(Biome.valueOf(biomeName.toString().toUpperCase()), configuration.getInt(THIRST_RULE + DOT + DEFAULT_BIOME));
                    }

                } else {
                    defaultBiomeRule = configuration.getInt(THIRST_RULE + DOT + DEFAULT_BIOME);
                }
            }
        }
        // begin food reading
        if (!configuration.isSet(DEFAULT_FOOD)) {
            configuration.set(DEFAULT_FOOD, defaultFoodRule);
        }
        // don't read until require
        System.out.println("[ThirstSystem] Completed!");
    }

    public int readFoodRule(Material material) {
        if (configuration.isSet(FOOD_RULE + DOT + material.toString())) {
            return configuration.getInt(FOOD_RULE + DOT + material.toString());
        } else {
            return defaultFoodRule;
        }
    }
    public void saveRule(String ruleFileName) {
        // save to configuration object
        for (Biome biome : biomeRuleList.keySet()) {
            configuration.set(THIRST_RULE + DOT + biome.toString(), biomeRuleList.get(biome));
        }
        configuration.set(THIRST_RULE + DOT + DEFAULT_BIOME, defaultBiomeRule);
        // save foos
        for (Material material : foodRuleList.keySet()) {
            configuration.set(FOOD_RULE + DOT + material.toString(), foodRuleList.get(material));
        }
        configuration.set(DEFAULT_FOOD, defaultFoodRule);
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
