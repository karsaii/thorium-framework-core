package com.github.karsaii.framework.core.namespaces.factory;

import com.github.karsaii.core.extensions.interfaces.functional.TriFunction;
import com.github.karsaii.core.records.Data;
import com.github.karsaii.framework.core.namespaces.extensions.boilers.LazyLocatorList;
import com.github.karsaii.framework.core.abstracts.element.finder.BaseFilterParameters;

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
