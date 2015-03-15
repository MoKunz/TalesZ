package com.talesdev.talesz.world;

import com.sk89q.worldedit.bukkit.selections.CuboidSelection;
import org.bukkit.Location;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Block zone
 * Created by MoKunz on 3/14/2015.
 */
public class BlockZone {
    private String name;
    private ArrayList<BlockResources> blockList;
    private CuboidSelection selection;
    private BlockZoneRule blockZoneRule;

    public BlockZone(String name, CuboidSelection selection, BlockZoneRule rule) {
        this.name = name;
        this.blockList = new ArrayList<>();
        this.selection = selection;
    }

    public String getZoneName() {
        return this.name;
    }

    public List<BlockResources> getBlockResourcesList() {
        return this.blockList;
    }

    public CuboidSelection getZoneSelection() {
        return this.selection;
    }

    public void addBlockResources(BlockResources blockResources) {
        if (blockResources != null) {
            getBlockResourcesList().add(blockResources);
        }
    }

    public void removeBlockResources(Location location) {
        if (location != null) {
            for (Iterator<BlockResources> iterator = getBlockResourcesList().iterator(); iterator.hasNext(); ) {
                BlockResources block = iterator.next();
                Location blockLocation = block.getLocation();
                if (location.getBlockX() == blockLocation.getBlockX() &&
                        location.getBlockY() == blockLocation.getBlockY() &&
                        location.getBlockZ() == blockLocation.getBlockZ()) {
                    iterator.remove();
                }
            }
        }
    }

    public void removeBlockResources(Material material) {
        for (Iterator<BlockResources> iterator = getBlockResourcesList().iterator(); iterator.hasNext(); ) {
            BlockResources block = iterator.next();
            Material blockMaterial = block.getMaterial();
            if (material.equals(blockMaterial)) {
                iterator.remove();
            }
        }
    }

    public void update(int timePassed) {
        for (BlockResources block : getBlockResourcesList()) {
            block.timePassed(timePassed);
            if (block.shouldRegenerate()) {
                block.regenerate();
            }
        }
    }
}
