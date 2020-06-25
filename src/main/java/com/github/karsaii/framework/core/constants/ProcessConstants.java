package com.github.karsaii.framework.core.constants;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import static java.util.Map.entry;

public abstract class ProcessConstants {
    public static final String WINDOWS = "Windows";
    public static final String ANY = "Any";
    public static final String WIN_NULL_FILE_PATH = "NUL";
    public static final String ANY_NULL_FILE_PATH = "/dev/null";

    public static final ProcessBuilder NULL_BUILDER = new ProcessBuilder();

    public static final Map<String, String> NULL_FILE_PLATFORM_MAP = (
        Collections.unmodifiableMap(
            new LinkedHashMap<>(
                Map.ofEntries(
                    entry(WINDOWS, WIN_NULL_FILE_PATH),
                    entry(ANY, ANY_NULL_FILE_PATH)
                )
            )
        )
    );
}
