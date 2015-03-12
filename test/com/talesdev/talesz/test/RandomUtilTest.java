package com.talesdev.talesz.test;

import com.talesdev.talesz.RandomUtil;
import org.junit.Test;

import static org.junit.Assert.*;

public class RandomUtilTest {

    @Test
    public void testRandomPercent() throws Exception {
        assertTrue(RandomUtil.randomPercent(100));
        assertFalse(RandomUtil.randomPercent(0));
    }

    @Test
    public void testRandomRange() throws Exception {
        // range
        int randomNumber = RandomUtil.randomRange(10, 100);
        if (randomNumber < 10 || randomNumber > 100) {
            fail("Number range must between 10 and 100");
        }
    }

    @Test
    public void testRandomRangeFromZero() throws Exception {
        int randomNumber = RandomUtil.randomRange(100);
        if (randomNumber < 0 || randomNumber > 100) {
            fail("Number range must between 0 and 100");
        }
    }

    @Test
    public void testRandomAllZero() throws Exception {
        int randomNumber = RandomUtil.randomRange(0, 0);
        if (randomNumber != 0) {
            fail("Number can only be 0!");
        }
    }
}