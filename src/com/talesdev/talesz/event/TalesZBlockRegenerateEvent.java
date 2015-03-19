package com.talesdev.talesz.event;

import com.talesdev.talesz.world.BlockInfo;
import org.bukkit.block.Block;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Block regen event
 * Created by MoKunz on 3/18/2015.
 */
public class TalesZBlockRegenerateEvent extends Event implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private Block block;
    private BlockInfo blockInfo;
    private boolean cancelled = false;

    public TalesZBlockRegenerateEvent(Block block, BlockInfo blockInfo) {
        this.block = block;
        this.blockInfo = blockInfo;
    }

    public BlockInfo getBlockInfo() {
        return blockInfo;
    }

    public Block getBlock() {
        return block;
    }

    public void setBlock(Block block) {
        this.block = block;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }
}
