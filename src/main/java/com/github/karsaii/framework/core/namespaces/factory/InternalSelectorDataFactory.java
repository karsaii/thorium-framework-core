package com.github.karsaii.framework.core.namespaces.factory;

import com.github.karsaii.core.constants.CommandRangeDataConstants;
import com.github.karsaii.core.namespaces.validators.CoreFormatter;
import com.github.karsaii.core.records.command.CommandRangeData;
import com.github.karsaii.framework.core.records.InternalSelectorData;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

public interface InternalSelectorDataFactory {
    static InternalSelectorData getWith(CommandRangeData range, int limit) {
        var errorMessage = CoreFormatter.getCommandAmountRangeErrorMessage(limit, range);
        if (isNotBlank(errorMessage)) {
            throw new IllegalArgumentException("Limit(\"" + limit + "\") was invalid: " + errorMessage);
        }

        return new InternalSelectorData(range, limit);
    }

    static InternalSelectorData getWithDefaultRange(int limit) {
        return getWith(CommandRangeDataConstants.DEFAULT_RANGE, limit);
    }

    static InternalSelectorData getWithDefaults() {
        return getWithDefaultRange(CommandRangeDataConstants.MINIMUM_COMMAND_LIMIT);
    }
}
