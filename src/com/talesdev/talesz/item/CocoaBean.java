package com.talesdev.talesz.item;

import com.talesdev.talesz.itemsystem.MaxStackableInterface;
import com.talesdev.talesz.itemsystem.TalesZItem;
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

public class CocoaBean implements TalesZItem, MaxStackableInterface {
    @Override
    public String getName() {
        return null;
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
        return 3;
    }

    @Override
    public ItemMeta configItemMeta(ItemMeta itemMeta) {
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
    public void handleEvent(EntityDamageByEntityEvent event) {

    }

    @Override
    public boolean compare(ItemStack itemStack) {
        return itemStack.getType().equals(Material.INK_SACK) && itemStack.getDurability() == 3;
    }

    @Override
    public int getMaxStackSize() {
        return 3;
    }
}
