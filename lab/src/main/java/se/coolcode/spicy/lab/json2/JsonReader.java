package se.coolcode.spicy.lab.json2;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonReader {

    private Map<String, JsonValue> keyValues;
    private List<JsonValue> values;
    private StringReader stringReader;

    public JsonReader(StringReader stringReader) {
        this.stringReader = stringReader;
    }

    public Map<String, JsonValue> getKeyValues() {
        if (keyValues == null) {
            parseKeyValues();
        }
        return keyValues;
    }

    public List<JsonValue> getValues() {
        if (values == null) {
            parseValues();
        }
        return values;
    }

    private void parseKeyValues() {
        try {
            this.keyValues = new HashMap<>();
            String key = null;
            int data = stringReader.read();
            while (data != -1) {
                key = getKey(data);
                if (key != null) {
                    JsonValue jsonValue = getValue(data);
                    keyValues.put(key, jsonValue);
                    key = null;
                }
                data = stringReader.read();
            }
        } catch (Exception e) {

        }
    }

    private void parseValues() {
        try {
            this.values = new ArrayList<>();
            int data = stringReader.read();
            while (data != -1) {
                JsonValue jsonValue = getValue(data);
                values.add(jsonValue);
                data = stringReader.read();
            }
        } catch (Exception e) {

        }
    }

    private JsonValue getValue(int data) {
        try {
            StringBuilder value = new StringBuilder();
            JsonType type = null;
            while (data != -1) {
                char currentChar = (char) data;
                //find json value type, i.e string, object, array, boolean, number, undefined, null
                if (type == null) {
                    type = JsonType.getType(currentChar);
                } else if (!type.isEnd(currentChar)) {
                    value.append(currentChar);
                } else if (!value.isEmpty() && type.isEnd(currentChar)) {
                    return new JsonValue(type, value.toString());
                }
                data = stringReader.read();
            }
        } catch (Exception e) {

        }
        return null;
    }

    private String getKey(int data) {
        try {
            StringBuilder key = new StringBuilder();
            boolean appendKey = false;
            while (data != -1) {
                char currentChar = (char) data;
                if (key.isEmpty() && JsonType.STRING.isBegin(currentChar)) {
                    appendKey = true;
                } else if (appendKey && !JsonType.STRING.isEnd(currentChar)) {
                    key.append(currentChar);
                } else if (!key.isEmpty() && JsonType.STRING.isEnd(currentChar)) {
                    return key.toString();
                }
                data = stringReader.read();
            }
        } catch (Exception e) {
            //TODO: handle exceptions from string reader
        }
        return null;
    }
}
