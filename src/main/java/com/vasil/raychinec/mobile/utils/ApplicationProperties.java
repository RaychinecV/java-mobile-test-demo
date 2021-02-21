package com.vasil.raychinec.mobile.utils;

public class ApplicationProperties {
    public static final String PLATFORM = System.getProperty("platform");
    public static final String BASE_USER = System.getProperty("user.login");
    public static final String BASE_PASSWORD = System.getProperty("user.password");

    private ApplicationProperties() {
        throw new AssertionError("Suppress default constructor for noninstantiability");
    }
}
