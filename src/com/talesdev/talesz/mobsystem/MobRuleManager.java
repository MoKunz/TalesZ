package com.talesdev.talesz.mobsystem;

import com.talesdev.talesz.Rule;
import org.bukkit.entity.EntityType;

import java.util.HashMap;


/**
 * Mob rule manager
 * Created by MoKunz on 3/5/2015.
 */
public class MobRuleManager {
    // mob rule hashmap
    private static HashMap<EntityType, Rule> mobRule = new HashMap<>();

    public static boolean isAllowedToSpawn() {
        return false;
    }

    public static void setRule(EntityType type, Rule rule) {
        mobRule.put(type, rule);
    }

    public static Rule getRule(EntityType type) {
        if (mobRule.containsKey(type)) {
            return mobRule.get(type);
        } else {
            return Rule.DENY;
        }
    }
}