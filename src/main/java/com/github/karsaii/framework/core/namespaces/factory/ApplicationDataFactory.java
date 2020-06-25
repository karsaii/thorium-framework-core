package com.github.karsaii.framework.core.namespaces.factory;

import com.github.karsaii.core.constants.validators.CoreFormatterConstants;
import com.github.karsaii.framework.core.records.ApplicationData;

public interface ApplicationDataFactory {
    static ApplicationData getWith(String name, String path, String arguments) {
        return new ApplicationData(name, path, arguments);
    }

    static ApplicationData getWithNoArguments(String name, String path) {
        return getWith(name, path, CoreFormatterConstants.EMPTY);
    }
}
