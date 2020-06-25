package com.github.karsaii.framework.core.records;

import com.github.karsaii.core.extensions.interfaces.functional.TriFunction;

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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GetAmountData that = (GetAmountData) o;
        return Objects.equals(nameof, that.nameof) && Objects.equals(messageHandler, that.messageHandler) && Objects.equals(condition, that.condition) && Objects.equals(elementName, that.elementName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameof, messageHandler, condition, elementName);
    }
}
