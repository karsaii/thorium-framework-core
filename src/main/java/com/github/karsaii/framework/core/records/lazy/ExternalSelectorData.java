package com.github.karsaii.framework.core.records.lazy;

import com.github.karsaii.core.records.Data;
import com.github.karsaii.core.records.command.CommandRangeData;

import java.util.List;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;

public class ExternalSelectorData<T> {
    public final BiFunction<String, List<String>, Function<T, Data<String>>> getSelector;
    public final String preferredProperties;
    public final String selectorType;
    public final CommandRangeData range;
    public final int limit;
    public final Data<String> defaultSelector;

    public ExternalSelectorData(
        BiFunction<String, List<String>, Function<T, Data<String>>> getSelector,
        String preferredProperties,
        String selectorType,
        CommandRangeData range,
        int limit,
        Data<String> defaultSelector
    ) {
        this.getSelector = getSelector;
        this.preferredProperties = preferredProperties;
        this.selectorType = selectorType;
        this.range = range;
        this.limit = limit;
        this.defaultSelector = defaultSelector;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final var that = (ExternalSelectorData<?>) o;
        return (
            Objects.equals(limit, that.limit) &&
            Objects.equals(getSelector, that.getSelector) &&
            Objects.equals(preferredProperties, that.preferredProperties) &&
            Objects.equals(selectorType, that.selectorType) &&
            Objects.equals(range, that.range) &&
            Objects.equals(defaultSelector, that.defaultSelector)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSelector, preferredProperties, selectorType, range, limit, defaultSelector);
    }
}
