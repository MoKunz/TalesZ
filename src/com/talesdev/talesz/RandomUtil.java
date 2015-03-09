package com.talesdev.talesz;

/**
 * Random util
 * Created by MoKunz on 3/9/2015.
 */
public class RandomUtil {
    public static boolean randomPercent(double percent) {
        double random = Math.random() * 100;
        return random <= percent;
    }
}
