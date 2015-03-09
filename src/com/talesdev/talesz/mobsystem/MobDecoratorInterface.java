package com.talesdev.talesz.mobsystem;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

import java.util.List;

/**
 * Mob Decorator Interface
 * Created by MoKunz on 3/8/2015.
 */
public interface MobDecoratorInterface {
    public List<EntityType> getDecoratableEntityType();

    public Entity decorate(Entity entity);

    public Entity passiveDecorate(Entity entity, int tickPassed);
}
