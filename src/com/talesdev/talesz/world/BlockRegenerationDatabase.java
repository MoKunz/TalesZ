package com.talesdev.talesz.world;

import org.bukkit.Material;
import org.bukkit.block.Block;

import java.util.HashMap;

/**
 * Block regeneration manager
 * Created by MoKunz on 3/10/2015.
 */
public class BlockRegenerationDatabase {
    public HashMap<Material, Integer> blockMap = new HashMap<>();

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
