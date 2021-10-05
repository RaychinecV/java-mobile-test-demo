package com.vasil.raychinec.mobile.exeption;


import java.io.Serializable;

public class UnsupportedMobilePlatformException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = -944053063920967924L;

    public UnsupportedMobilePlatformException(final String message) {
        super(message);
    }

    public UnsupportedMobilePlatformException(final String message, final String errorMessage) {
        super(message + "Cause: " + errorMessage);
    }
}
