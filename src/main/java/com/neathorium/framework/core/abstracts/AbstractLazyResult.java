package com.neathorium.framework.core.abstracts;

import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

public abstract class AbstractLazyResult<T>  {
    public final String name;
    public final Map<String, T> parameters;
    public final Predicate<T> validator;

    public AbstractLazyResult(String name, Map<String, T> parameters, Predicate<T> validator) {
        this.name = name;
        this.parameters = parameters;
        this.validator = validator;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final var that = (AbstractLazyResult<?>) o;
        return Objects.equals(name, that.name) && Objects.equals(parameters, that.parameters) && Objects.equals(validator, that.validator);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, parameters, validator);
    }
}
