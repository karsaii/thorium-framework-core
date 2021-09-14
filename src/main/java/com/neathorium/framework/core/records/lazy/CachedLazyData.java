package com.neathorium.framework.core.records.lazy;

import com.neathorium.framework.core.abstracts.AbstractLazyResult;
import com.neathorium.framework.core.selector.records.SelectorKeySpecificityData;
import com.neathorium.core.extensions.DecoratedList;
import com.neathorium.core.extensions.namespaces.CoreUtilities;
import com.neathorium.core.extensions.namespaces.NullableFunctions;

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
        if (CoreUtilities.isEqual(this, o)) {
            return true;
        }

        if (NullableFunctions.isNull(o) || CoreUtilities.isNotEqual(getClass(), o.getClass())) {
            return false;
        }

        final var that = (CachedLazyData<?, ?>) o;
        return (
            CoreUtilities.isEqual(element, that.element) &&
            CoreUtilities.isEqual(typeKeys, that.typeKeys)
        );
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
