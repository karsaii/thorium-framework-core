package com.github.karsaii.framework.core.namespaces;

import com.github.karsaii.core.namespaces.DataFactoryFunctions;
import com.github.karsaii.core.namespaces.validators.DataValidators;
import com.github.karsaii.core.records.Data;
import com.github.karsaii.framework.core.constants.ProcessConstants;
import com.github.karsaii.framework.core.namespaces.validators.FrameworkCoreFormatter;
import com.github.karsaii.framework.core.namespaces.validators.ProcessFunctionsValidators;
import com.github.karsaii.framework.core.records.ApplicationData;

import java.io.File;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

public interface ProcessFunctions {
    static String getPlatformOrDefault(String os, String defaultOs) {
        if (isNotBlank(os)) {
            return os.contains(ProcessConstants.WINDOWS) ? ProcessConstants.WINDOWS : ProcessConstants.ANY;
        }

        return isNotBlank(defaultOs) ? defaultOs : ProcessConstants.ANY;
    }

    static String getPlatform(String os) {
        return getPlatformOrDefault(os, ProcessConstants.ANY);
    }

    static File getNullFile(String platform) {
        final var nullFile = ProcessConstants.NULL_FILE_PLATFORM_MAP.getOrDefault(platform, ProcessConstants.ANY_NULL_FILE_PATH);
        return new File(nullFile);
    }

    static Data<ProcessBuilder> getBuilder(ApplicationData data) {
        final var nameof = "getBuilder";
        final var errorMessage = ProcessFunctionsValidators.isValidGetBuilderParameters(data);
        if (isNotBlank(errorMessage)) {
            return DataFactoryFunctions.getInvalidWithNameAndMessage(ProcessConstants.NULL_BUILDER, nameof, errorMessage);
        }

        final var path = data.path;
        final var arguments = data.arguments;
        final var builder = new ProcessBuilder().command(path, arguments);
        final var message = FrameworkCoreFormatter.getBuilderFormattedParametersMessage(data.name, path, arguments);

        return DataFactoryFunctions.getWithNameAndMessage(builder, true, nameof, message);
    }

    static Data<ProcessBuilder> getBuilderWithRedirects(ApplicationData data, String os) {
        final var nameof = "getBuilderWithRedirects";
        final var errorMessage = ProcessFunctionsValidators.isValidGetBuilderParameters(data);
        if (isNotBlank(errorMessage)) {
            return DataFactoryFunctions.getInvalidWithNameAndMessage(ProcessConstants.NULL_BUILDER, nameof, errorMessage);
        }

        final var builderData = getBuilder(data);
        if (DataValidators.isInvalidOrFalse(builderData)) {
            return builderData;
        }

        final var builder = builderData.object;
        builder.redirectOutput(getNullFile(getPlatform(os))).redirectErrorStream(true);

        return DataFactoryFunctions.replaceObject(builderData, builder);
    }
}
