package com.neathorium.framework.core.abstracts.lazy.filtered;

import com.neathorium.framework.core.abstracts.element.finder.BaseFilterParameters;
import com.neathorium.core.extensions.namespaces.CoreUtilities;
import com.neathorium.core.extensions.namespaces.NullableFunctions;
import com.neathorium.core.records.Data;

import java.util.Objects;
import java.util.function.Function;

public abstract class BaseFilterData<DependencyType, GetterType, FilterType, FilterMetaType, ResultListType, ResultType> implements Function<BaseFilterParameters<DependencyType, GetterType, ResultListType>, Function<DependencyType, Data<ResultType>>> {
    public final boolean isFiltered;
    public final Function<FilterMetaType, Function<FilterType, Function<DependencyType, Data<ResultType>>>> handler;
    public final FilterType filterParameter;

    public BaseFilterData(
        boolean isFiltered,
        Function<FilterMetaType, Function<FilterType, Function<DependencyType, Data<ResultType>>>> handler,
        FilterType filterParameter
    ) {
        this.isFiltered = isFiltered;
        this.handler = handler;
        this.filterParameter = filterParameter;
    }

    @Override
    public boolean equals(Object o) {
        if (CoreUtilities.isEqual(this, o)) {
            return true;
        }

        if (NullableFunctions.isNull(o) || CoreUtilities.isNotEqual(getClass(), o.getClass())) {
            return false;
        }

        final var that = (BaseFilterData<?, ?, ?, ?, ?, ?>) o;
        return (
            CoreUtilities.isEqual(isFiltered, that.isFiltered) &&
            CoreUtilities.isEqual(handler, that.handler) &&
            CoreUtilities.isEqual(filterParameter, that.filterParameter)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(isFiltered, handler, filterParameter);
    }

    @Override
    public String toString() {
        return (
            "BaseFilterData{" +
            "isFiltered=" + isFiltered +
            ", handler=" + handler +
            ", filterParameter=" + filterParameter +
            '}'
        );
    }
}
