package com.talesdev.talesz.loot;

import com.talesdev.talesz.ReflectionUtils;
import com.talesdev.talesz.event.TalesZBlockRegenerateEvent;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Chest;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;

/**
 * Chest regeneration listener
 * Created by MoKunz on 3/18/2015.
 */
public class LootingSystemListener implements Listener {
    @EventHandler
    public void onChestRegen(TalesZBlockRegenerateEvent event) {
        if (event.getBlockInfo().hasUserData("chestType")) {
            // chest type
            String chestType = (String) event.getBlockInfo().getUserData("chestType");
            BlockFace blockFace = BlockFace.NORTH;
            if (event.getBlockInfo().getUserData("chestFace") instanceof BlockFace)
                blockFace = (BlockFace) event.getBlockInfo().getUserData("chestFace");
            Block chestBlock = event.getBlock();
            // set material to chest
            chestBlock.setType(Material.CHEST);
            // get chest
            Chest chest = (Chest) chestBlock.getState();
            // set direction
            byte dir = 0x2;
            if (blockFace.equals(BlockFace.NORTH)) dir = 0x2;
            if (blockFace.equals(BlockFace.SOUTH)) dir = 0x3;
            if (blockFace.equals(BlockFace.WEST)) dir = 0x4;
            if (blockFace.equals(BlockFace.EAST)) dir = 0x5;
            event.getBlock().setData(dir);
            // get inv
            Inventory inventory = chest.getInventory();
            try {
                // get field
                ReflectionUtils.RefClass craftInventoryClass = ReflectionUtils.getRefClass(inventory.getClass());
                ReflectionUtils.RefField nmsInventoryField = craftInventoryClass.getField("inventory");
                // TileEntityChest
                Object tileEntityChest = nmsInventoryField.of(inventory).get();
                ReflectionUtils.RefClass tileEntityChestClass = ReflectionUtils.getRefClass(tileEntityChest.getClass());
                ReflectionUtils.RefMethod setNameMethod = tileEntityChestClass.getMethod("a", String.class);
                setNameMethod.of(tileEntityChest).call(chestType);
            } catch (Exception ignored) {
            }

            event.setCancelled(true);
        }
    }
}
