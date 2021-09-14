package com.neathorium.framework.core.abstracts.element.finder;

import com.neathorium.framework.core.namespaces.extensions.boilers.LazyLocatorList;
import com.neathorium.core.extensions.namespaces.CoreUtilities;
import com.neathorium.core.extensions.namespaces.NullableFunctions;
import com.neathorium.core.records.Data;

import java.util.Map;
import java.util.Objects;
import java.util.function.Function;


public abstract class BaseFilterParameters<DependencyType, GetterType, ReturnType> {
    public final LazyLocatorList locators;
    public final Map<GetterType, Function<LazyLocatorList, Function<DependencyType, Data<ReturnType>>>> getterMap;
    public final GetterType getter;

    public BaseFilterParameters(LazyLocatorList locators, Map<GetterType, Function<LazyLocatorList, Function<DependencyType, Data<ReturnType>>>> getterMap, GetterType getter) {
        this.locators = locators;
        this.getterMap = getterMap;
        this.getter = getter;
    }

    @Override
    public boolean equals(Object o) {
        if (CoreUtilities.isEqual(this, o)) {
            return true;
        }

        if (NullableFunctions.isNull(o) || CoreUtilities.isNotEqual(getClass(), o.getClass())) {
            return false;
        }

        final var that = (BaseFilterParameters<?, ?, ?>) o;
        return (
            CoreUtilities.isEqual(locators, that.locators) &&
            CoreUtilities.isEqual(getterMap, that.getterMap) &&
            CoreUtilities.isEqual(getter, that.getter)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(locators, getterMap, getter);
    }

    @Override
    public String toString() {
        return (
            "BaseFilterParameters{" +
            "locators=" + locators +
            ", getterMap=" + getterMap +
            ", getter=" + getter +
            '}'
        );
    }
}
