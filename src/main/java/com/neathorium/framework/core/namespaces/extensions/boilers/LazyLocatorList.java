package com.neathorium.framework.core.namespaces.extensions.boilers;


import com.neathorium.framework.core.records.lazy.LazyLocator;
import com.neathorium.core.extensions.DecoratedList;

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
