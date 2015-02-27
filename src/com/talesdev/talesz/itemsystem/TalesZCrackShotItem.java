package com.talesdev.talesz.itemsystem;

import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * CrackShot Item Interface
 * Created by MoKunz on 2/26/2015.
 */
public abstract class TalesZCrackShotItem implements TalesZItem{
    @Override
    public final String getName(){
        return null;
    }
    @Override
    public final Material getType(){
        return Material.AIR;
    }
    @Override
    public final ItemMeta configItemMeta(ItemMeta itemMeta){
        return null;
    }
    public final void onClick(InventoryClickEvent event){}
    public abstract String getWeaponTitle();
    public abstract void getCrackShotItem();
}
