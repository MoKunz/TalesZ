package com.talesdev.talesz.bleeding;

/**
 * Bleeding update task
 * Created by MoKunz on 18/10/2557.
 */
public class BleedingUpdateTask implements Runnable{
    @Override
    public void run() {
        Bleeding.updateAll(2.0);
    }
}
