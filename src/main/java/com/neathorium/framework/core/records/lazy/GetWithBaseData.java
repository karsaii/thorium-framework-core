package com.neathorium.framework.core.records.lazy;

import com.neathorium.core.extensions.namespaces.CoreUtilities;
import com.neathorium.core.extensions.namespaces.NullableFunctions;
import com.neathorium.core.records.Data;

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
        if (CoreUtilities.isEqual(this, o)) {
            return true;
        }

        if (NullableFunctions.isNull(o) || CoreUtilities.isNotEqual(getClass(), o.getClass())) {
            return false;
        }

        final var that = (GetWithBaseData<?, ?, ?, ?, ?>) o;
        return (
            CoreUtilities.isEqual(locators, that.locators) &&
            CoreUtilities.isEqual(locatorGetter, that.locatorGetter) &&
            CoreUtilities.isEqual(getter, that.getter) &&
            CoreUtilities.isEqual(guardData, that.guardData)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(locators, locatorGetter, getter, guardData);
    }

    @Override
    public String toString() {
        return (
            "GetWithBaseData{" +
            "locators=" + locators +
            ", locatorGetter=" + locatorGetter +
            ", getter=" + getter +
            ", guardData=" + guardData +
            '}'
        );
    }
}
