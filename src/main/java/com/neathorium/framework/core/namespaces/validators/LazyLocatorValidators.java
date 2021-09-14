package com.neathorium.framework.core.namespaces.validators;

import com.neathorium.framework.core.records.lazy.LazyLocator;
import com.neathorium.core.namespaces.validators.CoreFormatter;

import static org.apache.commons.lang3.StringUtils.isBlank;

public interface LazyLocatorValidators {
    static String isInvalidLazyLocatorCommon(LazyLocator data) {
        var message = CoreFormatter.isNullMessageWithName(data, "Lazy Locator data");
        if (isBlank(message)) {
            message += (
                CoreFormatter.isBlankMessageWithName(data.LOCATOR, "Lazy Locator locator value") +
                CoreFormatter.isBlankMessageWithName(data.STRATEGY, "Lazy Locator strategy value")
            );
        }

        return CoreFormatter.getNamedErrorMessageOrEmpty("isInvalidLazyLocatorCommon", message);
    }

    static String isInvalidLazyLocator(LazyLocator data) {
        var message = isInvalidLazyLocatorCommon(data);
        if (isBlank(message)) {
            message += CoreFormatter.isBlankMessageWithName(data.STRATEGY, "Lazy Locator strategy value");
        }

        return CoreFormatter.getNamedErrorMessageOrEmpty("isInvalidLazyLocator", message);
    }
}
