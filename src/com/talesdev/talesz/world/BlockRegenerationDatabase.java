package com.talesdev.talesz.world;

import com.talesdev.talesz.Main;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;

/**
 * Block regeneration manager
 * Created by MoKunz on 3/10/2015.
 */
public class BlockRegenerationDatabase {
    private YamlConfiguration configuration = new YamlConfiguration();
    private File file = new File("plugins/TalesZ/blockregen.yml");
    private HashMap<Material, Integer> blockMap = new HashMap<>();
    private ArrayList<Material> blockMaterial = new ArrayList<>();
    private boolean cached = false;

    public void readFromFile() {
        try {
            configuration.load(file);
        } catch (IOException | InvalidConfigurationException e) {
            Main.getPlugin().getLogger().log(Level.WARNING, "Error while loading BlockRegen config file");
            e.printStackTrace();
        }
        // build cache
        if (!cached) {
            fillBlockMaterial(blockMaterial);
            cached = true;
        }
        // if not created
        if (!configuration.isSet("BlockRegen")) {
            configuration.set("BlockRegen", null);
        }
        // iterate block material list
        for (Material block : blockMaterial) {
            int value = configuration.getInt("BlockRegen" + "." + block.toString());
            if (value > 0) {
                setRegenerationTime(block, value);
            }
        }
    }

    private void fillBlockMaterial(ArrayList<Material> materialArrayList) {
        Material[] allMaterial = Material.values();
        for (Material material : allMaterial) {
            if (material.isBlock()) {
                materialArrayList.add(material);
            }
        }
    }
    public int getRegenerationTime(Material material) {
        if (blockMap.containsKey(material)) {
            return blockMap.get(material);
        }
        return -1;
    }

    public void setRegenerationTime(Material material, int time) {
        if (time > 0) {
            blockMap.put(material, time);
        }
    }

    public BlockInfo createBlockInfo(Block block) {
        if (!isRegenerable(block.getType())) {
            return null;
        }
        return new BlockInfo(block.getType(), block.getLocation(), getRegenerationTime(block.getType()));
    }

    public boolean isRegenerable(Material material) {
        return blockMap.containsKey(material);
    }
}
