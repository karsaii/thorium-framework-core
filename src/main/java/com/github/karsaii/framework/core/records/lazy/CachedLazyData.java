package com.github.karsaii.framework.core.records.lazy;

import com.github.karsaii.core.extensions.DecoratedList;
import com.github.karsaii.framework.core.abstracts.AbstractLazyResult;
import com.github.karsaii.framework.core.selector.records.SelectorKeySpecificityData;

import java.util.Map;
import java.util.Objects;

public class CachedLazyData<T, U extends AbstractLazyResult<T>> {
    public final U element;
    public final Map<String, DecoratedList<SelectorKeySpecificityData>> typeKeys;

    public CachedLazyData(U element, Map<String, DecoratedList<SelectorKeySpecificityData>> typeKeys) {
        this.element = element;
        this.typeKeys = typeKeys;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final var that = (CachedLazyData<?, ?>) o;
        return Objects.equals(element, that.element) && Objects.equals(typeKeys, that.typeKeys);
    }

    @Override
    public int hashCode() {
        return Objects.hash(element, typeKeys);
    }

    @Override
    public String toString() {
        return "CachedLazyData{element=" + element + ", typeKeys=" + typeKeys + '}';
    }
}
