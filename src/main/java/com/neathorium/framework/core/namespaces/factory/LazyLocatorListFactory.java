package com.neathorium.framework.core.namespaces.factory;

import com.neathorium.framework.core.namespaces.extensions.boilers.LazyLocatorList;
import com.neathorium.framework.core.records.lazy.LazyLocator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public interface LazyLocatorListFactory {
    static LazyLocatorList getWith(List<LazyLocator> list) {
        return new LazyLocatorList(list);
    }

    static LazyLocatorList getWith(LazyLocator... locators) {
        return getWith(Arrays.asList(locators));
    }

    static LazyLocatorList getWithEmptyList() {
        return getWith(new ArrayList<>());
    }

    static LazyLocatorList getWithSingleItem(LazyLocator locator) {
        return new LazyLocatorList(List.of(locator));
    }
}
