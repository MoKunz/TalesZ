package com.talesdev.talesz.item;

import com.talesdev.talesz.itemsystem.TalesZItemUtil;
import com.talesdev.talesz.itemsystem.TalesZToolItem;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.Dye;
import org.bukkit.material.MaterialData;

/**
 * TalesZToolItem
 * Created by MoKunz on 2/27/2015.
 */
public class HealingOintment implements TalesZToolItem {
    @Override
    public void handleEvent(BlockBreakEvent event) {

    }

    @Override
    public String getName() {
        return "HealingOintment";
    }

    @Override
    public Material getType() {
        return Material.INK_SACK;
    }

    @Override
    public int getAmount() {
        return 1;
    }

    @Override
    public short getDurability() {
        return 1;
    }

    @Override
    public ItemMeta configItemMeta(ItemMeta itemMeta) {
        itemMeta.setDisplayName(ChatColor.RED + "Healing Ointment");
        return itemMeta;
    }

    @Override
    public MaterialData configMaterialData(MaterialData materialData) {
        Dye dye = (Dye) materialData;
        dye.setColor(DyeColor.RED);
        return dye;
    }

    @Override
    public void handleEvent(InventoryClickEvent event) {

    }

    @Override
    public void handleEvent(PlayerInteractEntityEvent event) {

    }

    @Override
    public void handleEvent(PlayerInteractEvent event) {
        if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK) || event.getAction().equals(Action.RIGHT_CLICK_AIR)) {
            event.setUseItemInHand(Event.Result.DENY);
            TalesZItemUtil.heal(event.getPlayer(), 10);
            TalesZItemUtil.removeOneItemFromPlayer(event.getPlayer(), event.getItem());
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
        if (event.getEntity() instanceof Player) {
            event.setCancelled(true);
            Player p = (Player) event.getEntity();
            TalesZItemUtil.heal(p, 10);
            TalesZItemUtil.removeOneItemFromPlayer(p, p.getItemInHand());
        }
    }

    @Override
    public boolean compare(ItemStack itemStack) {
        if (itemStack.getType().equals(Material.INK_SACK) && itemStack.hasItemMeta()) {
            ItemMeta meta = itemStack.getItemMeta();
            if (meta.hasDisplayName()) {
                if (ChatColor.stripColor(meta.getDisplayName()).equals("Healing Ointment")) {
                    if (itemStack.getDurability() == 1) {

                    }
                }
            }
        }
        return false;
    }
}
