package com.talesdev.talesz.test;

import com.talesdev.talesz.RandomUtil;
import com.talesdev.talesz.itemsystem.TalesZItemFactory;
import com.talesdev.talesz.loot.LootItem;
import com.talesdev.talesz.loot.LootItemCollection;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class LootItemCollectionTest {
    LootItemCollection collection;

    @Before
    public void setUp() {
        collection = new LootItemCollection();
        collection.addLootItem(new LootItem(new ItemStack(Material.AIR), 5));
        collection.addLootItem(new LootItem(new ItemStack(Material.AIR), 50));
        collection.addLootItem(new LootItem(new ItemStack(Material.AIR), 100));
        collection.addLootItem(new LootItem(new ItemStack(Material.APPLE, 16), 100));
        collection.addLootItem(new LootItem(new ItemStack(Material.APPLE, 8), 25));
        collection.addLootItem(new LootItem(new ItemStack(Material.APPLE, 64), 5));
        collection.addLootItem(new LootItem(TalesZItemFactory.createItem("Bandage"), 50));
        collection.addLootItem(new LootItem(TalesZItemFactory.createItem("GrapplingHook"), 15));
    }

    @Test
    public void testRandomlyPickUp() throws Exception {
        List<LootItem> lootItems = collection.randomlyPickUp();
        assertTrue(lootItems.size() > 0);
        int size = RandomUtil.randomRange(1, 54);
        int range = RandomUtil.randomRange(0, 10);
        List<LootItem> lootItems2 = collection.randomlyPickUp(size, size + range);
        System.out.println(lootItems2.size());
        assertTrue(lootItems2.size() >= size && lootItems2.size() <= (size + range));
    }
}