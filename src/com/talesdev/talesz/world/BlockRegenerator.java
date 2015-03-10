package com.talesdev.talesz.world;

import org.bukkit.World;
import org.bukkit.block.Block;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Block regenerator
 * Created by MoKunz on 3/10/2015.
 */
public class BlockRegenerator {
    private static BlockRegenerationDatabase database = new BlockRegenerationDatabase();
    private static ArrayList<BlockInfo> blockList = new ArrayList<>();

    public static void update() {
        if (blockList.size() > 0) {
            for (Iterator<BlockInfo> iterator = blockList.iterator(); iterator.hasNext(); ) {
                BlockInfo blockInfo = iterator.next();
                // update time
                if (blockInfo.getTime() - 1 > 0) {
                    blockInfo.setTime(blockInfo.getTime() - 1);
                } else {
                    blockInfo.setTime(0);
                }
                // check time
                if (blockInfo.getTime() <= 0) {
                    World world = blockInfo.getBlockLocation().getWorld();
                    world.getBlockAt(blockInfo.getBlockLocation()).setType(blockInfo.getBlock());
                    iterator.remove();
                }
            }
        }
    }

    public static void breakBlock(Block block) {
        blockList.add(getDatabase().createBlockInfo(block));
    }

    public static BlockRegenerationDatabase getDatabase() {
        return database;
    }
}
