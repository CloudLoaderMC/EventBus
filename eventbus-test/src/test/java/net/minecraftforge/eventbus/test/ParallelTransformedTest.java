package net.minecraftforge.eventbus.test;

import cpw.mods.bootstraplauncher.BootstrapLauncher;
import cpw.mods.modlauncher.api.ServiceRunner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;

import java.util.concurrent.atomic.AtomicLong;

public class ParallelTransformedTest {
    static final int LISTENER_COUNT = 1000;
    static final int RUN_ITERATIONS = 1000;

    static final AtomicLong COUNTER = new AtomicLong();

    @BeforeEach
    public void setup() {
        COUNTER.set(0);
    }

    @RepeatedTest(100)
    public void testOneBusParallelTransformed() throws Exception {
        System.setProperty("test.harness.game", MockTransformerService.getTestJarsPath() + "," + MockTransformerService.getBasePath());
        System.setProperty("test.harness.callable", "net.minecraftforge.eventbus.test.ParallelTransformedTest$TestCallback");
        BootstrapLauncher.main("--version", "1.0", "--launchTarget", "testharness");
    }


    public static class TestCallback {
        public static ServiceRunner supplier() {
            return () -> new ArmsLengthHandler().call();
        }
    }
}