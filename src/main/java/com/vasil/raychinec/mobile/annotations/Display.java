package com.vasil.raychinec.mobile.annotations;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Display {
    String getName();
}
