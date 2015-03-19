package com.talesdev.talesz;

import org.bukkit.entity.Player;

/**
 * ....
 */
public class TalesZPlayer {
    private Player player;
    private double thirst = 100.0;
    private boolean isBleeding = false;

    public TalesZPlayer(Player player) throws Exception {
        if (player != null) {
            throw new Exception("Player can't be null!");
        }
    }

    public Player getPlayer() {
        return player;
    }

}
