package com.talesdev.talesz.mobsystem;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

import java.util.ArrayList;

/**
 * Mob Decorator
 * Created by MoKunz on 3/8/2015.
 */
public class MobDecorator {
    private static ArrayList<MobDecoratorInterface> mobDecorator = new ArrayList<>();

    public static void decorate(Entity entity) {
        EntityType type = entity.getType();
        ArrayList<MobDecoratorInterface> decorator = findEntityDecorator(type);
        if (decorator.size() > 0) {
            for (MobDecoratorInterface md : decorator) {
                md.decorate(entity);
            }
        }

    }

    public static void addMobDecorator(MobDecoratorInterface mobDecoratorInterface) {
        mobDecorator.add(mobDecoratorInterface);
    }

    public static ArrayList<MobDecoratorInterface> findEntityDecorator(EntityType type) {
        ArrayList<MobDecoratorInterface> decorator = new ArrayList<>();
        for (MobDecoratorInterface md : mobDecorator) {
            if (md.getDecoratableEntityType().contains(type)) {
                decorator.add(md);
            }
        }
        return decorator;
    }
}
