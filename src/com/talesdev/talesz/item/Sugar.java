package com.talesdev.talesz.item;

import com.talesdev.talesz.Main;
import com.talesdev.talesz.itemsystem.TalesZItem;
import com.talesdev.talesz.itemsystem.TalesZItemUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/**
 * Sugar
 * Created by MoKunz on 2/28/2015.
 */
public class Sugar implements TalesZItem {

    @Override
    public String getName() {
        return "Sugar";
    }

    @Override
    public Material getType() {
        return Material.SUGAR;
    }

    @Override
    public int getAmount() {
        return 1;
    }

    @Override
    public short getDurability() {
        return 0;
    }

    @Override
    public ItemMeta configItemMeta(ItemMeta itemMeta) {
        itemMeta.setDisplayName(ChatColor.WHITE + "Sugar");
        return itemMeta;
    }

    @Override
    public MaterialData configMaterialData(MaterialData materialData) {
        return materialData;
    }

    @Override
    public void handleEvent(InventoryClickEvent event) {

    }

    @Override
    public void handleEvent(PlayerInteractEntityEvent event) {

    }

    @Override
    public void handleEvent(PlayerInteractEvent event) {
        if (TalesZItemUtil.isActionRightClick(event.getAction())) {
            final Player p = event.getPlayer();
            if (!(p.hasPotionEffect(PotionEffectType.SPEED) || p.hasPotionEffect(PotionEffectType.BLINDNESS) || p.hasPotionEffect(PotionEffectType.SLOW))) {
                p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 600, 1));
                TalesZItemUtil.removeOneItemFromPlayer(event.getPlayer(), event.getItem());
                Bukkit.getScheduler().runTaskLater(Main.getPlugin(), new Runnable() {
                    @Override
                    public void run() {
                        p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 200, 0));
                        p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 200, 0));
                    }
                }, 600L);
            }
        }
    }

    @Override
    public void handleEvent(PlayerItemConsumeEvent event) {

    }

    @Override
    public void handleEvent(PlayerDropItemEvent event) {

    }

    @Override
    public void handleEvent(EntityDamageByEntityEvent event) {

    }

    @Override
    public boolean compare(ItemStack itemStack) {
        return itemStack.getType().equals(Material.SUGAR);
    }
}
