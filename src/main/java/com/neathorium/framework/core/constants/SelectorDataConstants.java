package com.neathorium.framework.core.constants;

import com.neathorium.framework.core.namespaces.factory.InternalSelectorDataFactory;
import com.neathorium.framework.core.records.InternalSelectorData;

public abstract class SelectorDataConstants {
    public static final InternalSelectorData INTERNAL_SELECTOR_DATA = InternalSelectorDataFactory.getWithDefaults();
}
