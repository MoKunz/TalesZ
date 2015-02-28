package com.talesdev.talesz;

import org.bukkit.entity.Player;

import java.util.HashMap;

/**
 * Player task manager
 * Created by MoKunz on 2/28/2015.
 */
public class PlayerTaskManager {
    private static HashMap<String, PlayerTask> playerTaskHashMap = new HashMap<>();

    public static PlayerTask getPlayerTask(Player player) {
        if (player == null) {
            return null;
        }
        if (!playerTaskHashMap.containsKey(player.getName())) {
            createNewPlayerTask(new PlayerTask(player.getName()));
        }
        return playerTaskHashMap.get(player.getName());
    }

    private static void createNewPlayerTask(PlayerTask task) {
        playerTaskHashMap.put(task.getPlayerName(), task);
    }

    public static void cancelPlayerTask(Player player) {
        PlayerTask task = playerTaskHashMap.get(player.getName());
        if (task != null) {
            if (task.getTaskOwner().size() > 0) {
                for (String owner : task.getTaskOwner()) {
                    task.cancel(owner);
                }
            }
        }
    }
}
