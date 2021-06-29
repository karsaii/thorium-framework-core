package com.github.karsaii.framework.core.namespaces;

import com.github.karsaii.core.extensions.DecoratedList;
import com.github.karsaii.core.namespaces.DataFactoryFunctions;
import com.github.karsaii.core.records.Data;
import com.github.karsaii.framework.core.abstracts.lazy.filtered.AbstractLazyFilteredElementParameters;
import com.github.karsaii.framework.core.namespaces.validators.FrameworkCoreFormatter;
import com.github.karsaii.framework.core.selector.records.SelectorKeySpecificityData;
import com.github.karsaii.framework.core.constants.AdjusterConstants;
import com.github.karsaii.framework.core.records.ProbabilityData;

import java.util.Map;

public interface Adjuster {
    static <DependencyType, GetterType, FilterMetaType, LocatorListType, ResultListType, ResultType> Data<Boolean> adjustProbability(
        AbstractLazyFilteredElementParameters<DependencyType, GetterType, FilterMetaType, LocatorListType, ResultListType, ResultType> parameters,
        Map<String, DecoratedList<SelectorKeySpecificityData>> typeKeys,
        String key,
        boolean increase,
        ProbabilityData data
    ) {
        final var originalProbability = parameters.PROBABILITY;
        final var step = data.step;
        parameters.PROBABILITY += increase ? +step : -step;

        final var probability = parameters.PROBABILITY;
        final var status = probability > data.threshold;
        if (!status) {
            parameters.PROBABILITY = 0.0;
        }

        final var message = FrameworkCoreFormatter.getProbabilityAdjustmentMessage(key, originalProbability, probability, increase, !typeKeys.containsKey(key), !status);
        return DataFactoryFunctions.getBoolean(status, "adjustProbability", message);
    }

    static <DependencyType, GetterType, FilterMetaType, LocatorListType, ResultListType, ResultType> Data<Boolean> adjustProbability(
        AbstractLazyFilteredElementParameters<DependencyType, GetterType, FilterMetaType, LocatorListType,  ResultListType, ResultType> parameters,
        Map<String, DecoratedList<SelectorKeySpecificityData>> typeKeys,
        String key,
        boolean increase,
        double threshold,
        double step
    ) {
        return adjustProbability(parameters, typeKeys, key, increase, new ProbabilityData(step, threshold));
    }

    static <DependencyType, GetterType, FilterMetaType, LocatorListType, ResultListType, ResultType> Data<Boolean> adjustProbabilityDefaultStep(
        AbstractLazyFilteredElementParameters<DependencyType, GetterType, FilterMetaType, LocatorListType, ResultListType, ResultType> parameters,
        Map<String, DecoratedList<SelectorKeySpecificityData>> typeKeys,
        String key,
        boolean increase,
        double threshold
    ) {
        return adjustProbability(parameters, typeKeys, key, increase, threshold, AdjusterConstants.ADJUST_STEP_AMOUNT);
    }

    static <DependencyType, GetterType, FilterMetaType, LocatorListType, ResultListType, ResultType> Data<Boolean> adjustProbabilityDefaultThreshold(
        AbstractLazyFilteredElementParameters<DependencyType, GetterType, FilterMetaType, LocatorListType, ResultListType, ResultType> parameters,
        Map<String, DecoratedList<SelectorKeySpecificityData>> typeKeys,
        String key,
        boolean increase,
        double step
    ) {
        return adjustProbability(parameters, typeKeys, key, increase, AdjusterConstants.PROBABILITY_THRESHOLD, step);
    }

    static <DependencyType, GetterType, FilterMetaType, LocatorListType, ResultListType, ResultType> Data<Boolean> adjustProbabilityDefault(
        AbstractLazyFilteredElementParameters<DependencyType, GetterType, FilterMetaType, LocatorListType, ResultListType, ResultType> parameters,
        Map<String, DecoratedList<SelectorKeySpecificityData>> typeKeys,
        String key,
        boolean increase
    ) {
        return adjustProbability(parameters, typeKeys, key, increase, AdjusterConstants.PROBABILITY_THRESHOLD, AdjusterConstants.ADJUST_STEP_AMOUNT);
    }

    static <DependencyType, GetterType, FilterMetaType, LocatorListType, ResultListType, ResultType> Data<Boolean> decreaseProbabilityDefault(
        AbstractLazyFilteredElementParameters<DependencyType, GetterType, FilterMetaType, LocatorListType, ResultListType, ResultType> parameters,
        Map<String, DecoratedList<SelectorKeySpecificityData>> typeKeys,
        String key
    ) {
        return adjustProbability(parameters, typeKeys, key, false, AdjusterConstants.PROBABILITY_THRESHOLD, AdjusterConstants.ADJUST_STEP_AMOUNT);
    }

    static <DependencyType, GetterType, FilterMetaType, LocatorListType, ResultListType, ResultType> Data<Boolean> increaseProbabilityDefault(
        AbstractLazyFilteredElementParameters<DependencyType, GetterType, FilterMetaType, LocatorListType, ResultListType, ResultType> parameters,
        Map<String, DecoratedList<SelectorKeySpecificityData>> typeKeys,
        String key
    ) {
        return adjustProbability(parameters, typeKeys, key, true, AdjusterConstants.PROBABILITY_THRESHOLD, AdjusterConstants.ADJUST_STEP_AMOUNT);
    }

    static <DependencyType, GetterType, FilterMetaType, LocatorListType, ResultListType, ResultType> Data<Boolean> decreaseProbabilityDefaultStep(
        AbstractLazyFilteredElementParameters<DependencyType, GetterType, FilterMetaType, LocatorListType, ResultListType, ResultType> parameters,
        Map<String, DecoratedList<SelectorKeySpecificityData>> typeKeys,
        String key,
        double step
    ) {
        return adjustProbability(parameters, typeKeys, key, false, AdjusterConstants.PROBABILITY_THRESHOLD, step);
    }

    static <DependencyType, GetterType, FilterMetaType, LocatorListType, ResultListType, ResultType> Data<Boolean> increaseProbabilityDefaultStep(
        AbstractLazyFilteredElementParameters<DependencyType, GetterType, FilterMetaType, LocatorListType, ResultListType, ResultType> parameters,
        Map<String, DecoratedList<SelectorKeySpecificityData>> typeKeys,
        String key,
        double step
    ) {
        return adjustProbability(parameters, typeKeys, key, true, AdjusterConstants.PROBABILITY_THRESHOLD, step);
    }

    static <DependencyType, GetterType, FilterMetaType, LocatorListType, ResultListType, ResultType> Data<Boolean> decreaseProbabilityDefaultThreshold(
        AbstractLazyFilteredElementParameters<DependencyType, GetterType, FilterMetaType, LocatorListType, ResultListType, ResultType> parameters,
        Map<String, DecoratedList<SelectorKeySpecificityData>> typeKeys,
        String key,
        double threshold
    ) {
        return adjustProbability(parameters, typeKeys, key, false, threshold, AdjusterConstants.ADJUST_STEP_AMOUNT);
    }

    static <DependencyType, GetterType, FilterMetaType, LocatorListType, ResultListType, ResultType> Data<Boolean> increaseProbabilityDefaultThreshold(
        AbstractLazyFilteredElementParameters<DependencyType, GetterType, FilterMetaType, LocatorListType, ResultListType, ResultType> parameters,
        Map<String, DecoratedList<SelectorKeySpecificityData>> typeKeys,
        String key,
        double threshold
    ) {
        return adjustProbability(parameters, typeKeys, key, true, threshold, AdjusterConstants.ADJUST_STEP_AMOUNT);
    }
}
