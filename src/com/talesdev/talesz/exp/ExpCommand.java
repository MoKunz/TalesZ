package com.talesdev.talesz.exp;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Command for testing purpose only
 * Created by MoKunz on 2/12/2015.
 */
public class ExpCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("xputil")) {
            if (commandSender instanceof Player) {
                Player p = (Player) commandSender;
                if (args != null) {
                    if (args.length >= 1) {
                        switch (args[0]) {
                            case "getExp":
                                p.sendMessage(ChatColor.GREEN + "Your XP : " + ChatColor.AQUA + p.getExp());
                                break;
                            case "getTotalExperience":
                                p.sendMessage(ChatColor.GREEN + "Your Total Experience : " + ChatColor.AQUA + p.getTotalExperience());
                                break;
                            case "getExpToLevel":
                                p.sendMessage(formatXpMessage("Your amount of xp required for next level", p.getExpToLevel()));
                                break;
                            case "setBar":
                                if (args.length > 2) {
                                    if (Integer.parseInt(args[1]) >= 0 && Double.parseDouble(args[2]) <= 100 && Double.parseDouble(args[2]) >= 0) {
                                        ExpBarUtil.apply(p, Integer.parseInt(args[1]), Double.parseDouble(args[2]));
                                    } else {
                                        p.sendMessage(ChatColor.RED + "Invalid arguments");
                                    }
                                }
                                break;
                            default:
                                return false;
                        }
                        return true;
                        // cmd
                    }
                }
            }
        } else {
            commandSender.sendMessage(ChatColor.RED + "Error : Invalid command sender!");
            return false;
        }
        return false;
    }

    private String formatXpMessage(String keyMessage, Object value) {
        return ChatColor.GREEN + keyMessage + " : " + ChatColor.AQUA + value;
    }
}
