package com.github.karsaii.framework.core.namespaces.factory;

import com.github.karsaii.core.extensions.interfaces.functional.TriFunction;
import com.github.karsaii.framework.core.namespaces.validators.FrameworkCoreFormatter;
import com.github.karsaii.framework.core.records.GetAmountData;

import java.util.function.Predicate;

public interface GetAmountDataFactory {
    static GetAmountData getWith(String nameof, TriFunction<Boolean, Integer, String, String> messageHandler, Predicate<Integer> condition, String elementName) {
        return new GetAmountData(nameof, messageHandler, condition, elementName);
    }

    static GetAmountData getWithDefaultMessageHandler(String nameof, Predicate<Integer> condition, String elementName) {
        return getWith(nameof, FrameworkCoreFormatter::getCountOfTypeMessage, condition, elementName);
    }
}
