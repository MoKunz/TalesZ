package com.talesdev.talesz.loot;

import com.talesdev.talesz.RandomUtil;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Chest;
import org.bukkit.inventory.Inventory;

import java.util.List;
import java.util.Map;

/**
 * Loot chest type
 * Created by MoKunz on 3/15/2015.
 */
public abstract class LootChest {
    private String typeName;
    private LootItemCollection itemCollection;
    private int minLootSize;
    private int maxLootSize;

    public LootChest(String name, LootItemCollection collection) {
        this.typeName = name;
        this.itemCollection = collection;
        this.minLootSize = 1;
        this.maxLootSize = 54;
    }

    public int getMinLootSize() {
        return minLootSize;
    }

    public void setMinLootSize(int size) {
        if (size >= 0 && size <= maxLootSize) {
            this.minLootSize = size;
        }
    }

    public int getMaxLootSize() {
        return minLootSize;
    }

    public void setMaxLootSize(int size) {
        if (size >= minLootSize) {
            this.minLootSize = size;
        }
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
        List<LootItem> lootItemList = getItemCollection().randomlyPickUp(getMinLootSize(), getMaxLootSize());
        List<Integer> inventoryIndex = RandomUtil.randomNumberList(0, 26, lootItemList.size());
        Map<Integer, LootItem> mappedLootItem = new MappedItem(inventoryIndex, lootItemList).getMappedLootItem();
        if (mappedLootItem.size() > 0) {
            for (Map.Entry<Integer, LootItem> entry : mappedLootItem.entrySet()) {
                inventory.setItem(entry.getKey(), entry.getValue().getItem());
            }
        }
    }
}
