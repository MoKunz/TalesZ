package com.talesdev.talesz.event;

import com.talesdev.talesz.world.BlockInfo;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Event is called when regenerable blocks break
 * Created by MoKunz on 3/18/2015.
 */
public class TalesZBlockInfoCreatedEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    private BlockInfo blockInfo;

    public TalesZBlockInfoCreatedEvent(BlockInfo blockInfo) {
        this.blockInfo = blockInfo;
    }

    public BlockInfo getBlockInfo() {
        return this.blockInfo;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
