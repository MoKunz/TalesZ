package com.talesdev.talesz.loot;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * class for mapping slot number with itemstack
 * Created by MoKunz on 3/16/2015.
 */
public class MappedItem {
    Map<Integer, LootItem> lootItemMap = new HashMap<>();

    public MappedItem(List<Integer> key, List<LootItem> value) {
        if (key.size() == value.size()) {
            int itemIndex = 0;
            for (int i : key) {
                lootItemMap.put(i, value.get(itemIndex));
                itemIndex++;
            }
        }
    }

    public Map<Integer, LootItem> getMappedLootItem() {
        return lootItemMap;
    }
}