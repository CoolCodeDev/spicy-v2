package se.coolcode.spicy.settings;

import se.coolcode.spicy.concurrency.NamedThreadFactory;

import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Settings {

    private final Set<Setting<?>> settings = new HashSet<>();
    private List<ConfigurationSource> configurationSources;
    private final int updatePeriod;
    private final ScheduledExecutorService executorService;

    public Settings(List<ConfigurationSource> configurationSources) {
        this(configurationSources, 30);
    }

    public Settings(List<ConfigurationSource> configurationSources, int updatePeriod) {
        this.configurationSources = configurationSources;
        this.updatePeriod = updatePeriod;
        this.executorService = initExecutorService();
    }

    private ScheduledExecutorService initExecutorService() {
        ThreadGroup parentThreadGroup = Thread.currentThread().getThreadGroup();
        ThreadGroup threadGroup = new ThreadGroup(parentThreadGroup, "settings");
        NamedThreadFactory threadFactory = new NamedThreadFactory("settings-updater", threadGroup);
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor(threadFactory);
        executor.scheduleAtFixedRate(new SettingsUpdater(this.settings, this.configurationSources), 0, updatePeriod, TimeUnit.SECONDS);
        return executor;
    }

    public BooleanSetting createBooleanSetting(Property<Boolean> property) {
        String value = getValue(property);
        BooleanSetting setting = new BooleanSetting(property.key(), value);
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
        IntegerSetting setting = new IntegerSetting(property.key(), value);
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

    public void destroy() {
        executorService.shutdown();
        int timeout = updatePeriod * 2;
        try {
            if (!executorService.awaitTermination(timeout, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
                if (!executorService.awaitTermination(timeout, TimeUnit.SECONDS))
                    System.err.println("ScheduledExecutorService settings-updater did not terminate.");
            }
        } catch (InterruptedException ie) {
            executorService.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

}
