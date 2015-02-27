package com.talesdev.talesz.item;

import com.talesdev.talesz.itemsystem.TalesZToolItem;
import org.bukkit.Material;
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
import org.bukkit.material.MaterialData;

/**
 * Button
 */
public class Button implements TalesZToolItem {
    @Override
    public void handleEvent(BlockBreakEvent event) {

    }

    @Override
    public String getName() {
        return "Button";
    }

    @Override
    public Material getType() {
        return Material.STONE_BUTTON;
    }

    @Override
    public int getAmount() {
        return 1;
    }

    @Override
    public long getDurability() {
        return 0;
    }

    @Override
    public ItemMeta configItemMeta(ItemMeta itemMeta) {
        return null;
    }

    @Override
    public MaterialData configMaterialData(MaterialData materialData) {
        return null;
    }

    @Override
    public void handleEvent(InventoryClickEvent event) {

    }

    @Override
    public void handleEvent(PlayerInteractEntityEvent event) {

    }

    @Override
    public void handleEvent(PlayerInteractEvent event) {
        if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            IronDoorUtil.open(event.getClickedBlock());
            event.setUseItemInHand(Event.Result.DENY);
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
        return itemStack.getType().equals(Material.STONE_BUTTON);
    }
}
