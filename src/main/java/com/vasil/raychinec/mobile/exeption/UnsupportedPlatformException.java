package com.vasil.raychinec.mobile.exeption;


import java.io.Serializable;

public class UnsupportedPlatformException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = -944053063920967924L;

    public UnsupportedPlatformException(final String message) {
        super(message);
    }

    public UnsupportedPlatformException(final String message, final String errorMessage) {
        super(message + "Cause: " + errorMessage);
    }
}
