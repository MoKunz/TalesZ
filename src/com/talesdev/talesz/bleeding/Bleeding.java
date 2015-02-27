package com.talesdev.talesz.bleeding;

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
    static{
        bleedingPlayer = new ArrayList<>();
    }
    public static void addBleedingPlayer(String player){
        if(!bleedingPlayer.contains(player)){
            bleedingPlayer.add(player);
            Bukkit.getPlayer(player).sendMessage(ChatColor.RED + "You are now bleeding. Hurry up and find some bandage to stop bleeding!");
        }
    }
    public static void removeBleedingPlayer(String player) {
        if (bleedingPlayer.contains(player)) {
            bleedingPlayer.remove(player);
            Bukkit.getPlayer(player).sendMessage(ChatColor.GREEN + "You are no longer bleeding.");
        }
    }
    public static boolean isBleeding(String player){
        if(bleedingPlayer.contains(player)){
            return true;
        }
        else{
            return false;
        }
    }
    public static void updateAll(double damage){
        for(String player : bleedingPlayer){
            update(player,damage);
        }
    }
    public static void update(String player,double damage){
        if(Bukkit.getServer().getPlayer(player).isOnline()){
            Bukkit.getServer().getPlayer(player).damage(0.25);
        }
    }
}
