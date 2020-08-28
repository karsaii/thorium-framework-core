package com.github.karsaii.framework.core.namespaces.extensions.boilers;

import com.github.karsaii.core.extensions.DecoratedList;
import com.github.karsaii.framework.core.records.lazy.LazyLocator;

import java.util.List;

public class LazyLocatorList extends DecoratedList<LazyLocator> {
    public LazyLocatorList(List<LazyLocator> list) {
        super(list, LazyLocator.class.getTypeName());
    }

    public LazyLocatorList subList(int fromIndex, int toIndex) {
        return new LazyLocatorList(super.subList(fromIndex, toIndex));
    }

    public LazyLocatorList tail() {
        return new LazyLocatorList(super.tail());
    }

    public LazyLocatorList initials() {
        return new LazyLocatorList(super.initials());
    }
}
