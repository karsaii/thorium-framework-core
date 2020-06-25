package com.github.karsaii.framework.core.abstracts;

import com.github.karsaii.core.extensions.DecoratedList;
import com.github.karsaii.framework.core.records.lazy.ExternalSelectorData;
import com.github.karsaii.framework.core.records.InternalSelectorData;
import com.github.karsaii.framework.core.records.ProbabilityData;

import java.util.Objects;

public abstract class AbstractLazyElementWithOptionsData<
    FilterType,
    ElementType extends AbstractLazyResult<FilterType>,
    DependencyType,
    ExternalSelectorDataType extends ExternalSelectorData<DependencyType>
> {
    public final ElementType element;
    public final InternalSelectorData internalData;
    public final ExternalSelectorDataType externalData;
    public final DecoratedList<String> getOrder;
    public final ProbabilityData probabilityData;

    public AbstractLazyElementWithOptionsData(ElementType element, InternalSelectorData internalData, ExternalSelectorDataType externalData, DecoratedList<String> getOrder, ProbabilityData probabilityData) {
        this.element = element;
        this.internalData = internalData;
        this.externalData = externalData;
        this.getOrder = getOrder;
        this.probabilityData = probabilityData;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final var that = (AbstractLazyElementWithOptionsData<?, ?, ?, ?>) o;
        return (
            Objects.equals(element, that.element) &&
            Objects.equals(internalData, that.internalData) &&
            Objects.equals(externalData, that.externalData) &&
            Objects.equals(getOrder, that.getOrder) &&
            Objects.equals(probabilityData, that.probabilityData)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(element, internalData, externalData, getOrder, probabilityData);
    }
}
