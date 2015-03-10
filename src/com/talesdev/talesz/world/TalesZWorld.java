package com.talesdev.talesz.world;

import java.util.ArrayList;
import java.util.List;

/**
 * TalesZ World manager
 * Created by MoKunz on 20/10/2557.
 */
public class TalesZWorld {
    public static String WORLD_NAME = "world";
    protected static List<String> playerList = new ArrayList<>();

    public static void setWorldName(String worldName) {
        WORLD_NAME = worldName;
    }

    public static void addPlayer(String player) {
        playerList.add(player);
    }

    public static void removePlayer(String player) {
        playerList.remove(player);
    }

    public static boolean containPlayer(String player) {
        return playerList.contains(player);
    }
}
