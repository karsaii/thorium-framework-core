package com.neathorium.framework.core.abstracts;

import com.neathorium.framework.core.selector.records.SelectorKeySpecificityData;
import com.neathorium.core.extensions.DecoratedList;
import com.neathorium.core.extensions.namespaces.CoreUtilities;
import com.neathorium.core.extensions.namespaces.NullableFunctions;
import com.neathorium.core.records.Data;

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
        if (CoreUtilities.isEqual(this, o)) {
            return true;
        }

        if (NullableFunctions.isNull(o) || CoreUtilities.isNotEqual(getClass(), o.getClass())) {
            return false;
        }

        final var that = (AbstractExternalElementData<?>) o;
        return (
            CoreUtilities.isEqual(typeKeys, that.typeKeys) &&
            CoreUtilities.isEqual(found, that.found)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(typeKeys, found);
    }
}