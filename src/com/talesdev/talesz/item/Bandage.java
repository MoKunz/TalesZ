package com.talesdev.talesz.item;

import com.talesdev.talesz.bleeding.Bleeding;
import com.talesdev.talesz.itemsystem.TalesZToolItem;
import org.bukkit.Material;
import org.bukkit.entity.Player;
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
 * Bandage
 * Created by MoKunz on 2/27/2015.
 */
public class Bandage implements TalesZToolItem{
    @Override
    public void handleEvent(BlockBreakEvent event) {

    }

    @Override
    public String getName() {
        return "Bandage";
    }

    @Override
    public Material getType() {
        return Material.PAPER;
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
        itemMeta.setDisplayName("Bandage");
        return itemMeta;
    }

    @Override
    public MaterialData configMaterialData(MaterialData materialData) {
        return null;
    }

    @Override
    public void handleEvent(InventoryClickEvent event) {
        ItemStack current = event.getCurrentItem();
        ItemStack cursor = event.getCursor();

        // We want to prevent the player from INCREASING a stack size
        if (current != null && cursor != null) {

            int maxAmount = 1;

            // They should be able to split "too large" stacks, or move them around.
            if (cursor.getAmount() == 0 || event.isRightClick()) {
                return;
            } else if (!isValid(current, cursor, maxAmount)) {
                // But never increase a stack above their personal limit
                event.setCancelled(true);
            }
        }
    }
    private boolean isValid(ItemStack current, ItemStack cursor, int maxAmount) {

        int total = current.getAmount() + cursor.getAmount();

        if (current.getType() == Material.COMPASS) {
            return total <= maxAmount;
        } else {
            return true;
        }
    }

    @Override
    public void handleEvent(PlayerInteractEntityEvent event) {

    }

    @Override
    public void handleEvent(PlayerInteractEvent event) {
        if(event.getAction().equals(Action.RIGHT_CLICK_AIR)){
            Player p = event.getPlayer();
            if(Bleeding.isBleeding(p.getName())){
                Bleeding.removeBleedingPlayer(p.getName());
                if(p.getHealth() + 1 <= p.getMaxHealth()){
                    p.setHealth(p.getHealth() + 1);
                }
                else{
                    p.setHealth(p.getMaxHealth());
                }
            }
            else{
                if(p.getHealth() + 2 <= p.getMaxHealth()){
                    p.setHealth(p.getHealth() + 2);
                }
                else{
                    p.setHealth(p.getMaxHealth());
                }
            }
            if(event.getItem().getAmount() > 1){
                event.getItem().setAmount(event.getItem().getAmount() - 1);
            }
            else{
                event.getPlayer().getInventory().remove(event.getItem());
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
        event.setCancelled(true);
        if(event.getEntity() instanceof Player){
            Player p = (Player) event.getEntity();

        }
    }

    @Override
    public boolean compare(ItemStack itemStack) {
        if(itemStack.hasItemMeta()){
            if(itemStack.getItemMeta().hasDisplayName()){
                if(itemStack.getItemMeta().getDisplayName().equals("Bandage")){
                    return true;
                }
            }
        }
        return false;
    }
    private void heal(Player player){
        if(Bleeding.isBleeding(player.getName())){
            Bleeding.removeBleedingPlayer(player.getName());
            if(player.getHealth() + 1 <= player.getMaxHealth()){
                player.setHealth(player.getHealth() + 1);
            }
            else{
                player.setHealth(player.getMaxHealth());
            }
        }
        else{
            if(player.getHealth() + 2 <= player.getMaxHealth()){
                player.setHealth(player.getHealth() + 2);
            }
            else{
                player.setHealth(player.getMaxHealth());
            }
        }
    }
}
