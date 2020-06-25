package com.github.karsaii.framework.core.namespaces.extensions.boilers;

import com.github.karsaii.core.extensions.DecoratedList;
import com.github.karsaii.framework.core.records.lazy.LazyLocator;

import java.util.List;

public class LazyLocatorList extends DecoratedList<LazyLocator> {
    public LazyLocatorList(List<LazyLocator> list) {
        super(list, LazyLocator.class.getTypeName());
    }

    public LazyLocatorList subList(int fromIndex, int toIndex) {
        return subList(LazyLocatorList.class, fromIndex, toIndex);
    }

    public LazyLocatorList tail() {
        return tail(LazyLocatorList.class);
    }

    public LazyLocatorList initials() {
        return initials(LazyLocatorList.class);
    }
}
