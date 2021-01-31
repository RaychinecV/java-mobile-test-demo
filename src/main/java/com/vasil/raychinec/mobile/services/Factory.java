package com.vasil.raychinec.mobile.services;

import java.util.function.Consumer;

public interface Factory<T, R extends Factory<T, R>> {
    T create();
}
