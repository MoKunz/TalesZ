package com.talesdev.talesz.bleeding;

import com.talesdev.talesz.PlayerUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collection;

/**
 * Bleeding Command
 * Created by MoKunz on 2/25/2015.
 */
public class BleedingCommand implements CommandExecutor{
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        if(command.getName().equalsIgnoreCase("bleeding")){
            if(args !=null){
                if(args.length > 1){
                    switch(args[0]){
                        case "getBleeding" :
                            if(PlayerUtil.isValidPlayer(args[1])){
                                commandSender.sendMessage(ChatColor.YELLOW + "Is bleeding : " + ChatColor.RED + Bleeding.isBleeding(args[1]));
                            }
                            break;
                        case "addBleeding" :
                            if(PlayerUtil.isValidPlayer(args[1])){
                                Bleeding.addBleedingPlayer(args[1]);
                                commandSender.sendMessage(ChatColor.GREEN + args[1] + ChatColor.YELLOW + " is now bleeding");
                            }
                            break;
                        case "removeBleeding" :
                            if(PlayerUtil.isValidPlayer(args[1])){
                                Bleeding.removeBleedingPlayer(args[1]);
                                commandSender.sendMessage(ChatColor.YELLOW  + "Removed bleeding from " + ChatColor.GREEN + args[1]);
                            }
                            break;
                        default :
                            return false;
                    }
                    return true;
                }
            }
        }
        return false;
    }
}