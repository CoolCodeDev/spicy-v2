package se.coolcode.spicy.settings;

import java.util.Objects;

public record Property<T>(Class<T> type, String key, String defaultValue) {

    public static <T> Property<T> create(Class<T> type, String key, String defaultValue) {
        String message = String.format("Default value must not be null for key %s.", key);
        return new Property<>(type, key, Objects.requireNonNull(defaultValue, message));
    }

}
