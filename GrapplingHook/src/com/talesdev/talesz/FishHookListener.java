package com.talesdev.talesz;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

/**
 * Listener
 * Created by MoKunz on 3/12/2015.
 */
public class FishHookListener implements Listener {
    @EventHandler
    public void onFish(PlayerFishEvent event) {
        if (compare(event.getPlayer().getItemInHand())) {
            if (TalesZItemUtil.isHookLanded(event.getHook())) {
                Location loc = event.getHook().getLocation();
                if (event.getHook().getLocation().distance(event.getPlayer().getLocation()) < 3) {
                    Player p = event.getPlayer();
                    if (loc.getY() > p.getLocation().getY()) {
                        p.setVelocity(new Vector(0, 0.25, 0));
                    } else {
                        Location playerLoc = p.getLocation();
                        Vector vector = loc.toVector().subtract(playerLoc.toVector());
                        p.setVelocity(vector);
                    }
                } else {
                   /* Vector vector = event.getHook().getLocation().subtract(event.getPlayer().getLocation()).toVector();
                    vector.multiply(new Vector(0.2, 0.17, 0.2));
                    event.getPlayer().setVelocity(event.getPlayer().getVelocity().add(vector));*/

                    // begin
                    Player player = event.getPlayer();
                    Location entityLoc = player.getLocation();

                    entityLoc.setY(entityLoc.getY() + 0.5);
                    player.teleport(entityLoc);

                    double g = -0.08;
                    double t = loc.distance(entityLoc);
                    double vX = (1.0 + 0.07 * t) * (loc.getX() - entityLoc.getX()) / t;
                    double vY = (1.0 + 0.03 * t) * (loc.getY() - entityLoc.getY()) / t - 0.5 * g * t;
                    double vZ = (1.0 + 0.07 * t) * (loc.getZ() - entityLoc.getZ()) / t;

                    Vector v = player.getVelocity();
                    v.setX(vX);
                    v.setY(vY);
                    v.setZ(vZ);
                    player.setVelocity(v);
                    // end
                }
            }
        }
    }

    public boolean compare(ItemStack itemStack) {
        if (itemStack.hasItemMeta() && itemStack.getType().equals(Material.FISHING_ROD)) {
            if (itemStack.getItemMeta().hasDisplayName()) {
                if (ChatColor.stripColor(itemStack.getItemMeta().getDisplayName()).equalsIgnoreCase("Grappling Hook")) {
                    return true;
                }
            }
        }
        return false;
    }
}
