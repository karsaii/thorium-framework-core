package com.github.karsaii.framework.core.records.lazy;

import com.github.karsaii.core.extensions.namespaces.CoreUtilities;
import com.github.karsaii.core.extensions.namespaces.NullableFunctions;
import com.github.karsaii.core.extensions.namespaces.predicates.BasicPredicates;

import java.util.Objects;

public class LazyElementParameters<ListType> {
    public final ListType LAZY_LOCATORS;
    public final String GETTER;
    public double PROBABILITY;

    public LazyElementParameters(double probability, ListType lazyLocators, String getter) {
        this.PROBABILITY = probability;
        this.LAZY_LOCATORS = lazyLocators;
        this.GETTER = getter;
    }

    @Override
    public boolean equals(Object o) {
        if (CoreUtilities.isEqual(this, o)) {
            return true;
        }

        if (NullableFunctions.isNull(o) || CoreUtilities.isNotEqual(getClass(), o.getClass())) {
            return false;
        }

        final var that = (LazyElementParameters<?>) o;
        return (
            BasicPredicates.isZero(Double.compare(that.PROBABILITY, PROBABILITY)) &&
            CoreUtilities.isEqual(LAZY_LOCATORS, that.LAZY_LOCATORS) &&
            CoreUtilities.isEqual(GETTER, that.GETTER)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(LAZY_LOCATORS, GETTER, PROBABILITY);
    }

    @Override
    public String toString() {
        return (
            "LazyElementParameters{lazyLocators=" +
            LAZY_LOCATORS +
            ", getter='" +
            GETTER +
            "', probability=" +
            PROBABILITY +
            '}'
        );
    }
}
