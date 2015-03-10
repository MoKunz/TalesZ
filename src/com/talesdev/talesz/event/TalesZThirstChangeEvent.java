package com.talesdev.talesz.event;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Event is called when player thirst changed
 * Created by MoKunz on 3/10/2015.
 */
public class TalesZThirstChangeEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    private Player player;
    private double changed;

    public TalesZThirstChangeEvent(Player player, double amountChanged) {
        this.player = player;
        this.changed = amountChanged;
    }

    public Player getPlayer() {
        return player;
    }

    public double getAmountChanged() {
        return changed;
    }

    public void setAmountChanged(double amount) {
        this.changed = amount;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

}
