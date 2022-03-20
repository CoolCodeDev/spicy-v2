package se.coolcode.spicy.settings;

import java.util.Objects;

public abstract class AbstractSetting<T> implements Setting<T> {

    private final String key;
    private final T value;

    public AbstractSetting(String key, T value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractSetting<?> that = (AbstractSetting<?>) o;
        return Objects.equals(key, that.key);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key);
    }
}
