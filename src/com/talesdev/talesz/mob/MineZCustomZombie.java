package com.talesdev.talesz.mob;

import com.talesdev.talesz.TalesZ;
import com.talesdev.talesz.mobsystem.MobDecoratorInterface;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Zombie;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

/**
 * Custom Zombie
 * Created by MoKunz on 3/8/2015.
 */
public class MineZCustomZombie implements MobDecoratorInterface {
    @Override
    public List<EntityType> getDecoratableEntityType() {
        ArrayList<EntityType> entityTypes = new ArrayList<>();
        entityTypes.add(EntityType.ZOMBIE);
        return entityTypes;
    }

    @Override
    public Entity decorate(Entity entity) {
        if (entity instanceof Zombie) {
            Zombie zombie = (Zombie) entity;
            zombie.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 1, true, false));
            zombie.setMetadata("MineZCustomZombie", new FixedMetadataValue(TalesZ.getPlugin(), true));
            zombie.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 0, true, false));
            zombie.setMaxHealth(20.0D);
        }
        return entity;
    }

    @Override
    public Entity passiveDecorate(Entity entity, int tickPassed) {
        return entity;
    }
}
