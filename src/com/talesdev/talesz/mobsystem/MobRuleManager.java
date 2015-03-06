package com.talesdev.talesz.mobsystem;

import org.bukkit.entity.EntityType;

import java.util.HashMap;


/**
 * Mob rule manager
 * Created by MoKunz on 3/5/2015.
 */
public class MobRuleManager {
    private static HashMap<EntityType, MobRule> mobRule = new HashMap<>();

    public static boolean isAllowedToSpawn() {
        return false;
    }

    public static void setRule(EntityType type, MobRule rule) {
        mobRule.put(type, rule);
    }

    public static MobRule getRule(EntityType type) {
        if (mobRule.containsKey(type)) {
            return mobRule.get(type);
        } else {
            return MobRule.DENY;
        }
    }
}