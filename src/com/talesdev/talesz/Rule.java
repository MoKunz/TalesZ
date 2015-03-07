package com.talesdev.talesz;

/**
 * Block breaking/placing rule
 * Created by MoKunz on 3/6/2015.
 */
public enum Rule {
    ALLOW(true),
    DENY(false);
    private boolean result;

    Rule(boolean result) {
        this.result = result;
    }

    public boolean getResult() {
        return result;
    }
}
