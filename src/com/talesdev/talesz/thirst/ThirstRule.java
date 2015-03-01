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
    private YamlConfiguration configuration;
    private HashMap<Biome, Integer> ruleList = new HashMap<>();

    public void setRule(Biome biome, int value) {
        ruleList.put(biome, value);
    }

    public int getRule(Biome biome) {
        return ruleList.get(biome);
    }

    public void loadRule(String ruleFileName) {
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
        List<String> biomeList = configuration.getStringList("ThirstRule");

    }

    public void loadRule() {
        loadRule("thirst.yml");
    }
}
