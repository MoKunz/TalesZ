package com.talesdev.talesz.item;

import com.talesdev.talesz.itemsystem.TalesZWeaponsItem;
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
 *
 * Created by MoKunz on 2/27/2015.
 */
public class EnderPearl implements TalesZWeaponsItem{
    @Override
    public void handleEvent(EntityDamageByEntityEvent event) {

    }

    @Override
    public String getName() {
        return "EnderPearl";
    }

    @Override
    public Material getType() {
        return Material.ENDER_PEARL;
    }

    @Override
    public int getAmount() {
        return 1;
    }

    @Override
    public long getDurability() {
        return 0;
    }

    @Override
    public ItemMeta configItemMeta(ItemMeta itemMeta) {
        itemMeta.setDisplayName("Ender Pearl");
        return itemMeta;
    }

    @Override
    public MaterialData configMaterialData(MaterialData materialData) {
        return materialData;
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
    public boolean compare(ItemStack itemStack) {
        return false;
    }
}
