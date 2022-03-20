package se.coolcode.spicy.settings;

public interface ConfigurationSource {

    String getKey();
    String getValue(String key);
}
