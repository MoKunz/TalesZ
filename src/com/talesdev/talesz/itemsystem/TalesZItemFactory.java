package com.talesdev.talesz.itemsystem;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;

/**
 * Item Factory class
 * Created by MoKunz on 2/26/2015.
 */
public class TalesZItemFactory {
    /**
     * Create A new TalesZItem from itemsystem registry
     * @param itemName name of the itemsystem
     * @return ItemStack or null if itemName is not found in the registry
     */
    public static ItemStack createItem(String itemName){
        TalesZItem item = TalesZItemRegistry.getTalesZItem(itemName);
        if(item == null){
            return new ItemStack(Material.AIR);
        }
        ItemStack itemStack =  new ItemStack(item.getType());
        ItemMeta itemMeta = item.configItemMeta(itemStack.getItemMeta());
        MaterialData itemData = item.configMaterialData(itemStack.getData());
        if(itemMeta != null){
            itemStack.setItemMeta(itemMeta);
        }
        if(itemData != null){
            itemStack.setData(itemData);
        }
        return itemStack;
    }
    public static TalesZItem getTalesZItemFromItemStack(ItemStack itemStack){
        if(itemStack == null){
            return null;
        }
        for(TalesZItem item : TalesZItemRegistry.getAllTalesZItem()) {
            if (item.compare(itemStack)) {
                return item;
            }
        }
        return null;
    }
}
