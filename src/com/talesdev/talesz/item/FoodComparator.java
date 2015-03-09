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

    public static Material[] getAllFood() {
        return new Material[]{
                Material.APPLE, Material.MUSHROOM_SOUP,
                Material.BREAD, Material.PORK,
                Material.GRILLED_PORK, Material.GOLDEN_APPLE,
                Material.RAW_FISH, Material.COOKED_FISH,
                Material.CAKE, Material.COOKIE,
                Material.MELON, Material.RAW_BEEF,
                Material.COOKED_BEEF, Material.RAW_CHICKEN,
                Material.COOKED_CHICKEN, Material.ROTTEN_FLESH,
                Material.SPIDER_EYE, Material.CARROT,
                Material.POTATO_ITEM, Material.BAKED_POTATO,
                Material.POISONOUS_POTATO, Material.PUMPKIN_PIE,
                Material.RABBIT, Material.COOKED_RABBIT,
                Material.RABBIT_STEW, Material.COOKED_MUTTON
        };
    }
}
