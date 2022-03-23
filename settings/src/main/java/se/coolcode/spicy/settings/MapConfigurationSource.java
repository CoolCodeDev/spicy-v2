package se.coolcode.spicy.settings;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class MapConfigurationSource implements ConfigurationSource {

    private final Map<String, String> properties;

    public MapConfigurationSource(Map<String, String> properties) {
        this.properties = properties;
    }

    @Override
    public String getValue(String key) {
        return properties.get(key);
    }

    @Override
    public Map<String, String> getValues(Set<String> keys) {
        return properties.entrySet().stream()
                .filter(entry -> keys.contains(entry.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
