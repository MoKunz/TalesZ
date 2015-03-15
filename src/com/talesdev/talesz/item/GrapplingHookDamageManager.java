package com.talesdev.talesz.item;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Damage manager
 * Created by MoKunz on 3/12/2015.
 */
public class GrapplingHookDamageManager {
    private static HashMap<String, Integer> playerList = new HashMap<>();

    public static void addReduceDamage(String player, int tick) {
        playerList.put(player, tick);
    }

    public static void addReduceDamage(String player) {
        addReduceDamage(player, 100);
    }

    public static void removeReduceDamage(String player) {
        playerList.remove(player);
    }

    private static void updateTime(String playerName) {
        playerList.put(playerName, playerList.get(playerName) - 1);
    }

    private static int getTime(String playerName) {
        return playerList.get(playerName);
    }

    public static void update() {
        if (playerList.size() > 0) {
            for (Iterator<Map.Entry<String, Integer>> it = playerList.entrySet().iterator(); it.hasNext(); ) {
                Map.Entry<String, Integer> entry = it.next();
                updateTime(entry.getKey());
                if (getTime(entry.getKey()) < 1) {
                    it.remove();
                }
            }
        }
    }

    public static boolean contains(String playerName) {
        return playerList.containsKey(playerName);
    }

    public static void clear() {
        playerList.clear();
    }
}
