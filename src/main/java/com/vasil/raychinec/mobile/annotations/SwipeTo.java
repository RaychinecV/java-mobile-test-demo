package com.vasil.raychinec.mobile.annotations;

import com.vasil.raychinec.mobile.constants.SwipeDirection;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SwipeTo {
    SwipeDirection swipeTo();
}
