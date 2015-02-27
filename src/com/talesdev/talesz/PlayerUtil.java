package com.talesdev.talesz;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * Player util
 * Created by MoKunz on 2/27/2015.
 */
public class PlayerUtil {
    public static boolean isValidPlayer(String playerName){
        for(Player p : Bukkit.getServer().getOnlinePlayers()){
            if(p.getName().equalsIgnoreCase(playerName)){
                return true;
            }
        }
        return false;
    }
}
