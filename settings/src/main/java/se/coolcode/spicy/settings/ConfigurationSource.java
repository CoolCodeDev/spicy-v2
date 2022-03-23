package se.coolcode.spicy.settings;

import java.util.Map;
import java.util.Set;

public interface ConfigurationSource {

    String getValue(String key);
    Map<String, String> getValues(Set<String> keys);
}
