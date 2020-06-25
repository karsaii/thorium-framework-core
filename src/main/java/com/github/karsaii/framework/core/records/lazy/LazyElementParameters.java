package com.github.karsaii.framework.core.records.lazy;

import com.github.karsaii.framework.core.namespaces.extensions.boilers.LazyLocatorList;

import java.util.Objects;

public class LazyElementParameters {
    public final LazyLocatorList lazyLocators;
    public final String getter;
    public double probability;

    public LazyElementParameters(double probability, LazyLocatorList lazyLocators, String getter) {
        this.probability = probability;
        this.lazyLocators = lazyLocators;
        this.getter = getter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final var that = (LazyElementParameters) o;
        return Double.compare(that.probability, probability) == 0 && Objects.equals(lazyLocators, that.lazyLocators) && Objects.equals(getter, that.getter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lazyLocators, getter, probability);
    }

    @Override
    public String toString() {
        return "LazyElementParameters{lazyLocators=" + lazyLocators + ", getter='" + getter + "\', probability=" + probability + '}';
    }
}
