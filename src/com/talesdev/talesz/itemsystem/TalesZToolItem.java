package com.talesdev.talesz.itemsystem;

import org.bukkit.event.block.BlockBreakEvent;

/**
 * Tool Item Interface
 * Created by MoKunz on 2/26/2015.
 */
public interface TalesZToolItem extends TalesZItem{
    public void handleEvent(BlockBreakEvent event);
}
