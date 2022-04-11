package se.coolcode.spicy.lab.json1;

import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

class JsonObject implements JsonElement {

    private Map<String, JsonElement> keyValue = new HashMap<>();
    private StringBuilder keyBuilder = new StringBuilder();
    private JsonElement valueBuilder = null;
    private String currentKey = null;
    private boolean keyStart = false;
    private boolean appendKey = false;
    private boolean valueStart = false;
    private boolean appendValue = false;
    private char valueEnd = Character.MIN_VALUE;
    private AtomicInteger curlyCount = new AtomicInteger();
    private StringBuilder json = new StringBuilder();

    public JsonObject() {}

    public JsonObject(String json) {
        parse(json);
    }

    public static JsonObject fromJson(String json) {
        return new JsonObject(json);
    }

    private void parse(String json) {
        try {
            StringReader reader = new StringReader(json);
            keyStart = true;
            char data = (char) reader.read();
            while (data != -1) {
                if (keyStart) {
                    buildKey(data);
                } else if (valueStart) {
                    buildValue(data);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void buildKey(char data) {
        if (data == JsonType.STRING.begin) {
            appendKey = true;
        } else if (data == JsonType.STRING.end) {
            appendKey = false;
            keyStart = false;
            currentKey = keyBuilder.toString();
            keyBuilder.setLength(0);
        } else if (appendKey) {
            keyBuilder.append(data);
        }
    }

    private void buildValue(char data) {
        if (valueBuilder == null) {
            createValueBuilder(data);
        } else if (data == valueEnd) {
            keyValue.put(currentKey, valueBuilder);
            currentKey = null;
            valueBuilder = null;
            keyStart = true;
            valueStart = false;
        } else {
            valueBuilder.append(data);
        }
    }

    private void createValueBuilder(char data) {
        if (JsonType.STRING.isBegin(data)) {
            valueBuilder = new JsonString();
            valueEnd = JsonType.STRING.end;
        } else if (JsonType.NUMBER.isBegin(data)) {
            valueBuilder = new JsonNumber();
        } else if (JsonType.BOOLEAN.isBegin(data)) {
            valueBuilder = new JsonBoolean();
        } else if (JsonType.OBJECT.isBegin(data)) {
            valueBuilder = new JsonObject();
        } else if (JsonType.ARRAY.isBegin(data)) {
            valueBuilder = new JsonArray();
        }
    }

    @Override
    public void append(char data) {
        if (JsonType.OBJECT.isBegin(data)) {
            curlyCount.incrementAndGet();
        } else if (JsonType.OBJECT.isEnd(data)) {
            curlyCount.decrementAndGet();
        }

        if (curlyCount.get() == 0) {
            parse(json.toString());
            json.setLength(0);
        }
    }
}
