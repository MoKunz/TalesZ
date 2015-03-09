package com.talesdev.talesz.itemsystem;

import com.talesdev.talesz.ReflectionUtils;
import me.captainbern.bukkittool.BukkitTool;
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
     *
     * @param itemName name of the itemsystem
     * @return ItemStack or null if itemName is not found in the registry
     */
    public static ItemStack createItem(String itemName) {
        TalesZItem item = TalesZItemRegistry.getTalesZItem(itemName);
        if (item == null) {
            return new ItemStack(Material.AIR);
        }
        ItemStack itemStack = new ItemStack(item.getType());
        itemStack.setDurability(item.getDurability());
        ItemMeta itemMeta = item.configItemMeta(itemStack.getItemMeta());
        MaterialData itemData = item.configMaterialData(itemStack.getData());
        if (itemMeta != null) {
            itemStack.setItemMeta(itemMeta);
        }
        if (itemData != null) {
            itemStack.setData(itemData);
        }
        // max stack
        if (item instanceof MaxStackableInterface) {
            // max stack size
            int maxStackSize = ((MaxStackableInterface) item).getMaxStackSize();
            if (maxStackSize > 0) {
                // get item stack class
                ReflectionUtils.RefClass CBItemStackClass = ReflectionUtils.getRefClass(BukkitTool.getCBClass("inventory.CraftItemStack"));
                ReflectionUtils.RefClass NMSItemStackClass = ReflectionUtils.getRefClass(BukkitTool.getNMSClass("ItemStack"));
                ReflectionUtils.RefClass NMSItemClass = ReflectionUtils.getRefClass(BukkitTool.getNMSClass("Item"));
                // get item stack method
                ReflectionUtils.RefMethod asNMSCopy = CBItemStackClass.getMethod("asNMSCopy");
                ReflectionUtils.RefMethod getItem = NMSItemStackClass.getMethod("getItem");
                ReflectionUtils.RefMethod setItem = NMSItemStackClass.getMethod("setItem");
                ReflectionUtils.RefMethod asBukkitCopy = CBItemStackClass.getMethod("asBukkitCopy");
                // max stack field
                ReflectionUtils.RefField maxStackSizeField = NMSItemClass.getField("maxStackSize");
                // cb item stack -> nms item stack
                Object nmsItemStack = asNMSCopy.of(null).call(itemStack);
                Object nmsItem = getItem.of(nmsItemStack).call();
                // set max stack size
                maxStackSizeField.of(nmsItem).set(maxStackSize);
                // set item
                setItem.of(nmsItemStack).call(nmsItem);
                // nms item stack -> bukkit item stack
                Object bukkitItemStack = asBukkitCopy.of(null).call(nmsItemStack);
                if (bukkitItemStack instanceof ItemStack) {
                    itemStack = (ItemStack) bukkitItemStack;
                }
            }
        }
        return itemStack;
    }

    public static TalesZItem getTalesZItemFromItemStack(ItemStack itemStack) {
        if (itemStack == null) {
            return null;
        }
        for (TalesZItem item : TalesZItemRegistry.getAllTalesZItem()) {
            if (item.compare(itemStack)) {
                return item;
            }
        }
        return null;
    }
}
