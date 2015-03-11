package com.talesdev.talesz.item;

import com.talesdev.talesz.itemsystem.TalesZItem;
import com.talesdev.talesz.itemsystem.TalesZItemUtil;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;
import org.bukkit.util.Vector;

/**
 * Grappling Hook
 * Created by MoKunz on 3/11/2015.
 */
public class GrapplingHook implements TalesZItem, Listener {
    @Override
    public String getName() {
        return "Grappling Hook";
    }

    @Override
    public Material getType() {
        return Material.FISHING_ROD;
    }

    @Override
    public int getAmount() {
        return 1;
    }

    @Override
    public short getDurability() {
        return 0;
    }

    @Override
    public ItemMeta configItemMeta(ItemMeta itemMeta) {
        itemMeta.setDisplayName(ChatColor.BLUE + "Grappling Hook");
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

    @EventHandler
    public void onFish(PlayerFishEvent event) {
        if (compare(event.getPlayer().getItemInHand())) {
            if (TalesZItemUtil.isHookLanded(event.getHook())) {
                if (event.getHook().getLocation().distance(event.getPlayer().getLocation()) < 3) {
                    return;
                }
                Vector vector = event.getHook().getLocation().subtract(event.getPlayer().getLocation()).toVector();
                vector.multiply(new Vector(0.2, 0.17, 0.2));
                event.getPlayer().setVelocity(event.getPlayer().getVelocity().add(vector));
            }
        }
    }

    @Override
    public boolean compare(ItemStack itemStack) {
        if (itemStack.hasItemMeta() && itemStack.getType().equals(Material.FISHING_ROD)) {
            if (itemStack.getItemMeta().hasDisplayName()) {
                if (ChatColor.stripColor(itemStack.getItemMeta().getDisplayName()).equalsIgnoreCase("Grappling Hook")) {
                    return true;
                }
            }
        }
        return false;
    }
}
