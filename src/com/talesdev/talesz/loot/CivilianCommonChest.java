package com.talesdev.talesz.loot;

import com.talesdev.talesz.itemsystem.TalesZItemFactory;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

/**
 * Civilian chest
 * Created by MoKunz on 3/15/2015.
 */
public class CivilianCommonChest extends LootChest {
    public CivilianCommonChest() {
        super("Civ_Common", new LootItemCollection());
        setMinLootSize(5);
        getItemCollection().addLootItem(new OldLootItem(new ItemStack(Material.WOOD_SWORD), 10, 30));
        getItemCollection().addLootItem(new OldLootItem(new ItemStack(Material.WOOD_AXE), 10, 30));
        getItemCollection().addLootItem(new OldLootItem(new ItemStack(Material.LEATHER_HELMET), 10, 100));
        getItemCollection().addLootItem(new OldLootItem(new ItemStack(Material.LEATHER_CHESTPLATE), 10, 100));
        getItemCollection().addLootItem(new OldLootItem(new ItemStack(Material.LEATHER_LEGGINGS), 10, 100));
        getItemCollection().addLootItem(new OldLootItem(new ItemStack(Material.LEATHER_BOOTS), 10, 100));
        getItemCollection().addLootItem(new LootItem(new ItemStack(Material.APPLE), 10));
        getItemCollection().addLootItem(new LootItem(TalesZItemFactory.createItem("Bandage"), 5));
        getItemCollection().addLootItem(new LootItem(new ItemStack(Material.ROTTEN_FLESH), 70));
        getItemCollection().addLootItem(new LootItem(new ItemStack(Material.BOWL), 10));
    }
}
