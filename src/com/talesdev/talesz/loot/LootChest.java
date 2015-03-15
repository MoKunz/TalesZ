package com.talesdev.talesz.loot;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Chest;
import org.bukkit.inventory.Inventory;

import java.util.List;

/**
 * Loot chest type
 * Created by MoKunz on 3/15/2015.
 */
public abstract class LootChest {
    private String typeName;
    private LootItemCollection itemCollection;

    public LootChest(String name, LootItemCollection collection) {
        this.typeName = name;
        this.itemCollection = collection;
    }

    public String getTypeName() {
        return typeName;
    }

    public LootItemCollection getItemCollection() {
        return itemCollection;
    }

    public boolean isLootChest(Block block) {
        BlockState blockState = block.getState();
        if (blockState instanceof Chest) {
            Chest chest = (Chest) blockState;
            return isLootChest(chest.getInventory());
        }
        return false;
    }

    public boolean isLootChest(Inventory inventory) {
        return ChatColor.stripColor(inventory.getName()).equalsIgnoreCase(typeName);
    }

    public void fillChest(Block block) {
        if (block.getType().equals(Material.CHEST)) {
            Chest chest = (Chest) block.getState();
            fillChest(chest.getInventory());
        }
    }

    public void fillChest(Inventory inventory) {
        List<LootItem> lootItemList = getItemCollection().randomlyPickUp();
        if (lootItemList.size() > 0) {
            for (LootItem item : getItemCollection().randomlyPickUp()) {
                inventory.addItem(item.getItem());
            }
        }
    }
}
