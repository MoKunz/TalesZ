package com.talesdev.talesz.item;

import com.talesdev.talesz.itemsystem.MaterialComparatorInterface;
import org.bukkit.Material;

/**
 * Food item comparator
 * Created by MoKunz on 3/8/2015.
 */
public class FoodComparator implements MaterialComparatorInterface {
    @Override
    public boolean contain(Material material) {
        return material.equals(Material.APPLE) || material.equals(Material.MUSHROOM_SOUP) ||
                material.equals(Material.BREAD) || material.equals(Material.PORK) ||
                material.equals(Material.GRILLED_PORK) || material.equals(Material.GOLDEN_APPLE) ||
                material.equals(Material.RAW_FISH) || material.equals(Material.COOKED_FISH) ||
                material.equals(Material.CAKE) || material.equals(Material.COOKIE) ||
                material.equals(Material.MELON) || material.equals(Material.RAW_BEEF) ||
                material.equals(Material.COOKED_BEEF) || material.equals(Material.RAW_CHICKEN) ||
                material.equals(Material.COOKED_CHICKEN) || material.equals(Material.ROTTEN_FLESH) ||
                material.equals(Material.SPIDER_EYE) || material.equals(Material.CARROT) ||
                material.equals(Material.POTATO_ITEM) || material.equals(Material.BAKED_POTATO) ||
                material.equals(Material.POISONOUS_POTATO) || material.equals(Material.PUMPKIN_PIE) ||
                material.equals(Material.RABBIT) || material.equals(Material.COOKED_RABBIT) ||
                material.equals(Material.RABBIT_STEW) || material.equals(Material.COOKED_MUTTON);
    }
}
