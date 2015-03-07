package com.talesdev.talesz;

import com.talesdev.talesz.itemsystem.TalesZItemUtil;
import com.talesdev.talesz.thirst.Thirst;
import com.talesdev.talesz.thirst.ThirstDamage;
import com.talesdev.talesz.world.BlockRuleManager;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Biome;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.io.IOException;

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
                    } else if (args[0].equalsIgnoreCase("saveThirst")) {
                        try {
                            Thirst.saveAll();
                        } catch (IOException e) {
                            commandSender.sendMessage(ChatColor.RED + "Error while saving thirst data. Check console for more details");
                            e.printStackTrace();
                        }
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
                    } else if (args[0].equalsIgnoreCase("clearThirstDamage")) {
                        ThirstDamage.clear();
                    } else if (args[0].equalsIgnoreCase("Rule")) {
                        if (args.length > 2) {
                            if (args[1].equalsIgnoreCase("get") || args[1].equalsIgnoreCase("set")) {
                                if (TalesZItemUtil.isValidMaterialString(args[2].toUpperCase())) {
                                    if (args[1].equalsIgnoreCase("get")) {
                                        commandSender.sendMessage(ChatColor.YELLOW + "Block rule of " + args[2].toUpperCase());
                                        commandSender.sendMessage(ChatColor.BLUE + "Breaking : " + ChatColor.GREEN + BlockRuleManager.getBreakingRule(Material.getMaterial(args[2].toUpperCase())).toString());
                                        commandSender.sendMessage(ChatColor.BLUE + "Placing : " + ChatColor.GREEN + BlockRuleManager.getPlacingRule(Material.getMaterial(args[2].toUpperCase())).toString());
                                    } else if (args[1].equalsIgnoreCase("set") && args.length > 4) {
                                        if (args[4].equalsIgnoreCase("allow") || args[4].equalsIgnoreCase("deny")) {
                                            if (args[3].equalsIgnoreCase("breaking")) {
                                                BlockRuleManager.setBreakingRule(Material.getMaterial(args[2].toUpperCase()), Rule.valueOf(args[4].toUpperCase()));
                                            } else if (args[3].equalsIgnoreCase("placing")) {
                                                BlockRuleManager.setPlacingRule(Material.getMaterial(args[2].toUpperCase()), Rule.valueOf(args[4].toUpperCase()));
                                            } else {
                                                commandSender.sendMessage(ChatColor.RED + "Error : Expected argument to be placing or breaking but found \"" + args[3] + "\"!");
                                            }
                                        } else {
                                            commandSender.sendMessage(ChatColor.RED + "Error : Expected argument to be ALLOW or DENY but found \"" + args[4] + "\"!");

                                        }
                                    }
                                } else {
                                    commandSender.sendMessage(ChatColor.RED + "Error : Material \"" + args[2] + "\" not found!");
                                }
                            } else {
                                commandSender.sendMessage(ChatColor.RED + "Error : Invalid arguments.");
                            }
                        } else if (args[1].equalsIgnoreCase("load")) {
                            commandSender.sendMessage(ChatColor.GREEN + "Rule Config file reloaded.");
                            BlockRuleManager.readConfigFile();
                        } else if (args[1].equalsIgnoreCase("save")) {
                            BlockRuleManager.saveConfigFile();
                            commandSender.sendMessage(ChatColor.GREEN + "Rule Config file saved.");
                        } else {
                            commandSender.sendMessage(ChatColor.RED + "Error : Invalid arguments.");
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
