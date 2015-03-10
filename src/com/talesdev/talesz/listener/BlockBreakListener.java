package com.talesdev.talesz.listener;

import com.talesdev.talesz.world.BlockRegenerator;
import com.talesdev.talesz.world.BlockRuleManager;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

/**
 * Block breaking listener
 * Created by MoKunz on 2/27/2015.
 */
public class BlockBreakListener implements Listener {
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (event.getPlayer().getGameMode().equals(GameMode.CREATIVE)) {
            return;
        }
        Block block = event.getBlock();
        ItemStack itemStack = event.getPlayer().getItemInHand();
        // restrict block
        if (!BlockRuleManager.isBreakable(block.getType())) {
            event.setCancelled(true);
            return;
        }
        // restrict item
        if (isRestrictedBlockType(RestrictedBlock.HOE, block)) {
            if (!itemStack.getType().equals(Material.WOOD_HOE)) {
                event.setCancelled(true);
                event.getPlayer().sendMessage(ChatColor.RED + "You need a wooden hoe to break this block!");
            }
        } else if (isRestrictedBlockType(RestrictedBlock.PICKAXE, block)) {
            if (!itemStack.getType().equals(Material.STONE_PICKAXE)) {
                event.setCancelled(true);
                event.getPlayer().sendMessage(ChatColor.RED + "You need a stone pickaxe to break this block!");
            }
        } else if (isRestrictedBlockType(RestrictedBlock.SHOVEL, block)) {
            if (!itemStack.getType().equals(Material.IRON_SPADE)) {
                event.setCancelled(true);
                event.getPlayer().sendMessage(ChatColor.RED + "You need an iron shovel to break this block!");
            } else {
                if (block.getType().equals(Material.WEB)) {
                    event.getPlayer().getWorld().dropItemNaturally(block.getLocation(), new ItemStack(Material.WEB));
                }
            }
        } else if (isRestrictedBlockType(RestrictedBlock.AXE, block)) {
            if (!itemStack.getType().equals(Material.STONE_AXE)) {
                event.setCancelled(true);
                event.getPlayer().sendMessage(ChatColor.RED + "You need a stone axe to break this block!");
            }
        }
        // you have bypassed all protection
        if (BlockRegenerator.getDatabase().isRegenerable(block.getType())) {
            BlockRegenerator.breakBlock(block);
        }
    }

    private boolean isRestrictedBlockType(RestrictedBlock restrictedBlock, Block block) {
        switch (restrictedBlock) {
            case HOE:
                return block.getType().equals(Material.MELON_BLOCK) ||
                        block.getType().equals(Material.BROWN_MUSHROOM) ||
                        block.getType().equals(Material.RED_MUSHROOM) ||
                        block.getType().equals(Material.CROPS);
            case PICKAXE:
                return block.getType().equals(Material.COAL_ORE) ||
                        block.getType().equals(Material.GOLD_ORE) ||
                        block.getType().equals(Material.IRON_ORE);
            case SHOVEL:
                return block.getType().equals(Material.WEB) ||
                        block.getType().equals(Material.CACTUS);
            case AXE:
                return false;
        }
        return false;
    }

    enum RestrictedBlock {
        HOE, PICKAXE, SHOVEL, AXE
    }
}
