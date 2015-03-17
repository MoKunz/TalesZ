package com.talesdev.talesz.loot;

import com.talesdev.talesz.RandomUtil;
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
        getItemCollection().addLootItem(new LootItem(new ItemStack(Material.WOOD_SWORD, 1, (short) RandomUtil.randomRange(40)), 45));
        getItemCollection().addLootItem(new LootItem(new ItemStack(Material.LEATHER_HELMET), 30));
        getItemCollection().addLootItem(new LootItem(new ItemStack(Material.LEATHER_CHESTPLATE), 20));
        getItemCollection().addLootItem(new LootItem(new ItemStack(Material.LEATHER_LEGGINGS), 20));
        getItemCollection().addLootItem(new LootItem(new ItemStack(Material.LEATHER_BOOTS), 20));
        getItemCollection().addLootItem(new LootItem(new ItemStack(Material.APPLE), 10));
        getItemCollection().addLootItem(new LootItem(TalesZItemFactory.createItem("Bandage"), 30));
        getItemCollection().addLootItem(new LootItem(new ItemStack(Material.ROTTEN_FLESH), 80));
        getItemCollection().addLootItem(new LootItem(new ItemStack(Material.BOWL), 10));
    }
}
