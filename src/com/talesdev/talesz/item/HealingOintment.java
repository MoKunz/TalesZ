package com.talesdev.talesz.item;

import com.talesdev.talesz.itemsystem.TalesZToolItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;
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
 * TalesZToolItem
 * Created by MoKunz on 2/27/2015.
 */
public class HealingOintment implements TalesZToolItem {
    @Override
    public void handleEvent(BlockBreakEvent event) {

    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public Material getType() {
        return null;
    }

    @Override
    public int getAmount() {
        return 0;
    }

    @Override
    public long getDurability() {
        return 0;
    }

    @Override
    public ItemMeta configItemMeta(ItemMeta itemMeta) {
        return null;
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

    }

    @Override
    public void handleEvent(PlayerItemConsumeEvent event) {

    }

    @Override
    public void handleEvent(PlayerDropItemEvent event) {

    }

    @Override
    public void handleEvent(EntityDamageByEntityEvent event) {

    }

    @Override
    public boolean compare(ItemStack itemStack) {
        if (itemStack.getType().equals(Material.INK_SACK) && itemStack.hasItemMeta()) {
            ItemMeta meta = itemStack.getItemMeta();
            if (meta.hasDisplayName()) {
                if (ChatColor.stripColor(meta.getDisplayName()).equals("Healing Ointment")) {

                }
            }
        }
        return false;
    }
}
