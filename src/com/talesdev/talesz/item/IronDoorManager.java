package com.talesdev.talesz.item;

import org.bukkit.block.Block;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Iron Door Manager
 * Created by MoKunz on 2/28/2015.
 */
public class IronDoorManager {
    public static HashMap<Block, Integer> ironDoorHashMap = new HashMap<>();

    /**
     * Add an iron door to the list
     *
     * @param block A bottom iron door block
     */
    public static void addToQueue(Block block) {
        ironDoorHashMap.put(block, 60);
    }

    /**
     * Must be called every 20 ticks / 1 seconds
     */
    public static void updateTime() {
        if (ironDoorHashMap.size() < 1) return;
        for (Block block : ironDoorHashMap.keySet()) {
            ironDoorHashMap.put(block, ironDoorHashMap.get(block) - 1);
        }
    }

    /**
     * Process iron door closing queue
     */
    public static void processIronDoor() {
        if (ironDoorHashMap.size() < 1) return;
        for (Iterator<Map.Entry<Block, Integer>> it = ironDoorHashMap.entrySet().iterator(); it.hasNext(); ) {
            Map.Entry<Block, Integer> entry = it.next();
            if (entry.getValue() <= 0) {
                IronDoorUtil.closeDoor(entry.getKey());
                it.remove();
            }
        }
    }

    public static void forceProcessIronDoor() {
        if (ironDoorHashMap.size() < 1) return;
        for (Iterator<Map.Entry<Block, Integer>> it = ironDoorHashMap.entrySet().iterator(); it.hasNext(); ) {
            Map.Entry<Block, Integer> entry = it.next();
            if (entry.getValue() <= 0) {
                IronDoorUtil.closeDoor(entry.getKey());
                it.remove();
            }
        }
    }
}
