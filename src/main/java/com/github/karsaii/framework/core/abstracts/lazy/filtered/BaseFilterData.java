package com.github.karsaii.framework.core.abstracts.lazy.filtered;

import com.github.karsaii.core.records.Data;
import com.github.karsaii.framework.core.abstracts.element.finder.BaseFilterParameters;

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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final var that = (BaseFilterData<?, ?, ?, ?, ?, ?>) o;
        return Objects.equals(isFiltered, that.isFiltered) && Objects.equals(handler, that.handler) && Objects.equals(filterParameter, that.filterParameter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isFiltered, handler, filterParameter);
    }


}
