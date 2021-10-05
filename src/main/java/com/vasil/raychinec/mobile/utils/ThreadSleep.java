package com.vasil.raychinec.mobile.utils;

import lombok.extern.log4j.Log4j2;

@Log4j2
public final class ThreadSleep {

    private ThreadSleep() {
        throw new AssertionError("Suppress default constructor for noninstantiability");
    }

    public static void sleepSec(long seconds) {
        try {
            if (seconds >= 1000) seconds /= 1000;
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            log.error("Exception log", e);
        }
    }

    public static void sleepMils(long mils) {
        try {
            Thread.sleep(mils);
        } catch (InterruptedException e) {
            log.error("Exception log", e);
        }
    }
}
