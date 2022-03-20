package se.coolcode.spicy.settings;

public record Property<T>(Class<T> type, String key, String defaultValue) {

    public static <T> Property<T> create(Class<T> type, String key, String defaultValue) {
        return new Property<>(type, key, defaultValue);
    }

}
