package com.talesdev.talesz.thirst;

import org.bukkit.Bukkit;

import java.util.ArrayList;

/**
 * Thirst damage
 * Created by MoKunz on 2/14/2015.
 */
public class ThirstDamage {
    public static final int THIRST_DAMAGE = 2;
    private static ArrayList<String> playerList = new ArrayList<>();
    public static void addToList(String playerName){
        playerList.add(playerName);
    }
    public static void removeFromList(String playerName){
        if(contain(playerName)){
            playerList.remove(playerList.indexOf(playerName));
        }
    }
    public static boolean contain(String playerName){
        return playerList.contains(playerName);
    }
    public static void update(){
        if(playerList != null && playerList.size() > 0){
            for(String playerName : playerList){
                if (playerName != null) {
                    if (Bukkit.getServer().getPlayer(playerName) != null) {
                        Bukkit.getServer().getPlayer(playerName).damage(THIRST_DAMAGE);
                    }
                }
            }
        }
    }
}
