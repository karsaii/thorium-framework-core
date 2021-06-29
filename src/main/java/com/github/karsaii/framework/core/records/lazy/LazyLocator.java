package com.github.karsaii.framework.core.records.lazy;

import com.github.karsaii.core.extensions.namespaces.CoreUtilities;
import com.github.karsaii.core.extensions.namespaces.NullableFunctions;

import java.util.Objects;

public class LazyLocator {
    public final String LOCATOR;
    public final String STRATEGY;

    public LazyLocator(String locator, String strategy) {
        this.LOCATOR = locator;
        this.STRATEGY = strategy;
    }

    @Override
    public String toString() {
        return "LazyLocator - By." + STRATEGY + ": " + LOCATOR;
    }

    @Override
    public boolean equals(Object o) {
        if (CoreUtilities.isEqual(this, o)) {
            return true;
        }

        if (NullableFunctions.isNull(o) || CoreUtilities.isNotEqual(getClass(), o.getClass())) {
            return false;
        }

        final var that = (LazyLocator) o;
        return (
            CoreUtilities.isEqual(LOCATOR, that.LOCATOR) &&
            CoreUtilities.isEqual(STRATEGY, that.STRATEGY)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(LOCATOR, STRATEGY);
    }
}
