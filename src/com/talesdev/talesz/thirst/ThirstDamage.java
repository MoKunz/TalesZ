package com.talesdev.talesz.thirst;

import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Thirst damage
 * Created by MoKunz on 2/14/2015.
 */
public class ThirstDamage {
    public static final int THIRST_DAMAGE = 2;
    private static ArrayList<String> playerList = new ArrayList<>();

    public static void addToList(String playerName) {
        if (!contain(playerName)) {
            playerList.add(playerName);
        }
    }

    public static void removeFromList(String playerName) {
        if (contain(playerName)) {
            playerList.remove(playerList.indexOf(playerName));
        }
    }

    public static boolean contain(String playerName) {
        return playerList.contains(playerName);
    }

    public static void update() {
        if (playerList != null && playerList.size() > 0) {
            // use iterator
            for (Iterator<String> iterator = playerList.iterator(); iterator.hasNext(); ) {
                String player = iterator.next();
                if (Thirst.getThirst(player) > 0) {
                    iterator.remove();
                } else {
                    if (player != null) {
                        if (Bukkit.getServer().getPlayer(player) != null) {
                            Bukkit.getServer().getPlayer(player).damage(THIRST_DAMAGE);
                        }
                    }
                }
            }
        }
    }

    public static void clear() {
        playerList.clear();
    }
}
