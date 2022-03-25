package se.coolcode.spicy.settings;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

public abstract class AbstractSetting<T> implements Setting<T> {

    protected final String key;
    private T value;
    private final List<Consumer<Setting<T>>> listeners;

    public AbstractSetting(String key, String value) {
        this.key = key;
        this.value = parse(value);
        listeners = new ArrayList<>();
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public T getValue() {
        return value;
    }

    @Override
    public boolean updateValue(String value) {
        boolean updated = false;
        if (Objects.nonNull(value) && !Objects.equals(this.value, value)) {
            this.value = parse(value);
            listeners.forEach(consumer -> consumer.accept(this));
            updated = true;
        }
        return updated;
    }

    public void addListener(Consumer<Setting<T>> listener) {
        this.listeners.add(listener);
    }

    public void removeListener(Consumer<Setting<T>> listener) {
        this.listeners.remove(listener);
    }

    abstract T parse(String value);

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
