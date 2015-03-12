package com.talesdev.talesz.world;

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
        List<String> locationString = getConfig().getStringList("spawnpoint");
        // add
        if (locationString != null) {
            if (locationString.size() > 0) {
                for (String locString : locationString) {
                    spawnLocation.add(LocationString.fromString(locString).getLocation());
                }
            }
        }
        // fallback
        if (spawnLocation.size() < 1) {
            spawnLocation.add(getFallbackSpawnLocation());
        }
    }

    public World getSpawnWorld() {
        return spawnWorld;
    }

    public Location randomSpawnLocation() {
        return getFallbackSpawnLocation();
    }

    private YamlConfiguration getConfig() {
        return TalesZMainConfig.getConfig();
    }

    private Location getFallbackSpawnLocation() {
        return getSpawnWorld().getSpawnLocation();
    }

    private List getAllSpawnPoint() {
        return spawnLocation;
    }

}
