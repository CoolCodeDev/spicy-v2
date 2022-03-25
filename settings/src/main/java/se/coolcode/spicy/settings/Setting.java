package se.coolcode.spicy.settings;

public interface Setting<T> {

    String getKey();
    T getValue();
    boolean updateValue(String value);

}
