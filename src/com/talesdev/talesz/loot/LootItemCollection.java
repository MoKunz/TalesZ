package com.talesdev.talesz.loot;

import com.talesdev.talesz.RandomUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Loot item collection
 * Created by MoKunz on 3/15/2015.
 */
public class LootItemCollection {
    private List<LootItem> lootItemList;

    public LootItemCollection() {
        lootItemList = new ArrayList<>();
    }

    public void addLootItem(LootItem item) {
        lootItemList.add(item);
    }

    public List<LootItem> getAllLootItem() {
        return lootItemList;
    }

    public List<LootItem> randomlyPickUp() {
        List<LootItem> lootCollection = new ArrayList<>();
        if (getAllLootItem().size() > 0) {
            for (LootItem item : getAllLootItem()) {
                if (RandomUtil.randomPercent(item.getProbability())) {
                    lootCollection.add(item);
                }
            }
        }
        return lootCollection;
    }
}
