package com.github.karsaii.framework.core.namespaces.validators;

import com.github.karsaii.core.constants.validators.CoreFormatterConstants;
import com.github.karsaii.core.extensions.DecoratedList;
import com.github.karsaii.core.extensions.interfaces.functional.TriFunction;
import com.github.karsaii.core.extensions.namespaces.CoreUtilities;
import com.github.karsaii.core.namespaces.validators.CoreFormatter;
import com.github.karsaii.core.records.Data;
import com.github.karsaii.framework.core.abstracts.AbstractLazyResult;
import com.github.karsaii.framework.core.abstracts.AbstractLazyElementWithOptionsData;
import com.github.karsaii.framework.core.constants.FrameworkCoreFormatterConstants;
import com.github.karsaii.framework.core.records.GetByFilterFormatterData;
import com.github.karsaii.framework.core.records.InternalSelectorData;
import com.github.karsaii.framework.core.records.ProbabilityData;
import com.github.karsaii.framework.core.records.lazy.ExternalSelectorData;
import com.github.karsaii.framework.core.records.lazy.LazyLocator;

import java.util.Collection;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.function.Predicate;

import static com.github.karsaii.core.namespaces.validators.CoreFormatter.getNamedErrorMessageOrEmpty;
import static com.github.karsaii.core.namespaces.validators.CoreFormatter.isBlankMessageWithName;
import static com.github.karsaii.core.namespaces.validators.CoreFormatter.isLessThanExpected;
import static com.github.karsaii.core.namespaces.validators.CoreFormatter.isMoreThanExpected;
import static com.github.karsaii.core.namespaces.validators.CoreFormatter.isMoreThanExpectedMessage;
import static com.github.karsaii.core.namespaces.validators.CoreFormatter.isNullMessage;
import static com.github.karsaii.core.namespaces.validators.CoreFormatter.isNullMessageWithName;
import static com.github.karsaii.core.namespaces.validators.DataValidators.isInvalidOrFalseMessage;
import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

public interface FrameworkCoreFormatter {
    static String getCountOfTypeMessage(boolean status, int value, String type) {
        return (status ? value : "No") + " " + type + " found" + CoreFormatterConstants.END_LINE;
    }

    static <T> String getLazyParameterErrorMessage(AbstractLazyResult<T> element, String nameof) {
        var message = isNullLazyElementMessage(element) + CoreFormatter.isBlankMessageWithName(nameof, "Name of the function");
        return getNamedErrorMessageOrEmpty("getLazyParameterErrorMessage", message);
    }

    static String getInternalSelectorDataMessage(InternalSelectorData internalData) {
        var message = CoreFormatter.isNullMessageWithName(internalData, "Internal Data");
        if (isBlank(message)) {
            message += CoreFormatter.getCommandAmountRangeErrorMessage(internalData.limit, internalData.range);
        }

        return getNamedErrorMessageOrEmpty("getInternalSelectorDataMessage", message);
    }

    static <T> String getExternalSelectorDataMessage(ExternalSelectorData<T> object) {
        var message = CoreFormatter.isNullMessageWithName(object, "External Selector Data");
        if (isBlank(message)) {
            message += (
                CoreFormatter.isNullMessageWithName(object.getSelector, "Selector getter function") +
                CoreFormatter.isNullMessageWithName(object.preferredProperties, "Preferred properties ") +
                CoreFormatter.isNullMessageWithName(object.selectorType, "Selector type") +
                CoreFormatter.getCommandAmountRangeErrorMessage(object.limit, object.range) +
                CoreFormatter.isNullMessageWithName(object.defaultSelector, "Default Selector value")
            );
        }
        return getNamedErrorMessageOrEmpty("getExternalSelectorDataMessage", message);
    }

    static <T, U> String getExternalSelectorDataErrorMessage(AbstractLazyResult<T> element, ExternalSelectorData<U> externalData, String nameof) {
        return getNamedErrorMessageOrEmpty(
            "getExternalSelectorDataErrorMessage",
            (
                CoreFormatter.isBlankMessageWithName(nameof, "Name of the function") +
                getLazyParameterErrorMessage(element, nameof) +
                getExternalSelectorDataMessage(externalData)
            )
        );
    }

    static <T> String isNullLazyElementMessage(AbstractLazyResult<T> object) {
        var message = CoreFormatter.isNullMessageWithName(object, "Lazy Element");
        if (isBlank(message)) {
            final var parameters = object.parameters;
            message += (
                CoreFormatter.isBlankMessageWithName(object.name, CoreFormatterConstants.ELEMENT + " name") +
                CoreFormatter.isNullMessageWithName(parameters, FrameworkCoreFormatterConstants.ELEMENT_PARAMETERS)
            );
            if (isBlank(message)) {
                message += CoreFormatter.areInvalidParametersMessage(parameters.values(), object.validator);
            }
        }

        return getNamedErrorMessageOrEmpty("isNullLazyElementMessage", message);
    }

    static <T> String getAmountParameterErrorMessage(Data<T> sizable, TriFunction<Boolean, Integer, String, String> messageHandler, Predicate<Integer> condition) {
        return getNamedErrorMessageOrEmpty("getAmountParameterErrorMessage", (
            isInvalidOrFalseMessage(sizable) +
            CoreFormatter.isNullMessageWithName(messageHandler, "Message Handler") +
            CoreFormatter.isNullMessageWithName(condition, "Condition")
        ));
    }

    static <T> String getLazyResultWithExternalMessage(String nameof, AbstractLazyResult<T> element, InternalSelectorData internalData, DecoratedList<String> getOrder, ProbabilityData data) {
        return getNamedErrorMessageOrEmpty("getLazyResultWithExternalMessage", (
            getLazyParameterErrorMessage(element, nameof) +
            getInternalSelectorDataMessage(internalData) +
            CoreFormatter.getListNotEnoughMessage(getOrder, "GetOrder list", 1) +
            CoreFormatter.isNullMessageWithName(data, "Probability data")
        ));
    }

    static <T> String getLazyResultWithOptionsMessage(AbstractLazyElementWithOptionsData<?, ?, ?, ?> data, String nameof) {
        var message = CoreFormatter.isNullMessageWithName(data, "Data");
        if (isBlank(message)) {
            message += getLazyResultWithExternalMessage(nameof, data.element, data.internalData, data.getOrder, data.probabilityData);
        }

        return getNamedErrorMessageOrEmpty("getLazyResultWithOptionsMessage", message);
    }

    static String getElementsAmountMessage(String locator, boolean status, int expectedSize, int size) {
        return (status ? expectedSize : (size > 0 ? "Wrong(" + expectedSize + ") amount of" : "No")) + " elements found by: " + locator + CoreFormatterConstants.END_LINE;
    }

    static <T> String isInvalidLocatorMessage(String locator, Function<String, T> locatorGetter) {
        final var parameterName = "Lazy locator";
        var message = (
            CoreFormatter.isNullMessageWithName(locator, parameterName) +
            CoreFormatter.isNullMessageWithName(locatorGetter, parameterName + " getter")
        );

        if (isBlank(message)) {
            message += CoreFormatter.isNullMessageWithName(locatorGetter.apply(locator), "Actual locator from locator");
        }

        return getNamedErrorMessageOrEmpty("isNotNullLazyDataMessage", message);
    }

    static <T> String isInvalidLazyLocatorMessage(LazyLocator locator, Function<LazyLocator, T> locatorGetter) {
        final var parameterName = "Lazy locator";
        var message = (
            CoreFormatter.isNullMessageWithName(locator, parameterName) +
            CoreFormatter.isNullMessageWithName(locatorGetter, parameterName + " getter")
        );
        if (isBlank(message)) {
            message += (
                CoreFormatter.isNullMessageWithName(locator.locator, parameterName + " value") +
                CoreFormatter.isNullMessageWithName(locator.strategy, parameterName + " strategy")
            );
        }

        if (isBlank(message)) {
            message += CoreFormatter.isNullMessageWithName(locatorGetter.apply(locator), "Actual locator from locator");
        }

        return getNamedErrorMessageOrEmpty("isInvalidLazyLocatorMessage", message);
    }

    static String getUniqueGeneratedName(String name, AtomicInteger count) {
        return name + "-" + CoreUtilities.getIncrementalUUID(count) + "-generated";
    }

    static String getProbabilityAdjustmentMessage(String key, double original, double adjusted, boolean increase, boolean generated, boolean belowThreshold) {
        var message = (increase ? "Increased" : "Reduced") + " probability of selector(\"" + original + "\") to \"" + adjusted + "\"" + CoreFormatterConstants.END_LINE;
        if (belowThreshold) {
            message += (generated ? "External" : "Regular") + "selector by key(\"" + key + "\") is below threshold(\"" + adjusted + "\"), set to \"0.0\"" + CoreFormatterConstants.END_LINE;
        }

        return message;
    }



    static <T> String getInvalidGetByFilterFormatterDataMessage(GetByFilterFormatterData<T> data) {
        var message = isNullMessageWithName(data, "Get By filter data");
        if (isBlank(message)) {
            message += (
                isMoreThanExpectedMessage(data.listSize, -1, "List size") +
                isBlankMessageWithName(data.filterName, "Filter name") +
                isBlankMessageWithName(data.message, "Message") +
                isNullMessageWithName(data.filter, "Filter") +
                isNullMessage(data.status)
            );
        }

        return getNamedErrorMessageOrEmpty("getInvalidGetByFilterFormatterDataMessage", message);
    }

    static <T> String getByFilterMessage(GetByFilterFormatterData<T> data) {
        var message = getInvalidGetByFilterFormatterDataMessage(data);
        return isBlank(message) ? (
            data.filterName + " was" + (data.status ? "" : "n't") + " found by " + data.filterName + "(\"" + data.filter + "\"), list size: " + data.listSize + CoreFormatterConstants.END_LINE + message
        ) : message;
    }
}
