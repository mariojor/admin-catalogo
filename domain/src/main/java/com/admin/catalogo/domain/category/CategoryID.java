package com.admin.catalogo.domain.category;

import com.admin.catalogo.domain.Identifier;

import java.util.Objects;
import java.util.UUID;

public class CategoryID extends Identifier {

    private final String value;
    private CategoryID(final String value) {
        Objects.requireNonNull(value, "Category ID can not be null");
        this.value = value;
    }
    //Factory method
    public static CategoryID unique() {
        return CategoryID.from(UUID.randomUUID().toString().toLowerCase());
    }

    public static CategoryID from(final String value) {
        return new CategoryID(value);
    }

    public static CategoryID from(final UUID value) {
        return new CategoryID(value.toString().toLowerCase());
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final CategoryID that = (CategoryID) o;
        return Objects.equals(getValue(), that.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}
