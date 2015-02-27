package com.talesdev.talesz.itemsystem;

import com.talesdev.talesz.PlayerUtil;
import com.talesdev.talesz.exp.ExpBarUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * TalesZ Item Command
 * Created by MoKunz on 2/26/2015.
 */
public class TalesZItemCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("taleszitem")) {
            if (args != null) {
                if (args.length >= 1) {
                    switch (args[0]) {
                        case "spawnItem":
                            if (args.length > 2 && PlayerUtil.isValidPlayer(args[1])){
                                Bukkit.getPlayer(args[1]).getInventory().addItem(TalesZItemFactory.createItem(args[2]));
                                commandSender.sendMessage(ChatColor.YELLOW + "Given " + ChatColor.AQUA + args[2] + ChatColor.YELLOW +
                                        " to " + ChatColor.GREEN + args[1] + ChatColor.YELLOW + "."
                                );
                            }
                            break;
                        default:
                            return false;
                    }
                    return true;
                }
                else{
                    return false;
                }
            }
            else{
                return false;
            }
        }
        return false;
    }
}
