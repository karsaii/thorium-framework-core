package com.github.karsaii.framework.core.records.credentials;

import java.util.Objects;

public class Credentials {
    public final String username;
    public final String password;
    public final String description;

    public Credentials(String username, String password, String description) {
        this.username = username;
        this.password = password;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final var that = (Credentials) o;
        return Objects.equals(username, that.username) && Objects.equals(password, that.password) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, description);
    }
}
