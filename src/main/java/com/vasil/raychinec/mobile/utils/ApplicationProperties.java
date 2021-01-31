package com.vasil.raychinec.mobile.utils;

public class ApplicationProperties {
    public static final String PLATFORM = System.getProperty("platform");

    private ApplicationProperties() {
        throw new AssertionError("Suppress default constructor for noninstantiability");
    }
}
