package com.talesdev.talesz.itemsystem;

import com.talesdev.talesz.bleeding.Bleeding;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

/**
 * Item Util
 * Created by MoKunz on 2/27/2015.
 */
public class TalesZItemUtil {

    public static void removeOneItemFromPlayer(Player p, ItemStack itemStack) {
        if (itemStack.getAmount() > 1) {
            itemStack.setAmount(itemStack.getAmount() - 1);
        } else {
            p.getInventory().remove(itemStack);
        }
    }

    public static void removeInventoryItems(PlayerInventory inv, Material type, int amount) {
        for (ItemStack is : inv.getContents()) {
            if (is != null && is.getType() == type) {
                int newAmount = is.getAmount() - amount;
                if (newAmount > 0) {
                    is.setAmount(newAmount);
                    break;
                } else {
                    inv.remove(is);
                    amount = -newAmount;
                    if (amount == 0) break;
                }
            }
        }
    }
    public static boolean isValidMaterialString(String material) {
        if (Material.getMaterial(material) != null) {
            return true;
        }
        return false;
    }

    public static boolean isValidRuleString(String rule) {
        if (rule.toUpperCase().equalsIgnoreCase("allow") || rule.toUpperCase().equalsIgnoreCase("deny")) {
            return true;
        }
        return false;
    }
    public static boolean isActionRightClick(Action action) {
        return action.equals(Action.RIGHT_CLICK_AIR) || action.equals(Action.RIGHT_CLICK_BLOCK);
    }

    public static void heal(Player player, double amount) {
        Bleeding.removeBleedingPlayer(player.getName());
        if (player.getHealth() + amount <= player.getMaxHealth()) {
            player.setHealth(player.getHealth() + amount);
        } else {
            player.setHealth(player.getMaxHealth());
        }

    }

    public static MaterialComparator getRightClickableComparator() {
        return new MaterialComparator(material -> material.equals(Material.DISPENSER) ||
                material.equals(Material.NOTE_BLOCK) ||
                material.equals(Material.BED) ||
                material.equals(Material.CHEST) ||
                material.equals(Material.WORKBENCH) ||
                material.equals(Material.FURNACE) ||
                material.equals(Material.BURNING_FURNACE) ||
                material.equals(Material.WOODEN_DOOR) ||
                material.equals(Material.LEVER) ||
                material.equals(Material.JUKEBOX) ||
                material.equals(Material.DIODE_BLOCK_OFF) ||
                material.equals(Material.DIODE_BLOCK_ON) ||
                material.equals(Material.TRAP_DOOR) ||
                material.equals(Material.FENCE_GATE) ||
                material.equals(Material.ENCHANTMENT_TABLE) ||
                material.equals(Material.BREWING_STAND) ||
                material.equals(Material.CAULDRON) ||
                material.equals(Material.ENDER_CHEST) ||
                material.equals(Material.COMMAND) ||
                material.equals(Material.BEACON) ||
                material.equals(Material.ANVIL) ||
                material.equals(Material.TRAPPED_CHEST) ||
                material.equals(Material.REDSTONE_COMPARATOR_OFF) ||
                material.equals(Material.REDSTONE_COMPARATOR_ON) ||
                material.equals(Material.HOPPER) ||
                material.equals(Material.DROPPER) ||
                material.equals(Material.STONE_BUTTON) ||
                material.equals(Material.ACACIA_DOOR) ||
                material.equals(Material.SPRUCE_DOOR) ||
                material.equals(Material.BIRCH_DOOR) ||
                material.equals(Material.JUNGLE_DOOR) ||
                material.equals(Material.DARK_OAK_DOOR) ||
                material.equals(Material.ACACIA_FENCE_GATE) ||
                material.equals(Material.SPRUCE_FENCE_GATE) ||
                material.equals(Material.BIRCH_FENCE_GATE) ||
                material.equals(Material.JUNGLE_FENCE_GATE) ||
                material.equals(Material.DARK_OAK_FENCE_GATE) ||
                material.equals(Material.SPRUCE_FENCE_GATE) ||
                material.equals(Material.WOOD_BUTTON));
    }
}
