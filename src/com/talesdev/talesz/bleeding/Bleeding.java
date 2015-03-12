package com.talesdev.talesz.bleeding;

import com.talesdev.talesz.TalesZ;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;

/**
 * Bleeding mechanic
 * Created by MoKunz on 17/10/2557.
 */
public class Bleeding {
    protected static List<String> bleedingPlayer;

    static {
        bleedingPlayer = new ArrayList<>();
    }

    public static void addBleedingPlayer(String player) {
        if (!bleedingPlayer.contains(player)) {
            bleedingPlayer.add(player);
            Bukkit.getScheduler().runTaskLater(TalesZ.getPlugin(), () -> {
                if (Bukkit.getPlayer(player) != null && isBleeding(player)) {
                    Bukkit.getPlayer(player).sendMessage(ChatColor.RED + "You are now bleeding. Hurry up and find some bandage to stop bleeding!");
                }
            }, 50L);
        }
    }

    public static void removeBleedingPlayer(String player, boolean notifyPlayer) {
        if (bleedingPlayer.contains(player)) {
            bleedingPlayer.remove(player);
            if (notifyPlayer) Bukkit.getPlayer(player).sendMessage(ChatColor.GREEN + "You are no longer bleeding.");
        }
    }

    public static void removeBleedingPlayer(String player) {
        removeBleedingPlayer(player, false);
    }

    public static boolean isBleeding(String player) {
        return bleedingPlayer.contains(player);
    }

    public static void updateAll(double damage) {
        for (String player : bleedingPlayer) {
            update(player, damage);
        }
    }

    public static void update(String player, double damage) {
        if (Bukkit.getServer().getPlayer(player) == null) {
            return;
        }
        if (Bukkit.getServer().getPlayer(player).isOnline()) {
            Bukkit.getServer().getPlayer(player).damage(0.25);
        }
    }
}
