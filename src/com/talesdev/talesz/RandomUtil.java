package com.talesdev.talesz;

import java.util.ArrayList;
import java.util.List;
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

    public static List<Integer> randomNumberList(int min, int max, int size) {
        List<Integer> rangedNumberList = new ArrayList<>();
        for (int i = min; i <= max; i++) {
            rangedNumberList.add(i);
        }
        List<Integer> randomNumberList = new ArrayList<>();
        while (randomNumberList.size() < size) {
            int index = randomRange(rangedNumberList.size() - 1);
            int e = rangedNumberList.get(index);
            randomNumberList.add(e);
        }
        return randomNumberList;
    }
}
