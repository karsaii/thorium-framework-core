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
import com.github.karsaii.framework.core.records.InternalSelectorData;
import com.github.karsaii.framework.core.records.ProbabilityData;
import com.github.karsaii.framework.core.records.lazy.ExternalSelectorData;
import com.github.karsaii.framework.core.records.lazy.LazyLocator;

import java.util.Collection;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.function.Predicate;

import static com.github.karsaii.core.namespaces.validators.DataValidators.isInvalidOrFalseMessage;
import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

public interface FrameworkCoreFormatter {
    static String getCountOfTypeMessage(boolean status, int value, String type) {
        return (status ? value : "No") + " " + type + " found" + CoreFormatterConstants.END_LINE;
    }

    static <T> String isNullExternalSelectorData(ExternalSelectorData<T> object) {
        final var nameof = "isNullExternalSelectorData";
        var message = CoreFormatter.isNullMessageWithName(object, "External Selector Data");
        if (isNotBlank(message)) {
            return message;
        }

        final var range = object.range;
        message += CoreFormatter.getCommandRangeParameterMessage(range);
        if (isBlank(message)) {
            message += CoreFormatter.getCommandAmountRangeErrorMessage(object.limit, range);
        }
        message += (
            CoreFormatter.isNullMessageWithName(object.getSelector, "Selector getter function") +
            CoreFormatter.isNullMessageWithName(object.preferredProperties, "Preferred properties ") +
            CoreFormatter.isNullMessageWithName(object.selectorType, "Selector type") +
            CoreFormatter.isNullMessageWithName(object.defaultSelector, "Default Selector value")
        );
        return isNotBlank(message) ? nameof + message : CoreFormatterConstants.EMPTY;
    }

    static <T, U> String getExternalSelectorDataErrorMessage(AbstractLazyResult<T> element, ExternalSelectorData<U> externalData, String nameof) {
        var message = (
            CoreFormatter.isBlankMessageWithName(nameof, "Name of the function") +
            getLazyParameterErrorMessage(element, nameof) +
            isNullExternalSelectorData(externalData)
        );
        return isNotBlank(message) ? "getExternalSelectorDataErrorMessage: " + message : CoreFormatterConstants.EMPTY;
    }

    static <T> String getExternalSelectorDataMessage(ExternalSelectorData<T> data) {
        var message = CoreFormatter.isNullMessageWithName(data, "Data");
        if (isBlank(message)) {
            message += (
                CoreFormatter.isNullMessageWithName(data.getSelector, "Selector Driver provider function") +
                CoreFormatter.isBlankMessageWithName(data.preferredProperties, "Preferred Properties") +
                CoreFormatter.isBlankMessageWithName(data.selectorType, "Selector type") +
                CoreFormatter.getCommandAmountRangeErrorMessage(data.limit, data.range) +
                CoreFormatter.isNullMessageWithName(data.defaultSelector, "Default Selector Data")
            );
        }

        return isNotBlank(message) ? "getExternalSelectorDataMessage: " + message : CoreFormatterConstants.EMPTY;
    }

    static <T> String getLazyParameterErrorMessage(AbstractLazyResult<T> element, String nameof) {
        var message = isNullLazyElementMessage(element) + CoreFormatter.isBlankMessageWithName(nameof, "Name of the function");
        return CoreFormatter.getNamedErrorMessageOrEmpty("getLazyParameterErrorMessage: ", message);
    }

    static String getInternalSelectorDataMessage(InternalSelectorData internalData) {
        var message = CoreFormatter.isNullMessageWithName(internalData, "Internal Data");
        if (isBlank(message)) {
            message += CoreFormatter.getCommandAmountRangeErrorMessage(internalData.limit, internalData.range);
        }

        return isNotBlank(message) ? "getInternalSelectorDataMessage: " + message : CoreFormatterConstants.EMPTY;
    }

    static <T> String areNullLazyElementParametersMessage(Collection<T> data, Predicate<T> validator) {
        var message = CoreFormatter.isEmptyMessage(data) + CoreFormatter.isNullMessageWithName(validator, "Validator");
        var sb = new StringBuilder();
        if (isBlank(message)) {
            var index = 0;
            for(T parameters : data) {
                sb.append(CoreFormatter.isInvalidMessage(validator.test(parameters), index + ". element data"));
            }
        }

        message += sb.toString();
        return CoreFormatter.getNamedErrorMessageOrEmpty("areNullLazyElementParametersMessage: ", message);
    }

    static <T> String isNullLazyElementMessage(AbstractLazyResult<T> object) {
        final var baseMessage = "isNullLazyElementMessage: " + CoreFormatterConstants.PARAMETER_ISSUES_LINE;
        var message = CoreFormatter.isNullMessageWithName(object, "Lazy Element");
        if (isNotBlank(message)) {
            return baseMessage + message;
        }
        final var parameters = object.parameters;
        if (isBlank(message)) {
            message += (
                CoreFormatter.isBlankMessageWithName(object.name, CoreFormatterConstants.ELEMENT + " name") +
                CoreFormatter.isNullMessageWithName(parameters, FrameworkCoreFormatterConstants.ELEMENT_PARAMETERS)
            );
        }
        if (isBlank(message)) {
            message += areNullLazyElementParametersMessage(parameters.values(), object.validator);
        }

        return isNotBlank(message) ? baseMessage + message : CoreFormatterConstants.EMPTY;
    }

    static <T> String isListTypeEqual(String object, String expected) {
        return CoreFormatter.isEqualMessage(object, "Actual Type of the list", expected, "Expected List type");
    }

    static <T, U> String isValidTypedNonEmptyListMessage(Data<? extends DecoratedList<?>> listData, Class<U> clazz) {
        final var nameof = "isOfTypeNonEmptyMessage: ";
        var message = (
            CoreFormatter.isInvalidOrFalseMessage(listData) +
            CoreFormatter.isNullMessage(clazz)
        );
        if (isNotBlank(message)) {
            return CoreFormatter.getNamedErrorMessageOrEmpty(nameof, message);
        }

        final var list = listData.object;
        if (isBlank(message)) {
            message += isListTypeEqual(list.getType(), clazz.getTypeName());
        }

        if (isBlank(message)) {
            message += CoreFormatter.isNullOrEmptyListMessage(list, "List");
        }

        return CoreFormatter.getNamedErrorMessageOrEmpty(nameof, message);
    }

    static <T, U> Function<Data<DecoratedList<?>>, String> isValidTypedNonEmptyListMessage(Class<U> clazz) {
        return list -> isValidTypedNonEmptyListMessage(list, clazz);
    }

    static <T> String getAmountParameterErrorMessage(Data<T> sizable, TriFunction<Boolean, Integer, String, String> messageHandler, Predicate<Integer> condition) {
        return CoreFormatter.getNamedErrorMessageOrEmpty("getAmountParameterErrorMessage: ", (
            isInvalidOrFalseMessage(sizable) +
            CoreFormatter.isNullMessageWithName(messageHandler, "Message Handler") +
            CoreFormatter.isNullMessageWithName(condition, "Condition")
        ));
    }

    static <T> String getLazyResultWithExternalMessage(String nameof, AbstractLazyResult<T> element, InternalSelectorData internalData, DecoratedList<String> getOrder, ProbabilityData data) {
        var message = (
            FrameworkCoreFormatter.getLazyParameterErrorMessage(element, nameof) +
            FrameworkCoreFormatter.getInternalSelectorDataMessage(internalData) +
            CoreFormatter.getListNotEnoughMessage(getOrder, "GetOrder list", 1) +
            CoreFormatter.isNullMessageWithName(data, "Probability data")
        );

        return CoreFormatter.getNamedErrorMessageOrEmpty("getLazyResultWithExternalMessage: ", message);
    }

    static <T> String getLazyResultWithOptionsMessage(AbstractLazyElementWithOptionsData<?, ?, ?, ?> data, String nameof) {
        var message = CoreFormatter.isNullMessageWithName(data, "Data");
        if (isBlank(message)) {
            message += getLazyResultWithExternalMessage(nameof, data.element, data.internalData, data.getOrder, data.probabilityData);
        }

        return CoreFormatter.getNamedErrorMessageOrEmpty("getLazyResultWithOptionsMessage: ", message);
    }

    static String getElementsAmountMessage(String locator, boolean status, int expectedSize, int size) {
        return (status ? expectedSize : (size > 0 ? "Wrong(" + expectedSize + ") amount of" : "No")) + " elements found by: " + locator.toString() + CoreFormatterConstants.END_LINE;
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

        return CoreFormatter.getNamedErrorMessageOrEmpty("isNotNullLazyDataMessage: ", message);
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

        return CoreFormatter.getNamedErrorMessageOrEmpty("isNotNullLazyDataMessage: ", message);
    }

    static String getUniqueGeneratedName(String selectorType, AtomicInteger count) {
        return selectorType + "-" + CoreUtilities.getIncrementalUUID(count) + "-generated";
    }

    static String getProbabilityAdjustmentMessage(String key, double original, double adjusted, boolean increase, boolean generated, boolean belowThreshold) {
        var message = (increase ? "Increased" : "Reduced") + " probability of com.github.karsaii.framework.core.selector(\"" + original + "\") to \"" + adjusted + "\"" + CoreFormatterConstants.END_LINE;
        if (belowThreshold) {
            message += (generated ? "External" : "Regular") + "com.github.karsaii.framework.core.selector by key(\"" + key + "\") is below threshold(\"" + adjusted + "\"), set to \"0.0\"" + CoreFormatterConstants.END_LINE;
        }

        return message;
    }
}
