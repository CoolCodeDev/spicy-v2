package se.coolcode.spicy.json.reader;

import java.util.regex.Pattern;

public class JsonObjectReader {
    private final String data;
    private int currentPosition;
    private boolean valueIsExtracted;
    private boolean keyIsExtracted;

    public JsonObjectReader(String data) {
        this.data = data;
        this.currentPosition = 0;
    }

    public boolean hasNextKey() {
        String currentData = getCurrentData();
        return Pattern.compile("(\".*?\":)", Pattern.MULTILINE)
                .matcher(currentData).results().findAny().isPresent();
    }

    public String getNextKey() {
        keyIsExtracted = true;
        if (! valueIsExtracted) {
            getNextValue();
        }
        String key = extractKey();
        setCurrentPosition(key);
        valueIsExtracted = false;
        return key;
        //register current position
        //extract next key starting on current position
        //if called before value has been extracted, ignore value by calling getValue()
        //use flag for state between key/value-methods to signal if key has been extracted
    }

    public String getNextValue() {
        valueIsExtracted = true;
        if (!keyIsExtracted) {
            getNextKey();
        }
        String value = extractValue();
        setCurrentPosition(value);
        keyIsExtracted = false;
        return value;
        //extract next value starting on current position
        //if called before key has been extracted, ignore key by calling getKey()
        //use flag for state between key/value-methods to signal if value has been extracted
    }

    private String extractKey() {
        String currentData = getCurrentData();
        return Pattern.compile("(\".*?\")", Pattern.MULTILINE)
                .matcher(currentData).results().findFirst()
                .orElse(NoMatchResult.NO_MATCH_RESULT).group();
    }

    private String extractValue() {
        String currentData = getCurrentData();
        return
    }

    private String getCurrentData() {
        return data.substring(currentPosition, data.length());
    }

    private void setCurrentPosition(String data) {
        if (data != null) {
           currentPosition = data.indexOf(data) + data.length() +1;
        }
    }
}
