package com.talesdev.talesz.mob;

import net.minecraft.server.v1_8_R1.EntityZombie;
import net.minecraft.server.v1_8_R1.World;

/**
 * MineZ Custom Entity
 * Created by MoKunz on 3/9/2015.
 */
public class MineZEntityZombie extends EntityZombie {
    public MineZEntityZombie(World world) {
        super(world);
    }

    @Override
    protected boolean m_() {
        return true;
    }
}
