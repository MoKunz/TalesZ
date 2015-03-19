package com.talesdev.talesz.loot;

import org.bukkit.Location;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Chest location list
 * Created by MoKunz on 3/19/2015.
 */
public class ChestLocationList {
    /**
     * key : Chest type name
     * value : list of location
     */
    private Map<String, List<Location>> chestLocations = new HashMap<>();

    public void createChest(String chestType) {
        chestLocations.putIfAbsent(chestType, new ArrayList<>());
    }

    public List<Location> getChest(String chestType) {
        if (!chestLocations.containsKey(chestType)) {
            createChest(chestType);
        }
        return chestLocations.get(chestType);
    }

    public void destroyChest(String chestType) {
        chestLocations.remove(chestType);
    }

    public void addLocation(String chestType, Location location) {
        getChest(chestType).add(location);
    }

    public boolean containsChest(String chestType) {
        return chestLocations.containsKey(chestType);
    }

    public boolean containsLocation(String chestType, Location location) {
        return containsChest(chestType) && getChest(chestType).contains(location);
    }

    public String getChestType(Location location) {
        for (Map.Entry<String, List<Location>> map : chestLocations.entrySet()) {
            String chestType = map.getKey();
            List<Location> locations = map.getValue();
            if (locations.contains(location)) {
                return chestType;
            }
        }
        return null;
    }
}
