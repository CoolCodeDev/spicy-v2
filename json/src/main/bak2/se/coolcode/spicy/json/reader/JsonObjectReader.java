package se.coolcode.spicy.json.reader;

import se.coolcode.spicy.json.JsonType;

import java.util.regex.Pattern;

public class JsonObjectReader {
    private String data;
    private boolean valueIsExtracted;
    private boolean keyIsExtracted;

    public JsonObjectReader(String data) {
        this.data = data;
    }

    public boolean hasNextKey() {
        return Pattern.compile("(\".*?\":)", Pattern.MULTILINE)
                .matcher(data).results().findAny().isPresent();
    }

    public String getNextKey() {
        keyIsExtracted = true;
        if (! valueIsExtracted) {
            getNextValue();
        }
        String key = extractKey();
        updateData(key);
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
        updateData(value);
        keyIsExtracted = false;
        return value;
        //extract next value starting on current position
        //if called before key has been extracted, ignore key by calling getKey()
        //use flag for state between key/value-methods to signal if value has been extracted
    }

    private String extractKey() {
        return Pattern.compile("(\".*?\")", Pattern.MULTILINE)
                .matcher(data).results().findFirst()
                .orElse(NoMatchResult.NO_MATCH_RESULT).group();
    }

    private String extractValue() {

        return null;
    }

    private void updateData(String extracted) {
        if (extracted != null) {
            this.data = this.data != null
                    ? this.data.replaceFirst(extracted, "")
                    : null;
        }
    }
}
