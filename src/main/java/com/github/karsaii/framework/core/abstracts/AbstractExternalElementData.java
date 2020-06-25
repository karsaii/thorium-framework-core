package com.github.karsaii.framework.core.abstracts;

import com.github.karsaii.core.extensions.DecoratedList;
import com.github.karsaii.core.records.Data;
import com.github.karsaii.framework.core.selector.records.SelectorKeySpecificityData;

import java.util.Map;
import java.util.Objects;

public abstract class AbstractExternalElementData<T> {
    public final Map<String, DecoratedList<SelectorKeySpecificityData>> typeKeys;
    public final Data<T> found;

    public AbstractExternalElementData(Map<String, DecoratedList<SelectorKeySpecificityData>> typeKeys, Data<T> found) {
        this.typeKeys = typeKeys;
        this.found = found;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final var that = (AbstractExternalElementData) o;
        return Objects.equals(typeKeys, that.typeKeys) && Objects.equals(found, that.found);
    }

    @Override
    public int hashCode() {
        return Objects.hash(typeKeys, found);
    }
}