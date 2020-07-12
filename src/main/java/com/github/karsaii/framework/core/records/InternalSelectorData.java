package com.github.karsaii.framework.core.records;

import com.github.karsaii.core.records.command.CommandRangeData;

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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final var that = (InternalSelectorData) o;
        return limit == that.limit && Objects.equals(range, that.range);
    }

    @Override
    public int hashCode() {
        return Objects.hash(range, limit);
    }
}
