package com.github.karsaii.framework.core.abstracts.element.finder;

import com.github.karsaii.core.records.Data;
import com.github.karsaii.framework.core.namespaces.extensions.boilers.LazyLocatorList;

import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

import static com.github.karsaii.core.extensions.namespaces.NullableFunctions.isNull;

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
        if (this == o) return true;
        if (isNull(o) || (getClass() != o.getClass())) return false;
        final var that = (BaseFilterParameters<?, ?, ?>) o;
        return Objects.equals(locators, that.locators) && Objects.equals(getterMap, that.getterMap) && Objects.equals(getter, that.getter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(locators, getterMap, getter);
    }
}
