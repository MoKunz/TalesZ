package com.talesdev.talesz.world;

/**
 * Regeneration task
 * Created by MoKunz on 3/10/2015.
 */
public class BlockRegenerationTask implements Runnable {
    @Override
    public void run() {
        BlockRegenerator.update();
    }
}
