package com.talesdev.talesz.test;

import com.talesdev.talesz.item.GrapplingHookDamageManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.fail;

public class GrapplingHookDamageManagerTest {

    @Before
    public void setUp() throws Exception {
        GrapplingHookDamageManager.addReduceDamage("MoKunz", 20);
        GrapplingHookDamageManager.addReduceDamage("Astraea", 100);
    }

    @Test
    public void testUpdate() {
        for (int i = 0; i < 50; i++) {
            GrapplingHookDamageManager.update();
        }
        if (GrapplingHookDamageManager.contains("MoKunz")) {
            fail();
        }
        if (!GrapplingHookDamageManager.contains("Astraea")) {
            fail();
        }
    }

    @After
    public void tearDown() throws Exception {

    }
}