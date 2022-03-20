package se.coolcode.spicy.settings;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Settings {

    private static Settings INSTANCE;
    private final Set<Setting<?>> settings = new HashSet<>();
    private List<ConfigurationSource> configurationSources;

    private Settings(List<ConfigurationSource> configurationSources) {
        this.configurationSources = configurationSources;
    }

    public static Settings create(List<ConfigurationSource> configurationSources) {
        if (INSTANCE == null) {
            INSTANCE = new Settings(configurationSources);
        } else {
            INSTANCE.addConfigurationSources(configurationSources);
        }
        return INSTANCE;
    }

    public static Settings getInstance() {
        return INSTANCE;
    }

    public BooleanSetting createBooleanSetting(Property<Boolean> property) {
        String value = getValue(property);
        BooleanSetting setting = new BooleanSetting(property.key(), BooleanSetting.parse(value, property));
        settings.add(setting);
        return setting;
    }

    public StringSetting createStringSetting(Property<String> property) {
        StringSetting setting = new StringSetting(property.key(), getValue(property));
        settings.add(setting);
        return setting;
    }

    public IntegerSetting createIntegerSetting(Property<Integer> property) {
        String value = getValue(property);
        IntegerSetting setting = new IntegerSetting(property.key(), IntegerSetting.parse(value, property));
        settings.add(setting);
        return setting;
    }

    private String getValue(Property<?> property) {
        return this.configurationSources.stream()
                .map(source -> source.getValue(property.key()))
                .filter(Objects::nonNull)
                .findFirst()
                .orElse(property.defaultValue());
    }

    public void addConfigurationSources(List<ConfigurationSource> configurationSources) {
        this.configurationSources = Stream.of(this.configurationSources, configurationSources)
                .flatMap(Collection::stream)
                .distinct()
                .collect(Collectors.toList());
    }

}
