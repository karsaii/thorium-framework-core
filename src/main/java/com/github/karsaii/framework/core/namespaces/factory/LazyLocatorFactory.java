package com.github.karsaii.framework.core.namespaces.factory;

import com.github.karsaii.framework.core.records.lazy.LazyLocator;

public interface LazyLocatorFactory {
    static LazyLocator get(String locator, String strategy) {
        return new LazyLocator(locator, strategy);
    }
}
