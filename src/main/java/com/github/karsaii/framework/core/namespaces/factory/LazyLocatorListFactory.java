package com.github.karsaii.framework.core.namespaces.factory;

import com.github.karsaii.framework.core.namespaces.extensions.boilers.LazyLocatorList;
import com.github.karsaii.framework.core.records.lazy.LazyLocator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public interface LazyLocatorListFactory {
    static LazyLocatorList getWith(List<LazyLocator> list) {
        return new LazyLocatorList(list);
    }

    static LazyLocatorList getWithEmptyList() {
        return getWith(new ArrayList<>());
    }

    static LazyLocatorList getWithSingleItem(LazyLocator locator) {
        return new LazyLocatorList(Collections.singletonList(locator));
    }
}
