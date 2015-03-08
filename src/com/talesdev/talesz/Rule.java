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

    public static Rule getRule(String ruleString) {
        if (ruleString == null) {
            return Rule.DENY;
        }
        if (ruleString.equalsIgnoreCase("alllow")) {
            return Rule.ALLOW;
        } else if (ruleString.equalsIgnoreCase("deny")) {
            return Rule.DENY;
        } else {
            return Rule.DENY;
        }
    }
}
