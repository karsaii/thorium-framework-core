package com.neathorium.framework.core.namespaces.factory;

import com.neathorium.framework.core.namespaces.extensions.boilers.LazyLocatorList;
import com.neathorium.framework.core.abstracts.element.finder.BaseFilterParameters;
import com.neathorium.core.extensions.interfaces.functional.TriFunction;
import com.neathorium.core.records.Data;

import java.util.Map;
import java.util.function.Function;

public interface BaseFilterParametersFactory {
    static <DependencyType, GetterType, ReturnType, ConstructedType extends BaseFilterParameters<DependencyType, GetterType, ReturnType>> ConstructedType getWith(
        TriFunction<
            LazyLocatorList,
            Map<GetterType, Function<LazyLocatorList, Function<DependencyType, Data<ReturnType>>>>,
            GetterType,
            ConstructedType
        > constructor,
        LazyLocatorList locators,
        Map<GetterType, Function<LazyLocatorList, Function<DependencyType, Data<ReturnType>>>> map,
        GetterType getter
    ) {
        return constructor.apply(locators, map, getter);
    }

    static <DependencyType, GetterType, ReturnType, ConstructedType extends BaseFilterParameters<DependencyType, GetterType, ReturnType>> ConstructedType getWith(
        TriFunction<
            LazyLocatorList,
            Map<GetterType, Function<LazyLocatorList, Function<DependencyType, Data<ReturnType>>>>,
            GetterType,
            ConstructedType
        > constructor,
        LazyLocatorList locators,
        Map<GetterType, Function<LazyLocatorList, Function<DependencyType, Data<ReturnType>>>> map,
        String getter,
        Function<String, GetterType> getterFunction
    ) {
        return constructor.apply(locators, map, getterFunction.apply(getter));
    }
}
