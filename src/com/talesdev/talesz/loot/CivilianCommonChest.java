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
        getItemCollection().addLootItem(new LootItem(TalesZItemFactory.createItem("Bandage"), 100));
        getItemCollection().addLootItem(new LootItem(new ItemStack(Material.WOOD_HOE), 100));
        getItemCollection().addLootItem(new LootItem(new ItemStack(Material.STONE_SWORD), 100));
        getItemCollection().addLootItem(new LootItem(TalesZItemFactory.createItem("Bandage"), 100));
    }
}
