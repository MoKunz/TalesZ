package com.talesdev.talesz.world;

import org.bukkit.Location;
import org.bukkit.Material;

/**
 * Unique block info
 * Created by MoKunz on 3/10/2015.
 */
public class BlockInfo {
    private Material block;
    private int time;
    private Location blockLocation;

    public BlockInfo(Material block, Location blockLocation, int time) {
        this.block = block;
        this.blockLocation = blockLocation;
        this.time = time;
    }

    public Material getBlock() {
        return block;
    }

    public void setBlock(Material block) {
        this.block = block;
    }

    public int getTime() {
        return this.time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void setBlockLocation(Location location) {
        this.blockLocation = location;
    }

    public Location getBlockLocation() {
        return this.blockLocation;
    }
}
