package com.github.karsaii.framework.core.abstracts.lazy.filtered;

import com.github.karsaii.core.extensions.namespaces.CoreUtilities;
import com.github.karsaii.core.extensions.namespaces.NullableFunctions;
import com.github.karsaii.framework.core.records.lazy.LazyElementParameters;

import java.util.Objects;

public abstract class AbstractLazyFilteredElementParameters<DependencyType, GetterType, FilterMetaType, LocatorListType, ResultListType, ResultType> extends LazyElementParameters<LocatorListType> {
    public final BaseFilterData<DependencyType, GetterType, ?, FilterMetaType, ResultListType, ResultType> ELEMENT_FILTER_DATA;
    public final Class<?> CLAZZ;

    public AbstractLazyFilteredElementParameters(
        BaseFilterData<DependencyType, GetterType, ?, FilterMetaType, ResultListType, ResultType> elementFilterData,
        Class<?> clazz,
        double probability,
        LocatorListType lazyLocators,
        String getter
    ) {
        super(probability, lazyLocators, getter);
        this.ELEMENT_FILTER_DATA = elementFilterData;
        this.CLAZZ = clazz;
    }

    @Override
    public boolean equals(Object o) {
        if (CoreUtilities.isEqual(this, o)) {
            return true;
        }

        if (NullableFunctions.isNull(o) || CoreUtilities.isNotEqual(getClass(), o.getClass()) || CoreUtilities.isFalse(super.equals(o))) {
            return false;
        }

        final var that = (AbstractLazyFilteredElementParameters<?, ?, ?, ?, ?, ?>) o;
        return (
            CoreUtilities.isEqual(ELEMENT_FILTER_DATA, that.ELEMENT_FILTER_DATA) &&
            CoreUtilities.isEqual(CLAZZ, that.CLAZZ)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), ELEMENT_FILTER_DATA, CLAZZ);
    }

    @Override
    public String toString() {
        return (
            "AbstractLazyFilteredElementParameters{" +
            "elementFilterData=" +
            ELEMENT_FILTER_DATA +
            ", clazz=" +
            CLAZZ +
            ", lazyLocators=" +
            LAZY_LOCATORS +
            ", getter='" +
            GETTER +
            "', probability=" +
            PROBABILITY +
            '}'
        );
    }
}
