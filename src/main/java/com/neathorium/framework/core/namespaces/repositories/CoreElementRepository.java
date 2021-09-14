package com.neathorium.framework.core.namespaces.repositories;

import com.neathorium.framework.core.selector.records.SelectorKeySpecificityData;
import com.neathorium.core.extensions.DecoratedList;
import com.neathorium.core.extensions.namespaces.factories.DecoratedListFactory;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public interface CoreElementRepository {
    static <T extends String> Map<String, DecoratedList<SelectorKeySpecificityData>> getInitializedTypeKeysMap(Set<T> typesSet, Class<T> clazz) {
        final var typeKeys = Collections.synchronizedMap(new LinkedHashMap<String, DecoratedList<SelectorKeySpecificityData>>());
        final var types = DecoratedListFactory.getWith(typesSet, clazz);
        for (var type : types) {
            typeKeys.put(type, DecoratedListFactory.getWithDefaults());
        }

        return typeKeys;
    }
}
