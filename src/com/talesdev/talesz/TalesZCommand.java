package com.talesdev.talesz;

import com.talesdev.talesz.thirst.Thirst;
import org.bukkit.ChatColor;
import org.bukkit.block.Biome;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * TalesZ Main Command
 * Created by MoKunz on 3/1/2015.
 */
public class TalesZCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("talesz")) {
            if (args != null) {
                if (args.length > 0) {
                    if (args[0].equalsIgnoreCase("reloadThirstRule")) {
                        Thirst.getThirstRule().loadRule();
                    } else if (args[0].equalsIgnoreCase("getThirstRule")) {
                        try {
                            Biome biome = Biome.valueOf(args[1]);
                            commandSender.sendMessage("Biome \"" + biome.toString() + "\" : " + Thirst.getThirstRule().getRule(biome));
                        } catch (IllegalArgumentException exception) {
                            commandSender.sendMessage(ChatColor.RED + "Error : Invalid arguments!");
                        }
                    } else if (args[0].equalsIgnoreCase("setThirstRule")) {
                        if (args.length > 2) {
                            try {
                                Biome biome = Biome.valueOf(args[1]);
                                Thirst.getThirstRule().setRule(biome, Integer.parseInt(args[2]));
                                Thirst.getThirstRule().saveRule();
                            } catch (IllegalArgumentException exception) {
                                commandSender.sendMessage(ChatColor.RED + "Error : Invalid arguments!");
                            }
                        }
                    } else {
                        commandSender.sendMessage(ChatColor.YELLOW + command.getUsage());
                    }
                } else {
                    commandSender.sendMessage(ChatColor.YELLOW + command.getDescription());
                }
            } else {
                commandSender.sendMessage(ChatColor.YELLOW + command.getDescription());
            }
            return true;
        }
        return false;
    }
}
