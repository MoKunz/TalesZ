package com.talesdev.talesz.world;

import com.talesdev.talesz.TalesZ;
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
            TalesZ.getPlugin().getLogger().log(Level.WARNING, "Error while loading BlockRegen config file");
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

    /**
     * Create BlockInfo object
     *
     * @param block The block to be regenerated
     * @return BlockInfo object contains information about the block to be regenerated or null if given material isn't regenerable
     */
    public BlockInfo createBlockInfo(Block block) {
        if (!isRegenerable(block.getType())) {
            return null;
        }
        return new BlockInfo(block.getType(), block.getLocation(), getRegenerationTime(block.getType()));
    }

    /**
     * Create BlockInfo object for material that isn't regenerable
     *
     * @param block     The block to be regenerated
     * @param regenTime The time used to regen this block
     * @return BlockInfo object contains information about the block to be regenerated
     */
    public BlockInfo createBlockInfo(Block block, int regenTime) {
        return new BlockInfo(block.getType(), block.getLocation(), regenTime);
    }

    /**
     * Check if this material is regenerable
     * @param material
     * @return Return true if this material is regenerable
     */
    public boolean isRegenerable(Material material) {
        return blockMap.containsKey(material);
    }
}
