package com.neathorium.framework.core.records;

import com.neathorium.core.extensions.namespaces.CoreUtilities;
import com.neathorium.core.extensions.namespaces.NullableFunctions;
import com.neathorium.core.records.command.CommandRangeData;

import java.util.Objects;

public class InternalSelectorData {
    public final CommandRangeData range;
    public final int limit;

    public InternalSelectorData(CommandRangeData range, int limit) {
        this.range = range;
        this.limit = limit;
    }

    @Override
    public boolean equals(Object o) {
        if (CoreUtilities.isEqual(this, o)) {
            return true;
        }

        if (NullableFunctions.isNull(o) || CoreUtilities.isNotEqual(getClass(), o.getClass())) {
            return false;
        }

        final var that = (InternalSelectorData) o;
        return (
            CoreUtilities.isEqual(limit, that.limit) &&
            CoreUtilities.isEqual(range, that.range)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(range, limit);
    }

    @Override
    public String toString() {
        return (
            "InternalSelectorData{" +
            "range=" + range +
            ", limit=" + limit +
            '}'
        );
    }
}
