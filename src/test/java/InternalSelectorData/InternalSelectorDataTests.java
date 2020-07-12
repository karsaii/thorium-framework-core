package InternalSelectorData;

import com.github.karsaii.core.constants.CommandRangeDataConstants;
import com.github.karsaii.core.records.command.CommandRangeData;
import com.github.karsaii.framework.core.namespaces.factory.InternalSelectorDataFactory;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class InternalSelectorDataTests {
    @DisplayName("Default construction of InternalSelectorData")
    @Test
    void factoryGetWithDefaultsTest() {
        final var data = InternalSelectorDataFactory.getWithDefaults();
        Assertions.assertTrue(
            Objects.equals(data.limit, CommandRangeDataConstants.MINIMUM_COMMAND_LIMIT) &&
            Objects.equals(data.range, CommandRangeDataConstants.DEFAULT_RANGE),
            "The defaults of the Factory weren't matching the expected defaults."
        );
    }

    public static Stream<Arguments> exceptionThrowingInternalSelectorSource() {
        final Predicate<String> isNotBlank = StringUtils::isNotBlank;
        return Stream.of(
            Arguments.of("Default range: 0 amount throws exception", 0, isNotBlank, "0 amount didn't throw exception.\n"),
            Arguments.of("Default range: -1 amount throws exception", -1, isNotBlank, "-1 amount didn't throw exception.\n"),
            Arguments.of("Default range: 21 amount throws exception", 21, isNotBlank, "21 amount didn't throw exception.\n"),
            Arguments.of("Default range: Integer.MIN_VALUE amount throws exception", Integer.MIN_VALUE, isNotBlank, "Integer.MIN_VALUE amount didn't throw exception.\n"),
            Arguments.of("Default range: Integer.MAX_VALUE amount throws exception", Integer.MAX_VALUE, isNotBlank, "Integer.MAX_VALUE amount didn't throw exception.\n")
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("exceptionThrowingInternalSelectorSource")
    void factoryGetWithInvalidLimitExceptionTest(String name, int amount, Predicate<String> condition, String message) {
        final var exception = Assertions.assertThrows(IllegalArgumentException.class, () -> InternalSelectorDataFactory.getWithDefaultRange(amount));
        final var exceptionMessage = exception.getMessage();
        Assertions.assertTrue(condition.test(message), message + exceptionMessage);
    }

    public static Stream<Arguments> exceptionThrowingInternalSelectorWithRangeSource() {
        final Predicate<String> isNotBlank = StringUtils::isNotBlank;
        return Stream.of(
                Arguments.of("CommandRangeData(2, 2) range: 0 amount throws exception", new CommandRangeData(2, 2), 0, isNotBlank, "0 amount didn't throw exception.\n"),
                Arguments.of("CommandRangeData(2, 2) range: -1 amount throws exception", new CommandRangeData(2, 2), -1, isNotBlank, "-1 amount didn't throw exception.\n"),
                Arguments.of("CommandRangeData(Integer.MIN_VALUE, 0) range: 1 amount throws exception", new CommandRangeData(Integer.MIN_VALUE, 0), 1, isNotBlank, "1 amount didn't throw exception.\n")
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("exceptionThrowingInternalSelectorWithRangeSource")
    void factoryGetWithInvalidLimitExceptionTest(String name, CommandRangeData range, int amount, Predicate<String> condition, String message) {
        final var exception = Assertions.assertThrows(IllegalArgumentException.class, () -> InternalSelectorDataFactory.getWith(range, amount));
        final var exceptionMessage = exception.getMessage();
        Assertions.assertTrue(condition.test(message), message + exceptionMessage);
    }
}
