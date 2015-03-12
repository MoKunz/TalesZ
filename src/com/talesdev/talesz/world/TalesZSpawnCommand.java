package com.talesdev.talesz.world;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TalesZSpawnCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("taleszspawn")) {
            if(sender instanceof Player){
                Player player = (Player) sender;
                boolean result = player.teleport(TalesZWorld.getSpawnPointManager().randomSpawnLocation());
                // teleport successful
                if(result){
                    player.sendMessage(ChatColor.GREEN + "You have been teleported to TalesZ World!");
                }
                // fail
                else{
                    player.sendMessage(ChatColor.RED + "Fail to teleport!");
                }
            }
            else{
                sender.sendMessage(ChatColor.RED + "Console can't warp!");
            }
            return true;
        }
        else{
            return false;
        }
    }
}
