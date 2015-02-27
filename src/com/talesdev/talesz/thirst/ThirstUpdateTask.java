package com.talesdev.talesz.thirst;

/**
 * Thirst update task
 * Created by MoKunz on 17/10/2557.
 */
public class ThirstUpdateTask implements Runnable{
    @Override
    public void run() {
        Thirst.updateAll();
    }
}
