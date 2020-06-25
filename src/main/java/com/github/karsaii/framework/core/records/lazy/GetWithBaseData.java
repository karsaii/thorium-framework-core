package com.github.karsaii.framework.core.records.lazy;

import com.github.karsaii.core.records.Data;

import java.util.Objects;
import java.util.function.Function;

public class GetWithBaseData<DependencyType, T, U, V, W> {
    public final T locators;
    public final Function<T, U> locatorGetter;
    public final Function<V, Function<DependencyType, Data<W>>> getter;
    public final Data<W> guardData;

    public GetWithBaseData(T locators, Function<T, U> locatorGetter, Function<V, Function<DependencyType, Data<W>>> getter, Data<W> guardData) {
        this.locators = locators;
        this.locatorGetter = locatorGetter;
        this.getter = getter;
        this.guardData = guardData;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final var that = (GetWithBaseData<?, ?, ?, ?, ?>) o;
        return (
            Objects.equals(locators, that.locators) &&
            Objects.equals(locatorGetter, that.locatorGetter) &&
            Objects.equals(getter, that.getter) &&
            Objects.equals(guardData, that.guardData)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(locators, locatorGetter, getter, guardData);
    }
}

