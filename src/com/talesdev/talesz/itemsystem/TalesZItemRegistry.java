package com.talesdev.talesz.itemsystem;

import com.talesdev.talesz.Main;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

import java.util.Collection;
import java.util.HashMap;

/**
 * Item Registry
 * Created by MoKunz on 2/26/2015.
 */
public class TalesZItemRegistry {
    public static HashMap<String, TalesZItem> itemHashMap = new HashMap<>();

    public static TalesZItem getTalesZItem(String itemName) {
        if (itemHashMap.containsKey(itemName)) {
            return itemHashMap.get(itemName);
        } else {
            return null;
        }
    }

    public static Collection<TalesZItem> getAllTalesZItem() {
        return itemHashMap.values();
    }

    public static boolean talesZItemExist(String itemName) {
        return itemHashMap.containsKey(itemName);
    }

    public static void registerTalesZItem(TalesZItem item) {
        itemHashMap.put(item.getName(), item);
        if (item instanceof Listener) {
            Bukkit.getPluginManager().registerEvents(((Listener) item), Main.getPlugin());
        }
    }

    public static void unregisterTalesZItem(String itemName) {
        itemHashMap.remove(itemName);
    }
}
