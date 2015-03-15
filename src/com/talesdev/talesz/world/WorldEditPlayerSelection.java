package com.talesdev.talesz.world;

import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.bukkit.selections.CuboidSelection;
import com.sk89q.worldedit.bukkit.selections.Selection;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * Selected block
 * Created by MoKunz on 3/14/2015.
 */
public class WorldEditPlayerSelection {
    private CuboidSelection selection;
    private boolean selectionAvailable;

    public WorldEditPlayerSelection(Player player) {
        WorldEditPlugin plugin = (WorldEditPlugin) Bukkit.getPluginManager().getPlugin("WorldEdit");
        Selection playerSelection = plugin.getSelection(player);
        if (this.selection != null && playerSelection instanceof CuboidSelection) {
            this.selection = (CuboidSelection) playerSelection;
            selectionAvailable = true;
        } else {
            selectionAvailable = false;
        }
    }

    public boolean isSelectionAvailable() {
        return this.selectionAvailable;
    }

    public CuboidSelection getSelection() {
        return this.selection;
    }
}
