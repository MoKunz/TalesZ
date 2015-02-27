package com.talesdev.talesz;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;

/**
 * TalesZ Task manager
 * Created by MoKunz on 17/10/2557.
 */
public class TalesZTask {
    private static HashMap<String,BukkitTask> task = new HashMap<>();
    // Thirst task
    public static BukkitTask thirstTask;
    // Bleeding task
    public static BukkitTask bleedingTask;
    public static void setTask(String taskName,BukkitTask bukkitTask){
        task.put(taskName,bukkitTask);
    }
    public static BukkitTask getTask(String taskName){
        return task.get(taskName);
    }
    public static void cancelAll(){
        task.values().forEach(org.bukkit.scheduler.BukkitTask::cancel);
    }
}
