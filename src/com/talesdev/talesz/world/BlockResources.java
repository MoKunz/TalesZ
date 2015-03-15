package com.talesdev.talesz.world;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

/**
 * Abstract block resources
 * Created by MoKunz on 3/14/2015.
 */
public class BlockResources implements TimedRegeneration {
    private int timeCount;
    protected int regenerationTime;
    protected Material material;
    protected Location location;

    public BlockResources(Material material, Location location, int regenerationTime) {
        this.material = material;
        this.location = location;
        this.regenerationTime = regenerationTime;
    }

    public int getTimeCount() {
        return this.timeCount;
    }

    public boolean shouldRegenerate() {
        return this.timeCount <= 0;
    }

    public void timePassed(int total) {
        if (this.timeCount - total >= 0) {
            this.timeCount -= total;
        } else {
            this.timeCount = 0;
        }
    }

    public int getRegenerationTime() {
        return this.regenerationTime;
    }

    public Material getMaterial() {
        return this.material;
    }

    public Location getLocation() {
        return this.location;
    }

    public void regenerate() {
        Block block = this.location.getWorld().getBlockAt(this.location);
        block.setType(getMaterial());
    }
}
