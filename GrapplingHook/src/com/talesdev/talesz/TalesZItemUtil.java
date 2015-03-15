package com.talesdev.talesz;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Fish;
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
        return Material.getMaterial(material) != null;
    }

    public static boolean isValidRuleString(String rule) {
        return rule.toUpperCase().equalsIgnoreCase("ALLOW") || rule.toUpperCase().equalsIgnoreCase("DENY");
    }

    public static boolean isActionRightClick(Action action) {
        return action.equals(Action.RIGHT_CLICK_AIR) || action.equals(Action.RIGHT_CLICK_BLOCK);
    }

    public static boolean isHookLanded(Fish hook) {
        if (hook.getVelocity().length() > 1.2) {
            return false;
        } else {
            Location loc = hook.getLocation();
            World w = hook.getWorld();
            return !(!w.getBlockAt(loc.add(0.0, -0.25, 0.0)).getType().isSolid() && w.getBlockAt(loc.add(0.0, -0.25, 0.0)).getType() != Material.GRASS) || (!(!w.getBlockAt(loc.add(0.25, -0.25, 0.0)).getType().isSolid() && w.getBlockAt(loc.add(-0.25, -0.25, 0.0)).getType() != Material.GRASS) || w.getBlockAt(loc.add(0.0, -0.25, 0.25)).getType().isSolid() || w.getBlockAt(loc.add(0.0, -0.25, -0.25)).getType() == Material.GRASS);
        }
    }
}
