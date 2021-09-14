package com.neathorium.framework.core.selector.extensions;

import com.neathorium.framework.core.selector.constants.SelectorComparatorConstants;
import selectorSpecificity.tuples.SpecificityData;

import java.util.Objects;

import static selectorSpecificity.Specificity.getSpecificityValuesInOrder;

public interface SelectorComparator {
    static SpecificityData better(SpecificityData left, SpecificityData right) {
        final var leftValues = getSpecificityValuesInOrder(left);
        final var rightValues = getSpecificityValuesInOrder(right);
        final var length = leftValues.size();
        double leftValue;
        double rightValue;
        var previous = "";
        var value = SelectorComparatorConstants.MIDDLE;
        for(var index = 0; index < length; ++index) {
            leftValue = leftValues.get(index);
            rightValue = rightValues.get(index);
            if (leftValue == rightValue) {
                previous = SelectorComparatorConstants.MIDDLE;
                continue;
            }

            if (Objects.equals(previous, SelectorComparatorConstants.MIDDLE)) {
                if (rightValue > 0.0) {
                    value = leftValue < 2.0 ? SelectorComparatorConstants.LEFT : SelectorComparatorConstants.RIGHT;
                }
                if (leftValue > 0.0) {
                    value = rightValue < 2.0 ? SelectorComparatorConstants.RIGHT : SelectorComparatorConstants.LEFT;
                }
                previous = value;
                continue;
            }

            if (Objects.equals(previous, SelectorComparatorConstants.LEFT)) {
                if (leftValue == 0.0) {
                    value = SelectorComparatorConstants.LEFT;
                }
                if (rightValue == 0.0) {
                    value = SelectorComparatorConstants.RIGHT;
                }
                previous = value;
                continue;
            }

            if (Objects.equals(previous, SelectorComparatorConstants.RIGHT)) {
                if (leftValue == 0.0) {
                    value = SelectorComparatorConstants.LEFT;
                }

                if (rightValue == 0.0) {
                    value = SelectorComparatorConstants.RIGHT;
                }

                previous = value;
                continue;
            }

            if (leftValue < 2.0) {
                value = rightValue == 1.0 ? SelectorComparatorConstants.RIGHT : SelectorComparatorConstants.LEFT;
            }

            if (rightValue < 2.0) {
                value = leftValue == 1.0 ? SelectorComparatorConstants.LEFT : SelectorComparatorConstants.RIGHT;
            }
            previous = value;
        }

        return Objects.equals(value, SelectorComparatorConstants.MIDDLE) ? left : Objects.equals(value, SelectorComparatorConstants.LEFT) ? left : right;
    }
}
