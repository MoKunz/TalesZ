package com.talesdev.talesz.world;

/**
 * Block breaking/placing rule
 * Created by MoKunz on 3/6/2015.
 */
public enum BlockRule {
    ALLOW(true),
    DENY(false);
    private boolean result;

    BlockRule(boolean result) {
        this.result = result;
    }

    public boolean getResult() {
        return result;
    }
}
