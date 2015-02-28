package com.talesdev.talesz;

import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;
import java.util.Set;

/**
 * Player Task
 * Created by MoKunz on 2/28/2015.
 */
public class PlayerTask {
    private String playerName;
    private HashMap<String, BukkitTask> taskArrayList = new HashMap<>();

    public PlayerTask(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void add(String owner, BukkitTask task) {
        taskArrayList.put(owner, task);
    }

    public boolean taskExist(String owner) {
        return taskArrayList.containsKey(owner);
    }

    public void cancel(String owner) {
        if (taskArrayList.get(owner) != null) {
            taskArrayList.get(owner).cancel();
        }
        taskArrayList.remove(owner);
    }

    public Set<String> getTaskOwner() {
        return taskArrayList.keySet();
    }
}
