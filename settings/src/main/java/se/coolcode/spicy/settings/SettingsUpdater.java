package se.coolcode.spicy.settings;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

class SettingsUpdater implements Runnable {

    private final Set<Setting<?>> settings;
    private final List<ConfigurationSource> configurationSources;

    public SettingsUpdater(Set<Setting<?>> settings, List<ConfigurationSource> configurationSources) {
        this.settings = settings;
        this.configurationSources = configurationSources;
    }

    @Override
    public void run() {
        Map<String, Setting<?>> settings = getSettingsAsMap();
        for (ConfigurationSource source : configurationSources) {
            Set<String> keys = settings.keySet();
            Map<String, String> values = source.getValues(keys);
            for (Map.Entry<String, String> entry : values.entrySet()) {
                Setting<?> setting = settings.get(entry.getKey());
                if (setting.updateValue(entry.getValue())) {
                    settings.remove(entry.getKey());
                }
            }
        }
    }

    private Map<String, Setting<?>> getSettingsAsMap() {
        return settings.stream().collect(Collectors.toMap(Setting::getKey, setting -> setting));
    }

}
