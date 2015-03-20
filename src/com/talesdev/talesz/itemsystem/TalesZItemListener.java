package com.talesdev.talesz.itemsystem;

import com.talesdev.talesz.TalesZ;
import com.talesdev.talesz.TalesZMainConfig;
import com.talesdev.talesz.loot.LootChest;
import com.talesdev.talesz.world.BlockInfo;
import com.talesdev.talesz.world.BlockRegenerator;
import com.talesdev.talesz.world.TalesZWorld;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;

/**
 * Listener
 * Created by MoKunz on 2/27/2015.
 */
public class TalesZItemListener implements Listener {
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        TalesZItem currentItem = TalesZItemFactory.getTalesZItemFromItemStack(event.getCurrentItem());
        // update inventory
        ItemStack activeItem = event.getCurrentItem();
        if ((activeItem == null) || (activeItem.getType() == Material.AIR)) {
            activeItem = event.getCursor();
        }
        if (currentItem instanceof MaxStackableInterface) {
            if (((MaxStackableInterface) currentItem).getMaxStackSize() > 0) {
                final Player player = Bukkit.getPlayer(event.getWhoClicked().getName());
                Bukkit.getScheduler().runTaskLater(TalesZ.getPlugin(), new Runnable() {
                    @Override
                    public void run() {
                        player.updateInventory();
                    }
                }, 1L);
            }
        }
        // pass event to item class
        if (currentItem != null) {
            currentItem.handleEvent(event);
        }
    }

    @EventHandler
    public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
        TalesZItem item = TalesZItemFactory.getTalesZItemFromItemStack(event.getPlayer().getItemInHand());
        if (item != null) {
            item.handleEvent(event);
        }
    }

    @EventHandler
    public void OnPlayerInteract(PlayerInteractEvent event) {
        if (event.getClickedBlock() != null) {
            MaterialComparator comparator = new MaterialComparator(material -> material.equals(Material.BED_BLOCK) || material.equals(Material.ENCHANTMENT_TABLE));
            if (comparator.containThisMaterial(event.getClickedBlock().getType())) {
                event.setCancelled(true);
            }
        }
        TalesZItem item = TalesZItemFactory.getTalesZItemFromItemStack(event.getItem());
        if (item != null) {
            item.handleEvent(event);
        }
        // right clicked block
        if ((event.getClickedBlock() != null) && event.getAction().equals(Action.RIGHT_CLICK_BLOCK) && (!event.getPlayer().getGameMode().equals(GameMode.CREATIVE))) {
            if (event.getClickedBlock().getType().equals(Material.CHEST)) {
                LootChest lootChest = TalesZWorld.getLootChest(event.getClickedBlock());
                if (lootChest != null) {
                    lootChest.fillChest(event.getClickedBlock());
                }
            } else if (event.getClickedBlock().getType().equals(Material.DIRT) ||
                    event.getClickedBlock().getType().equals(Material.GRASS) ||
                    event.getClickedBlock().getType().equals(Material.MYCEL)) {
                if (event.getPlayer().getItemInHand().getType().equals(Material.WOOD_HOE)) {
                    event.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        if (event.getPlayer().getGameMode().equals(GameMode.CREATIVE)) {
            return;
        }
        if (event.getInventory().getType().equals(InventoryType.CHEST)) {
            LootChest lootChest = TalesZWorld.getLootChest(event.getInventory());
            if (lootChest != null && event.getInventory().getHolder() instanceof Chest) {
                event.getInventory().clear();
                Chest chest = (Chest) event.getInventory().getHolder();
                org.bukkit.material.Chest chestData = (org.bukkit.material.Chest) chest.getData();
                BlockInfo blockInfo = BlockRegenerator.getDatabase().createBlockInfo(chest.getBlock(), TalesZMainConfig.getConfig().getInt("chest.regentime"));
                blockInfo.setBlock(Material.CHEST);
                blockInfo.setUserData("chestType", lootChest.getTypeName());
                blockInfo.setUserData("chestFace", chestData.getFacing());
                chest.getBlock().setType(Material.AIR);
                chest.getBlock().getWorld().playEffect(chest.getBlock().getLocation(), Effect.STEP_SOUND, Material.CHEST);
                BlockRegenerator.breakBlock(blockInfo);
            }
        }
    }
    @EventHandler
    public void onPlayerConsume(PlayerItemConsumeEvent event) {
        TalesZItem item = TalesZItemFactory.getTalesZItemFromItemStack(event.getItem());
        if (item != null) {
            item.handleEvent(event);
        }
    }

    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent event) {
        TalesZItem item = TalesZItemFactory.getTalesZItemFromItemStack(event.getItemDrop().getItemStack());
        if (item != null) {
            item.handleEvent(event);
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        TalesZItem item = TalesZItemFactory.getTalesZItemFromItemStack(event.getPlayer().getItemInHand());
        if (item != null) {
            if (item instanceof TalesZToolItem) {
                TalesZToolItem toolItem = (TalesZToolItem) item;
                toolItem.handleEvent(event);
            }
        }
    }

    @EventHandler
    public void onDamageEntity(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) {
            Player p = (Player) event.getDamager();
            TalesZItem item = TalesZItemFactory.getTalesZItemFromItemStack(p.getItemInHand());
            if (item != null) {
                item.handleEvent(event);
            }
        }
    }
}
