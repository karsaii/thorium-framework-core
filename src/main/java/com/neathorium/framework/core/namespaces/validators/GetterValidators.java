package com.neathorium.framework.core.namespaces.validators;

import com.neathorium.core.extensions.DecoratedList;
import com.neathorium.core.namespaces.validators.CoreFormatter;
import com.neathorium.core.records.Data;

import java.util.function.Function;

public interface GetterValidators {
    private static <T, U extends DecoratedList<T>> String isInvalidElementList(Data<U> data) {
        return CoreFormatter.getNamedErrorMessageOrEmpty(
            "isInvalidElementList",
            CoreFormatter.getValidNonFalseAndValidContainedMessage(data, CoreFormatter::isNullOrEmptyListMessage)
        );
    }

    private static <T, U extends DecoratedList<T>> Function<U, String> getContainsIndexMessage(int index) {
        return list -> CoreFormatter.getContainsIndexMessage(list, index);
    }

    static <T, U extends DecoratedList<T>> String isInvalidElementByTextParameters(Data<U> data, String text) {
        return CoreFormatter.getNamedErrorMessageOrEmpty(
            "isInvalidElementByTextParameters",
            (
                CoreFormatter.getValidNonFalseAndValidContainedMessage(data, CoreFormatter::isNullOrEmptyListMessage) +
                CoreFormatter.isBlankMessageWithName(text, "Text")
            )
        );
    }

    static <T, U extends DecoratedList<T>> String isInvalidElementByIndexParameters(Data<U> data, int index) {
        return CoreFormatter.getNamedErrorMessageOrEmpty(
            "isInvalidElementByIndexParameters",
            (
                CoreFormatter.getValidNonFalseAndValidContainedMessage(data, getContainsIndexMessage(index)) +
                CoreFormatter.isMoreThanExpectedMessage(index, -1, "Index")
            )
        );
    }
}
