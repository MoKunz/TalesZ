package com.talesdev.talesz;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.util.BlockIterator;

/**
 * Player util
 * Created by MoKunz on 2/27/2015.
 */
public class PlayerUtil {
    public static boolean isValidPlayer(String playerName) {
        for (Player p : Bukkit.getServer().getOnlinePlayers()) {
            if (p.getName().equalsIgnoreCase(playerName)) {
                return true;
            }
        }
        return false;
    }

    public static Block getTargetBlock(Player player, int range) {
        BlockIterator bi = new BlockIterator(player, range);
        Block lastBlock = bi.next();
        while (bi.hasNext()) {
            lastBlock = bi.next();
            if (lastBlock.getType() == Material.AIR)
                continue;
            break;
        }
        return lastBlock;
    }

    public static Block getTargetBlock(Player player) {
        return getTargetBlock(player, 6);
    }
}
