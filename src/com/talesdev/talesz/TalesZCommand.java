package com.talesdev.talesz;

import com.talesdev.talesz.itemsystem.TalesZItemFactory;
import com.talesdev.talesz.itemsystem.TalesZItemUtil;
import com.talesdev.talesz.mobsystem.MobRuleManager;
import com.talesdev.talesz.thirst.Thirst;
import com.talesdev.talesz.thirst.ThirstDamage;
import com.talesdev.talesz.world.BlockRuleManager;
import com.talesdev.talesz.world.LocationString;
import com.talesdev.talesz.world.TalesZWorld;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Biome;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

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
                        commandSender.sendMessage(ChatColor.GREEN + "Thirst data reloaded!");
                        Thirst.getThirstRule().loadRule();
                    } else if (args[0].equalsIgnoreCase("saveThirst")) {
                        try {
                            Thirst.saveAll();
                            commandSender.sendMessage(ChatColor.GREEN + "Thirst data saved!");
                        } catch (IOException e) {
                            commandSender.sendMessage(ChatColor.RED + "Error while saving thirst data. Check console for more details");
                            e.printStackTrace();
                        }
                    } else if (args[0].equalsIgnoreCase("getThirstRule")) {
                        try {
                            Biome biome = Biome.valueOf(args[1]);
                            commandSender.sendMessage("Biome \"" + biome.toString() + "\" : " + Thirst.getThirstRule().getBiomeRule(biome));
                        } catch (IllegalArgumentException exception) {
                            commandSender.sendMessage(ChatColor.RED + "Error : Invalid arguments!");
                        }
                    } else if (args[0].equalsIgnoreCase("setThirstRule")) {
                        if (args.length > 2) {
                            try {
                                Biome biome = Biome.valueOf(args[1]);
                                Thirst.getThirstRule().setBiomeRule(biome, Integer.parseInt(args[2]));
                                Thirst.getThirstRule().saveRule();
                                commandSender.sendMessage(ChatColor.GREEN + "ThirstRule of biome " +
                                                ChatColor.BLUE + args[1] + ChatColor.GREEN + " set to " + Integer.parseInt(args[2]) + "!"
                                );
                            } catch (IllegalArgumentException exception) {
                                commandSender.sendMessage(ChatColor.RED + "Error : Invalid arguments!");
                            }
                        }
                    } else if (args[0].equalsIgnoreCase("clearThirstDamage")) {
                        commandSender.sendMessage(ChatColor.GREEN + "Thirst damage queue cleared!");
                        ThirstDamage.clear();
                    } else if (args[0].equalsIgnoreCase("reload")) {
                        MobRuleManager.start();
                        BlockRuleManager.readConfigFile();
                        Thirst.getThirstRule().loadRule();
                        TalesZMainConfig.load("plugins/TalesZ/config.yml");
                        commandSender.sendMessage(ChatColor.GREEN + "Config reloaded!");
                    } else if (args[0].equalsIgnoreCase("giveItem")) {
                        if (args.length > 2 && PlayerUtil.isValidPlayer(args[1])) {
                            ItemStack itemStack = TalesZItemFactory.createItem(args[2]);
                            if (itemStack.getType() != Material.AIR) {
                                Bukkit.getPlayer(args[1]).getInventory().addItem(itemStack);
                                commandSender.sendMessage(ChatColor.GREEN + "Give " + ChatColor.BLUE + args[2] + ChatColor.GREEN + " to " + args[1]);
                            }
                        } else if (args.length > 1) {
                            if (commandSender instanceof Player) {
                                Player player = (Player) commandSender;
                                ItemStack itemStack = TalesZItemFactory.createItem(args[1]);
                                if (itemStack.getType() != Material.AIR) {
                                    player.getInventory().addItem(itemStack);
                                    commandSender.sendMessage(ChatColor.GREEN + "Give " + ChatColor.BLUE + args[1] + ChatColor.GREEN + " to you");
                                }
                            }
                        } else {
                            commandSender.sendMessage(ChatColor.RED + "Error : Too few arguments!");
                        }
                    } else if (args[0].equalsIgnoreCase("updateInventory")) {
                        if (commandSender instanceof Player) {
                            if (args.length > 1) {
                                if (PlayerUtil.isValidPlayer(args[1])) {
                                    Bukkit.getScheduler().runTask(TalesZ.getPlugin(), Bukkit.getPlayer(args[1])::updateInventory);
                                    commandSender.sendMessage(ChatColor.GREEN + args[1] + "\'s inventory has been updated!");
                                }
                            } else {
                                Player player = (Player) commandSender;
                                Bukkit.getScheduler().runTask(TalesZ.getPlugin(), player::updateInventory);
                                player.sendMessage(ChatColor.GREEN + "Your inventory has been updated!");
                            }
                        } else {
                            if (args.length > 1) {
                                if (PlayerUtil.isValidPlayer(args[1])) {
                                    Bukkit.getScheduler().runTask(TalesZ.getPlugin(), Bukkit.getPlayer(args[1])::updateInventory);
                                    commandSender.sendMessage(ChatColor.GREEN + args[1] + "\'s inventory has been updated!");
                                }
                            } else {
                                commandSender.sendMessage("You must specific the player to update inventory!");
                            }
                        }
                    } else if (args[0].equalsIgnoreCase("chest")) {
                        if (args.length > 1) {
                            if (args[1].equalsIgnoreCase("get")) {
                                if (args.length > 2) {
                                    if (TalesZWorld.getChestLocationList().containsChest(args[2])) {
                                        // TODO : Rewrite /talesz chest get command
                                        commandSender.sendMessage(ChatColor.RED + "Command not implemented!");
                                    } else {
                                        commandSender.sendMessage(ChatColor.RED + "Error : Chest type not found!");
                                    }
                                }
                            } else if (args[1].equalsIgnoreCase("add")) {
                                if (args.length > 3) {
                                    if (commandSender instanceof Player) {
                                        // TODO : Rewrite /talesz chest add command
                                        commandSender.sendMessage(ChatColor.RED + "Command not implemented!");
                                    } else {
                                        commandSender.sendMessage(ChatColor.RED + "Error : Invalid sender!");
                                    }
                                } else {
                                    commandSender.sendMessage(ChatColor.RED + "Error : Too few arguments!");
                                }
                            } else {
                                commandSender.sendMessage(ChatColor.RED + "Error : Invalid arguments!");
                            }
                        } else {
                            commandSender.sendMessage(ChatColor.RED + "Error : Too few arguments!");
                        }
                    } else if (args[0].equalsIgnoreCase("debug")) {
                        // for debug purpose only
                        long t1 = System.nanoTime();
                        // code start
                        System.out.println(TalesZWorld.getChestLocationList().getChestType(LocationString.fromString("0.0,100.0,0.0").getLocation()));
                        // code end
                        long t2 = System.nanoTime() - t1;
                        System.out.println("Execution time : " + (t2 / 1000000D) + " MS");
                        // end
                        commandSender.sendMessage(ChatColor.GREEN + "Debug output printed to console!");
                    } else if (args[0].equalsIgnoreCase("setMaxStack")) {
                        if (args.length > 1) {
                            // max stack size
                            int maxStackSize = 0;
                            if (TalesZItemUtil.isValidMaterialString(args[1])) {
                                try {
                                    maxStackSize = Integer.parseInt(args[2]);
                                } catch (NumberFormatException exception) {
                                    commandSender.sendMessage(ChatColor.RED + "Error : Invalid number format");
                                    return true;
                                }
                                Material material = Material.getMaterial(args[1].toUpperCase());
                                TalesZItemUtil.setMaxStackSize(material, maxStackSize);
                                commandSender.sendMessage(
                                        ChatColor.GREEN + "Max stack size of " +
                                                ChatColor.BLUE + material.toString() +
                                                ChatColor.GREEN + "changed to " + ChatColor.BLUE + maxStackSize
                                );
                            } else {
                                try {
                                    maxStackSize = Integer.parseInt(args[1]);
                                } catch (NumberFormatException exception) {
                                    commandSender.sendMessage(ChatColor.RED + "Error : Invalid number format");
                                    return true;
                                }
                                if (commandSender instanceof Player) {
                                    Player p = (Player) commandSender;
                                    if (p.getItemInHand() != null && p.getItemInHand().getType() != Material.AIR) {
                                        p.setItemInHand(TalesZItemUtil.setMaxStackSize(p.getItemInHand(), maxStackSize));
                                        commandSender.sendMessage(ChatColor.GREEN + "Max stack size changed to " + ChatColor.BLUE + maxStackSize);
                                    } else {
                                        commandSender.sendMessage(ChatColor.RED + "Error : You must have item in the hand ");
                                    }
                                } else {
                                    commandSender.sendMessage(ChatColor.RED + "Error : Invalid sender");
                                }
                            }
                        }
                    } else if (args[0].equalsIgnoreCase("FoodRule")) {
                        if (args.length > 1) {
                            if (TalesZItemUtil.isValidMaterialString(args[1].toUpperCase())) {
                                if (args.length > 2) {
                                    try {
                                        Thirst.getThirstRule().setFoodRule(Material.getMaterial(args[1].toUpperCase()), Integer.parseInt(args[2]));
                                        Thirst.getThirstRule().saveRule();
                                        commandSender.sendMessage(ChatColor.GREEN + "Food \"" + args[1].toUpperCase() + "\" set!");
                                    } catch (NumberFormatException ignored) {
                                        commandSender.sendMessage(ChatColor.RED + "Error : Invalid number format!");
                                    }
                                } else {
                                    commandSender.sendMessage(ChatColor.YELLOW + "Food \"" + args[1].toUpperCase() + "\" : " + Thirst.getThirstRule().getFoodRule(Material.getMaterial(args[1].toUpperCase())));
                                }
                            } else if (args[1].equalsIgnoreCase("load")) {
                                commandSender.sendMessage(ChatColor.YELLOW + "Use /talesz reloadthirstrule instead");
                            } else if (args[1].equalsIgnoreCase("save")) {
                                commandSender.sendMessage(ChatColor.YELLOW + "Use /talesz savethirst instead");
                            } else {
                                commandSender.sendMessage(ChatColor.RED + "Error : Material \"" + args[1] + "\" not found!");
                            }
                        } else {
                            commandSender.sendMessage(ChatColor.RED + "Error : Too few arguments.");
                        }
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
                                                BlockRuleManager.saveConfigFile();
                                                commandSender.sendMessage(ChatColor.GREEN + "Breaking rule of " +
                                                                ChatColor.BLUE + args[2] + ChatColor.GREEN + " set to" + args[4].toUpperCase() + "!"
                                                );
                                            } else if (args[3].equalsIgnoreCase("placing")) {
                                                BlockRuleManager.setPlacingRule(Material.getMaterial(args[2].toUpperCase()), Rule.valueOf(args[4].toUpperCase()));
                                                BlockRuleManager.saveConfigFile();
                                                commandSender.sendMessage(ChatColor.GREEN + "Placing rule of " +
                                                                ChatColor.BLUE + args[2] + ChatColor.GREEN + " set to" + args[4].toUpperCase() + "!"
                                                );
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
                            BlockRuleManager.readConfigFile();
                            commandSender.sendMessage(ChatColor.GREEN + "Rule Config file reloaded.");
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
