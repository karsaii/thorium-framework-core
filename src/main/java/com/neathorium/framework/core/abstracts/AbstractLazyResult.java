package com.neathorium.framework.core.abstracts;

import com.neathorium.core.extensions.namespaces.CoreUtilities;
import com.neathorium.core.extensions.namespaces.NullableFunctions;

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
        if (CoreUtilities.isEqual(this, o)) {
            return true;
        }
        
        if (NullableFunctions.isNull(o) || CoreUtilities.isNotEqual(getClass(), o.getClass())) {
            return false;
        }

        final var that = (AbstractLazyResult<?>) o;
        return (
            CoreUtilities.isEqual(name, that.name) &&
            CoreUtilities.isEqual(parameters, that.parameters) &&
            CoreUtilities.isEqual(validator, that.validator)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, parameters, validator);
    }
}
