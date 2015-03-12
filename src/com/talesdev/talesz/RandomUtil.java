package com.talesdev.talesz;

import java.util.Random;

/**
 * Random util
 * Created by MoKunz on 3/9/2015.
 */
public class RandomUtil {
    public static boolean randomPercent(double percent) {
        double random = Math.random() * 100;
        return random <= percent;
    }

    public static int randomRange(int start, int end) {
        Random random = new Random();
        return start + random.nextInt((end - start) + 1);
    }

    public static int randomRange(int range) {
        return randomRange(0, range);
    }
}
