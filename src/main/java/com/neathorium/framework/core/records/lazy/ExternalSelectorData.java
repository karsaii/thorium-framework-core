package com.neathorium.framework.core.records.lazy;

import com.neathorium.core.extensions.namespaces.CoreUtilities;
import com.neathorium.core.extensions.namespaces.NullableFunctions;
import com.neathorium.core.records.Data;
import com.neathorium.core.records.command.CommandRangeData;

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
        if (CoreUtilities.isEqual(this, o)) {
            return true;
        }

        if (NullableFunctions.isNull(o) || CoreUtilities.isNotEqual(getClass(), o.getClass())) {
            return false;
        }

        final var that = (ExternalSelectorData<?>) o;
        return (
            CoreUtilities.isEqual(limit, that.limit) &&
            CoreUtilities.isEqual(getSelector, that.getSelector) &&
            CoreUtilities.isEqual(preferredProperties, that.preferredProperties) &&
            CoreUtilities.isEqual(selectorType, that.selectorType) &&
            CoreUtilities.isEqual(range, that.range) &&
            CoreUtilities.isEqual(defaultSelector, that.defaultSelector)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSelector, preferredProperties, selectorType, range, limit, defaultSelector);
    }

    @Override
    public String toString() {
        return (
            "ExternalSelectorData{" +
            "getSelector=" + getSelector +
            ", preferredProperties='" + preferredProperties + '\'' +
            ", selectorType='" + selectorType + '\'' +
            ", range=" + range +
            ", limit=" + limit +
            ", defaultSelector=" + defaultSelector +
            '}'
        );
    }
}
