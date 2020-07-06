package FrameworkFormatter;

import com.github.karsaii.framework.core.namespaces.validators.FrameworkCoreFormatter;
import com.github.karsaii.framework.core.records.GetByFilterFormatterData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.apache.commons.lang3.StringUtils.isBlank;

public class GetByFilterFormatterDataTests {
    @DisplayName("Get By filter formatter data valid")
    @Test
    void isValidGetByFilterDataTest() {
        final var data = new GetByFilterFormatterData<>("", "String", false, 1, "This is a message");
        final var result = FrameworkCoreFormatter.getInvalidGetByFilterFormatterDataMessage(data);
        Assertions.assertTrue(isBlank(result), result);
    }
}
