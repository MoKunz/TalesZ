package com.talesdev.talesz.item;

import com.talesdev.talesz.itemsystem.TalesZItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;
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
 * Antibiotics
 * Created by MoKunz on 2/27/2015.
 */
public class Antibiotics implements TalesZItem {
    @Override
    public String getName() {
        return "Antibiotics";
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
        return 10;
    }

    @Override
    public ItemMeta configItemMeta(ItemMeta itemMeta) {
        itemMeta.setDisplayName(ChatColor.GREEN + "Antibiotics");
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
        if (itemStack.getType().equals(Material.INK_SACK)) {
            if (itemStack.getDurability() == 10) {
                if (itemStack.hasItemMeta()) {
                    ItemMeta meta = itemStack.getItemMeta();
                    if (meta.hasDisplayName()) {
                        if (ChatColor.stripColor(meta.getDisplayName()).equals("Antibiotics")) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}
