package com.talesdev.talesz.loot;

import com.talesdev.talesz.RandomUtil;

import java.util.ArrayList;
import java.util.Collections;
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

    public List<LootItem> randomlyPickUp(int minimumSize, int maximumSize) {
        List<LootItem> lootCollection = new ArrayList<>();
        if (getAllLootItem().size() > 0) {
            int limitCounter = 0;
            do {
                if (limitCounter > 8) {
                    break;
                }
                List<LootItem> shuffledList = getAllLootItem();
                Collections.shuffle(shuffledList);
                for (LootItem item : shuffledList) {
                    if (RandomUtil.randomPercent(item.getProbability())) {
                        if (lootCollection.size() >= maximumSize) {
                            break;
                        }
                        lootCollection.add(item);
                    }
                }
                if (lootCollection.size() < minimumSize) {
                    limitCounter++;
                }
            }
            while (lootCollection.size() < minimumSize);
        }
        return lootCollection;
    }

    public List<LootItem> randomlyPickUp() {
        return randomlyPickUp(1, 54);
    }
}
