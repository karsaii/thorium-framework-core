package com.github.karsaii.framework.core.records;

import java.util.Objects;

public class GetByFilterFormatterData<T> {
    public final T filter;
    public final String filterName;
    public final boolean status;
    public final int listSize;
    public final String message;

    public GetByFilterFormatterData(T filter, String filterName, boolean status, int listSize, String message) {
        this.filter = filter;
        this.filterName = filterName;
        this.status = status;
        this.listSize = listSize;
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final var that = (GetByFilterFormatterData<?>) o;
        return (
            status == that.status &&
            listSize == that.listSize &&
            Objects.equals(filter, that.filter) &&
            Objects.equals(filterName, that.filterName) &&
            Objects.equals(message, that.message)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(filter, filterName, status, listSize, message);
    }
}
