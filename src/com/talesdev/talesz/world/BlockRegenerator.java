package com.talesdev.talesz.world;

import com.talesdev.talesz.event.TalesZBlockInfoCreatedEvent;
import com.talesdev.talesz.event.TalesZBlockRegenerateEvent;
import org.bukkit.Bukkit;
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
                // update regenerationTime
                if (blockInfo.getTime() - 1 > 0) {
                    blockInfo.setTime(blockInfo.getTime() - 1);
                } else {
                    blockInfo.setTime(0);
                }
                // check regenerationTime
                if (blockInfo.getTime() <= 0) {
                    World world = blockInfo.getBlockLocation().getWorld();
                    Block block = world.getBlockAt(blockInfo.getBlockLocation());
                    // event
                    TalesZBlockRegenerateEvent event = new TalesZBlockRegenerateEvent(block, blockInfo);
                    Bukkit.getPluginManager().callEvent(event);
                    if (!event.isCancelled()) {
                        block.setType(event.getBlockInfo().getBlock());
                    }
                    iterator.remove();
                }
            }
        }
    }

    public static void breakBlock(Block block) {
        BlockInfo blockInfo = getDatabase().createBlockInfo(block);
        // event
        TalesZBlockInfoCreatedEvent event = new TalesZBlockInfoCreatedEvent(blockInfo);
        Bukkit.getPluginManager().callEvent(event);
        blockList.add(event.getBlockInfo());
    }

    public static void breakBlock(BlockInfo blockInfo) {
        TalesZBlockInfoCreatedEvent event = new TalesZBlockInfoCreatedEvent(blockInfo);
        Bukkit.getPluginManager().callEvent(event);
        blockList.add(event.getBlockInfo());
    }
    public static BlockRegenerationDatabase getDatabase() {
        return database;
    }
}
