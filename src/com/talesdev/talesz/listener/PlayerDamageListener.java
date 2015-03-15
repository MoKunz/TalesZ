package com.talesdev.talesz.listener;

import com.talesdev.talesz.RandomUtil;
import com.talesdev.talesz.bleeding.Bleeding;
import com.talesdev.talesz.item.GrapplingHookDamageManager;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

/**
 * Player damage listener
 * Created by MoKunz on 3/9/2015.
 */
public class PlayerDamageListener implements Listener {
    @EventHandler
    public void onPlayerDamaged(EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof Player) {
            Player p = (Player) event.getEntity();
            if (event.getDamager() instanceof LivingEntity) {
                // random bleeding chance
                if (RandomUtil.randomPercent(5)) {
                    // start bleeding
                    if (!Bleeding.isBleeding(p.getName())) {
                        Bleeding.addBleedingPlayer(p.getName());
                    }
                }
            }
            if (GrapplingHookDamageManager.contains(p.getName())) {
                event.setDamage(event.getDamage() / 2);
                GrapplingHookDamageManager.removeReduceDamage(p.getName());
            }
        }
    }
}
