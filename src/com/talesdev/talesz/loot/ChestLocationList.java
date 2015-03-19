package com.talesdev.talesz.loot;

import com.talesdev.talesz.TalesZMainConfig;
import com.talesdev.talesz.world.LocationString;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    private YamlConfiguration configuration = new YamlConfiguration();

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
        if (!getChest(chestType).contains(location)) {
            getChest(chestType).add(location);
        }
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

    public void load(String chestType) {
        YamlConfiguration config = TalesZMainConfig.getConfig();
        if (config.isSet("chestmapping" + "." + chestType)) {
            List<String> locList = config.getStringList("chestmapping" + "." + chestType);
            for (String loc : locList) {
                addLocation(chestType, LocationString.fromString(loc).getLocation());
            }
        }
    }

    public void save(String chestType) {
        YamlConfiguration config = TalesZMainConfig.getConfig();
        List<Location> locations = getChest(chestType);
        List<String> locList = locations.stream().map(loc -> new LocationString(loc).toString()).collect(Collectors.toList());
        config.set("chestmapping" + "." + chestType, locList);
    }
}
