package com.github.karsaii.framework.core.namespaces.validators;

import com.github.karsaii.core.extensions.namespaces.predicates.BasicPredicateFunctions;
import com.github.karsaii.core.extensions.namespaces.validators.FileUtilitiesValidators;
import com.github.karsaii.framework.core.records.ApplicationData;

import static com.github.karsaii.core.namespaces.validators.CoreFormatter.getNamedErrorMessageOrEmpty;
import static com.github.karsaii.core.namespaces.validators.CoreFormatter.isBlankMessageWithName;
import static com.github.karsaii.core.namespaces.validators.CoreFormatter.isNullMessageWithName;
import static com.github.karsaii.framework.core.constants.ProcessFunctionsValidatorsConstants.APPLICATION_NAME;
import static com.github.karsaii.framework.core.constants.ProcessFunctionsValidatorsConstants.ARGUMENTS;
import static com.github.karsaii.framework.core.constants.ProcessFunctionsValidatorsConstants.PATH;
import static org.apache.commons.lang3.StringUtils.isBlank;

public interface ProcessFunctionsValidators {
    static String isValidGetBuilderParameters(ApplicationData data) {
        var message = isNullMessageWithName(data, "Application Data");
        if (isBlank(message)) {
            message += (
                isBlankMessageWithName(data.name, APPLICATION_NAME) +
                isBlankMessageWithName(data.path, PATH) +
                isNullMessageWithName(data.arguments, ARGUMENTS)
            );
        }

        if (isBlank(message)) {
            final var arguments = data.arguments;
            message += FileUtilitiesValidators.isExistingMessage(data.path);
            if (BasicPredicateFunctions.isPositiveNonZero(arguments.length())) {
                message += isBlankMessageWithName(arguments, "Arguments");
            }
        }

        return getNamedErrorMessageOrEmpty("isValidGetBuilderParameters: ", message);
    }
}
