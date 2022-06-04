package se.coolcode.spicy.json;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class JsonTest {

    @Test
    void canCreateNewJson() {
        assertDoesNotThrow(() -> {
            Json json = new Json();
        }, "Could not create new Json with default constructor.");
    }

    @ParameterizedTest
    @ValueSource(strings = "{}")
    void returnsJsonObjectWhenJsonObjectStringIsProvided(String data) {
        Json json = new Json();

        JsonObject jsonObject = json.fromJson(data);

        assertNotNull(jsonObject, "JsonObject is null.");
    }

    @ParameterizedTest
    @ValueSource(strings = "{\"firstKey\": \"first value\", \"secondKey\": \"second value\"}")
    void returnsJsonObjectWhenJsonObjectStringWithStringValuesIsProvided(String data) {
        Json json = new Json();

        JsonObject jsonObject = json.fromJson(data);

        assertNotNull(jsonObject);
        assertEquals(new JsonString("first value"), jsonObject.get("firstKey"), "'firstKey' has wrong value.");
        assertEquals(new JsonString("second value"), jsonObject.get("secondKey"), "'secondKey' has wrong value.");
    }

    @ParameterizedTest
    @ValueSource(strings = "{\"firstKey\": {\"secondKey\": \"second value\"}}")
    void returnsNestedValue(String data) {
        Json json = new Json();

        JsonObject jsonObject = json.fromJson(data);

        assertNotNull(jsonObject);
        assertEquals(new JsonString("second value"), jsonObject.get("firstKey.secondKey"), "Nested 'secondKey' has wrong value.");
    }

//    void returnsAnyObjectFromArrayMatchingFilter(String data) {
//        Json json = new Json();
//
//        JsonObject jsonObject = json.fromJson(data);
//        List<JsonElement> found = jsonObject.find("arrayKey[*].firstKey=\"first value\"");
//    }

//    void returnsValueOnIndex(String data) {
//        Json json = new Json();
//
//        JsonObject jsonObject = json.fromJson(data);
//        JsonElement jsonElement = jsonObject.get("arrayKey[0]");
//    }

//    void returnsNestedValueOnIndex(String data) {
//        Json json = new Json();
//
//        JsonObject jsonObject = json.fromJson(data);
//        JsonElement jsonElement = jsonObject.get("arrayKey[0].firstKey");
//    }

//    void returnsFirstValueMatching(String data) {
//        Json json = new Json();
//
//        JsonObject jsonObject = json.fromJson(data);
//        JsonElement jsonElement = jsonObject.getIfExists("arrayKey[firstKey=\"first value\"]");
//    }

//    void returnsFirstValueIfExists(String data) {
//        Json json = new Json();
//
//        JsonObject jsonObject = json.fromJson(data);
//        JsonElement jsonElement = jsonObject.getIfExists("arrayKey[\"val1\"]");
//    }
}
