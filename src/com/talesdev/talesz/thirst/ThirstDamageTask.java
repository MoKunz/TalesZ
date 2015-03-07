package com.talesdev.talesz.thirst;

/**
 * Thirst Damage task
 * Created by MoKunz on 2/14/2015.
 */
public class ThirstDamageTask implements Runnable {
    @Override
    public void run() {
        ThirstDamage.update();
    }
}
