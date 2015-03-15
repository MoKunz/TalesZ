package com.talesdev.talesz.world;

import com.talesdev.talesz.RandomUtil;
import com.talesdev.talesz.TalesZMainConfig;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;

import java.util.ArrayList;
import java.util.List;

/**
 * Spawn point manager
 * Created by MoKunz on 3/11/2015.
 */
public class SpawnPointManager {
    private World spawnWorld;
    private ArrayList<Location> spawnLocation = new ArrayList<>();

    public SpawnPointManager(World world) {
        // get default world
        this.spawnWorld = world;
        // begin config reading
        loadSpawnLocation(loadSpawnLocationList());
    }

    public void loadSpawnLocation(List<String> spawnLocationList) {
        // clear
        spawnLocation.clear();
        // add
        if (spawnLocationList != null) {
            if (spawnLocationList.size() > 0) {
                for (String locString : spawnLocationList) {
                    spawnLocation.add(LocationString.fromString(locString).getLocation());
                }
            }
        }
        // fallback
        if (spawnLocation.size() < 1) {
            spawnLocation.add(getFallbackSpawnLocation());
        }
    }

    public List<String> loadSpawnLocationList() {
        return getConfig().getStringList("spawnpoint");
    }

    public World getSpawnWorld() {
        return spawnWorld;
    }

    public Location randomSpawnLocation() {
        return getAllSpawnPoint().get(RandomUtil.randomRange(spawnLocation.size() - 1));
    }

    private YamlConfiguration getConfig() {
        return TalesZMainConfig.getConfig();
    }

    private Location getFallbackSpawnLocation() {
        return getSpawnWorld().getSpawnLocation();
    }

    private List<Location> getAllSpawnPoint() {
        return spawnLocation;
    }

}
