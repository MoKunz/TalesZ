package com.talesdev.talesz.itemsystem;

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
 * Interface for talesz itemsystem
 * Created by MoKunz on 2/26/2015.
 */
public interface TalesZItem {
    public String getName();
    public Material getType();
    public int getAmount();

    public short getDurability();
    public ItemMeta configItemMeta(ItemMeta itemMeta);
    public MaterialData configMaterialData(MaterialData materialData);
    //public Class<? extends ItemMeta> getItemMetaType();
    public void handleEvent(InventoryClickEvent event);
    public void handleEvent(PlayerInteractEntityEvent event);
    public void handleEvent(PlayerInteractEvent event);
    public void handleEvent(PlayerItemConsumeEvent event);
    public void handleEvent(PlayerDropItemEvent event);
    public void handleEvent(EntityDamageByEntityEvent event);
    public boolean compare(ItemStack itemStack);
}
