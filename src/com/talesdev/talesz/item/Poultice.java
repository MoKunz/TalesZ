package com.talesdev.talesz.item;

import com.talesdev.talesz.bleeding.Bleeding;
import com.talesdev.talesz.itemsystem.MaxStackableInterface;
import com.talesdev.talesz.itemsystem.TalesZItemUtil;
import com.talesdev.talesz.itemsystem.TalesZToolItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;

/**
 * Poultice
 * Created by sonSunnoi ... Extend form Bandage on 3/10/2015.
 */
public class Poultice implements TalesZToolItem, MaxStackableInterface {
    @Override
    public void handleEvent(BlockBreakEvent event) {

    }

    @Override
    public String getName() {
        return "Poultice";
    }

    @Override
    public Material getType() {
        return Material.INK_SACK;
    }

    @Override
    public int getAmount() {
        return 1;
    }

    @Override
    public short getDurability() {
        return 7;
    }

    @Override
    public ItemMeta configItemMeta(ItemMeta itemMeta) {
        itemMeta.setDisplayName(ChatColor.WHITE + "Poultice");
        return itemMeta;
    }

    @Override
    public MaterialData configMaterialData(MaterialData materialData) {
        return null;
    }

    @Override
    public void handleEvent(InventoryClickEvent event) {

    }

    @Override
    public void handleEvent(PlayerInteractEntityEvent event) {

    }

    @Override
    public void handleEvent(PlayerInteractEvent event) {
        boolean isRightClickable;
        if (event.getClickedBlock() != null) {
            isRightClickable = TalesZItemUtil.getRightClickableComparator().notContainThisMaterial(event.getClickedBlock().getType());
        } else {
            isRightClickable = true;
        }
        if (TalesZItemUtil.isActionRightClick(event.getAction()) && isRightClickable) {
            event.setUseInteractedBlock(Event.Result.DENY);
            event.setUseItemInHand(Event.Result.DENY);
            heal(event.getPlayer());
            TalesZItemUtil.removeOneItemFromPlayer(event.getPlayer(), event.getItem());
        }
    }

    @Override
    public void handleEvent(PlayerItemConsumeEvent event) {

    }

    @Override
    public void handleEvent(PlayerDropItemEvent event) {

    }

    @Override
    public void handleEvent(EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof Player) {
            event.setCancelled(true);
            Player p = (Player) event.getEntity();
            heal(p);
        }
    }

    @Override
    public boolean compare(ItemStack itemStack) {
        if (itemStack.hasItemMeta()) {
            if (itemStack.getItemMeta().hasDisplayName()) {
                if (ChatColor.stripColor(itemStack.getItemMeta().getDisplayName()).equals("Poultice")) {
                    return true;
                }
            }
        }
        return false;
    }

    private void heal(Player player) {
        if (Bleeding.isBleeding(player.getName())) {
            Bleeding.removeBleedingPlayer(player.getName());
            TalesZItemUtil.heal(player, 4);
        } else {
            TalesZItemUtil.heal(player, 6);
        }
    }

    @Override
    public int getMaxStackSize() {
        return 5;
    }
}
