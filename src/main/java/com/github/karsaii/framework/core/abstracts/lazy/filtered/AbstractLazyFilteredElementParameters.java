package com.github.karsaii.framework.core.abstracts.lazy.filtered;

import com.github.karsaii.framework.core.namespaces.extensions.boilers.LazyLocatorList;
import com.github.karsaii.framework.core.records.lazy.LazyElementParameters;

import java.util.Objects;

public abstract class AbstractLazyFilteredElementParameters<
    DependencyType,
    GetterType,
    FilterMetaType,
    ResultListType,
    ResultType
> extends LazyElementParameters {
    public final BaseFilterData<DependencyType, GetterType, ?, FilterMetaType, ResultListType, ResultType> elementFilterData;
    public final Class clazz;

    public AbstractLazyFilteredElementParameters(
        BaseFilterData<DependencyType, GetterType, ?, FilterMetaType, ResultListType, ResultType> elementFilterData,
        Class clazz,
        double probability,
        LazyLocatorList lazyLocators,
        String getter
    ) {
        super(probability, lazyLocators, getter);
        this.elementFilterData = elementFilterData;
        this.clazz = clazz;
        //this.clazz = data.isFiltered ? WebElement.class : WebElementList.class;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if ((o == null) || (getClass() != o.getClass()) || !super.equals(o)) return false;
        final var that = (AbstractLazyFilteredElementParameters<?, ?, ?, ?, ?>) o;
        return Objects.equals(elementFilterData, that.elementFilterData) && Objects.equals(clazz, that.clazz);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), elementFilterData, clazz);
    }

    @Override
    public String toString() {
        return "AbstractLazyFilteredElementParameters{" +
            "elementFilterData=" + elementFilterData +
            ", clazz=" + clazz +
            ", lazyLocators=" + lazyLocators +
            ", getter='" + getter + '\'' +
            ", probability=" + probability +
            '}';
    }
}
