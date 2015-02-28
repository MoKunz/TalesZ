package com.talesdev.talesz.item;

/**
 * Iron door updating task
 * Created by MoKunz on 2/28/2015.
 */
public class IronDoorUpdateTask implements Runnable {
    @Override
    public void run() {
        IronDoorManager.updateTime();
        IronDoorManager.processIronDoor();
    }
}
