package com.neathorium.framework.core.records;

import com.neathorium.core.extensions.interfaces.functional.TriFunction;
import com.neathorium.core.extensions.namespaces.CoreUtilities;
import com.neathorium.core.extensions.namespaces.NullableFunctions;

import java.util.Objects;
import java.util.function.Predicate;

public class GetAmountData {
    public final String nameof;
    public final TriFunction<Boolean, Integer, String, String> messageHandler;
    public final Predicate<Integer> condition;
    public final String elementName;

    public GetAmountData(String nameof, TriFunction<Boolean, Integer, String, String> messageHandler, Predicate<Integer> condition, String elementName) {
        this.nameof = nameof;
        this.messageHandler = messageHandler;
        this.condition = condition;
        this.elementName = elementName;
    }

    @Override
    public boolean equals(Object o) {
        if (CoreUtilities.isEqual(this, o)) {
            return true;
        }

        if (NullableFunctions.isNull(o) || CoreUtilities.isNotEqual(getClass(), o.getClass())) {
            return false;
        }

        final var that = (GetAmountData) o;
        return (
            CoreUtilities.isEqual(nameof, that.nameof) &&
            CoreUtilities.isEqual(messageHandler, that.messageHandler) &&
            CoreUtilities.isEqual(condition, that.condition) &&
            CoreUtilities.isEqual(elementName, that.elementName)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameof, messageHandler, condition, elementName);
    }

    @Override
    public String toString() {
        return (
            "GetAmountData{" +
            "nameof='" + nameof + '\'' +
            ", messageHandler=" + messageHandler +
            ", condition=" + condition +
            ", elementName='" + elementName + '\'' +
            '}'
        );
    }
}
