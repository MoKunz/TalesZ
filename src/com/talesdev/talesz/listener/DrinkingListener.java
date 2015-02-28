package com.talesdev.talesz.listener;

import com.talesdev.talesz.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Collection;

/**
 * Potion Drinking Listener
 * Created by MoKunz on 2/27/2015.
 */
public class DrinkingListener implements Listener {
    @EventHandler
    public void onDrink(PlayerItemConsumeEvent event) {
        ItemStack itemStack = event.getItem();
        if (itemStack.getType().equals(Material.POTION)) {
            if (itemStack.getDurability() != 0) {
                Bukkit.getScheduler().runTask(Main.getPlugin(), new Runnable() {
                    @Override
                    public void run() {
                        event.getPlayer().setItemInHand(new ItemStack(Material.AIR));
                    }
                });
            }
        } else if (itemStack.getType().equals(Material.MILK_BUCKET)) {
            event.setCancelled(true);
            event.getPlayer().setItemInHand(new ItemStack(Material.AIR));
            Collection<PotionEffect> effectCollection = event.getPlayer().getActivePotionEffects();
            for (PotionEffect potionEffect : effectCollection) {
                if (isDebuffEffect(potionEffect)) {
                    event.getPlayer().removePotionEffect(potionEffect.getType());
                }
            }
        }
    }

    @EventHandler
    public void onDrinkMilk(PlayerBucketEmptyEvent emptyEvent) {

    }

    private boolean isDebuffEffect(PotionEffect effect) {
        PotionEffectType type = effect.getType();
        return type.equals(PotionEffectType.HARM) ||
                type.equals(PotionEffectType.BLINDNESS) ||
                type.equals(PotionEffectType.HUNGER) ||
                type.equals(PotionEffectType.POISON) ||
                type.equals(PotionEffectType.SLOW) ||
                type.equals(PotionEffectType.SLOW_DIGGING) ||
                type.equals(PotionEffectType.WEAKNESS) ||
                type.equals(PotionEffectType.CONFUSION) ||
                type.equals(PotionEffectType.WITHER);
    }
}
