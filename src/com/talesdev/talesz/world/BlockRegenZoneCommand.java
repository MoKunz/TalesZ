package com.talesdev.talesz.world;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Block zone command
 * Created by MoKunz on 3/14/2015.
 */
public class BlockRegenZoneCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("blockzone")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (args != null) {
                    if (args.length > 1) {
                        if (args[0].equalsIgnoreCase("add")) {
                            if (TalesZWorld.getBlockZone(args[1]) != null) {
                                player.sendMessage(ChatColor.RED + "Zone already exists!");
                                return true;
                            }
                            WorldEditPlayerSelection selection = new WorldEditPlayerSelection(player);
                            if (selection.isSelectionAvailable()) {
                                BlockZone blockZone = new BlockZone(args[1], selection.getSelection(), new BlockZoneRule());
                                TalesZWorld.addBlockZone(blockZone);
                            } else {
                                player.sendMessage(ChatColor.RED + "You must select region before adding new zone!");
                            }
                        }
                    }
                }
            } else {
                sender.sendMessage(ChatColor.RED + "Error : Invalid sender!");
            }
            return true;
        }
        return false;
    }
}
