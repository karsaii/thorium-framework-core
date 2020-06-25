package com.github.karsaii.framework.core.constants;

import com.github.karsaii.framework.core.records.ProbabilityData;

public abstract class AdjusterConstants {
    public static final double PROBABILITY_THRESHOLD = 80.0;
    public static final double ADJUST_STEP_AMOUNT = 5.0;
    public static final ProbabilityData PROBABILITY_DATA = new ProbabilityData(ADJUST_STEP_AMOUNT, PROBABILITY_THRESHOLD);
}
