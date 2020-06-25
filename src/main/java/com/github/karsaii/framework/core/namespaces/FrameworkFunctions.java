package com.github.karsaii.framework.core.namespaces;

import com.github.karsaii.core.constants.CoreDataConstants;
import com.github.karsaii.core.extensions.DecoratedList;
import com.github.karsaii.core.extensions.boilers.StringSet;
import com.github.karsaii.core.extensions.interfaces.ISizable;
import com.github.karsaii.core.extensions.interfaces.functional.TriFunction;
import com.github.karsaii.core.extensions.namespaces.predicates.BasicPredicateFunctions;
import com.github.karsaii.core.namespaces.DataFactoryFunctions;
import com.github.karsaii.core.records.Data;
import com.github.karsaii.framework.core.namespaces.validators.FrameworkCoreFormatter;

import java.util.function.Function;
import java.util.function.Predicate;

import static com.github.karsaii.core.namespaces.DataFactoryFunctions.replaceName;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

public interface FrameworkFunctions {
    static <T extends ISizable> Data<Integer> getAmount(
        String nameof,
        Data<T> sizable,
        TriFunction<Boolean, Integer, String, String> messageHandler,
        Predicate<Integer> condition,
        String elementName
    ) {
        final var lNameof = isNotBlank(nameof) ? nameof : "getAmount";
        final var errorMessage = FrameworkCoreFormatter.getAmountParameterErrorMessage(sizable, messageHandler, condition);
        if (isNotBlank(errorMessage)) {
            return replaceName(CoreDataConstants.NULL_INTEGER, lNameof);
        }

        final var size = sizable.object.size();
        final var status = condition.test(size);
        return DataFactoryFunctions.getWithNameAndMessage(size, status, nameof, messageHandler.apply(status, size, elementName));
    }

    static <T extends ISizable> Data<Integer> getCountOfElements(Data<T> data, String elementName) {
        return getAmount("getCountOfElements", data, FrameworkCoreFormatter::getCountOfTypeMessage, BasicPredicateFunctions::isNonNegative, elementName);
    }

    static <T extends ISizable> Data<Integer> getWindowHandleAmount(Data<T> data, String elementName) {
        return getAmount("getWindowHandleAmount", data, FrameworkCoreFormatter::getCountOfTypeMessage, BasicPredicateFunctions::isPositiveNonZero, elementName);
    }

    static <T> Function<Data<DecoratedList<?>>, Data<Integer>> getCountOfElements(String elementName) {
        return data -> getCountOfElements(data, elementName);
    }

    static Function<Data<StringSet>, Data<Integer>> getWindowHandleAmount(String elementName) {
        return data -> getWindowHandleAmount(data, elementName);
    }
}
