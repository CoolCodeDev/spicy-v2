package se.coolcode.spicy.logger;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LogConfiguration {
    static final LogConfiguration INSTANCE = new LogConfiguration();
    final Set<OutputStream> outputs = new HashSet<>(10);
    final List<LogPattern> patterns = new ArrayList<>(20);
    boolean json = false;

    private LogConfiguration() {}

    public static LogConfiguration config() {
        return INSTANCE;
    }

    public LogConfiguration eventTime(DateTimeFormatter formatter) {
        patterns.add(new EventTimeLogPattern(formatter));
        return INSTANCE;
    }

    public LogConfiguration logLevel() {
        patterns.add(new LogLevelLogPattern());
        return INSTANCE;
    }

    public LogConfiguration logger() {
        patterns.add(new LoggerLogPattern());
        return INSTANCE;
    }

    public LogConfiguration message() {
        patterns.add(new MessageLogPattern());
        return INSTANCE;
    }

    public LogConfiguration toConsole() {
        outputs.add(System.out);
        return INSTANCE;
    }

    public LogConfiguration toFile(String fileName) {
        try {
            Path path = Paths.get(fileName);
            outputs.add(Files.newOutputStream(path, StandardOpenOption.CREATE, StandardOpenOption.APPEND));
        } catch (IOException e) {
            String message = String.format("Failed to create log output configuration to file %s.", fileName);
            throw new LogConfigurationException(message, e);
        }
        return INSTANCE;
    }

    public LogConfiguration asJson() {
        json = true;
        return INSTANCE;
    }
}
