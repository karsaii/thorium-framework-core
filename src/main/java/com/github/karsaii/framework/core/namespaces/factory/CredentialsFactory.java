package com.github.karsaii.framework.core.namespaces.factory;

import com.github.karsaii.core.constants.validators.CoreFormatterConstants;
import com.github.karsaii.framework.core.records.credentials.Credentials;

public interface CredentialsFactory {
    static Credentials getWith(String username, String password, String description) {
        return new Credentials(username, password, description);
    }

    static Credentials getWithDefaultDescription(String username, String password) {
        return getWith(username, password, CoreFormatterConstants.EMPTY);
    }
}
