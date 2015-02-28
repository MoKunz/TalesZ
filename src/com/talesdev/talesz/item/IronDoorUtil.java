package com.talesdev.talesz.item;

import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;

/**
 * Iron door manager
 */
public class IronDoorUtil {
    private static ArrayList<BukkitTask> task = new ArrayList<>();
    public static void open(Block block) {
        if (block.getType() == Material.IRON_DOOR_BLOCK) {
            if (block.getData() >= 8) {
                block = block.getRelative(BlockFace.DOWN);
            }
            if (block.getType() == Material.IRON_DOOR_BLOCK) {
                // open
                if (block.getData() < 4) {
                    openDoor(block);
                    IronDoorManager.addToQueue(block);
                }
            }
        }
    }

    public static void openDoor(Block block) {
        block.setData((byte) (block.getData() + 4));
        block.getWorld().playEffect(block.getLocation(), Effect.DOOR_TOGGLE, 0);
    }

    public static void closeDoor(Block block) {
        block.setData((byte) (block.getData() - 4));
        block.getWorld().playEffect(block.getLocation(), Effect.DOOR_TOGGLE, 0);
    }

    public static boolean isOpen(Block block) {
        return !(block.getData() < 4);
    }

    public static boolean isClose(Block block) {
        return block.getData() < 4;
    }
}
