package se.coolcode.spicy.json;

import org.junit.jupiter.api.Test;
import se.coolcode.spicy.json.reader.JsonObjectReader;

import static org.junit.jupiter.api.Assertions.*;

public class JsonObjectReaderTest {

    @Test
    void hasNextKeyReturnsTrueWhenAtleastOneMoreKeyExists() {
        String json = "{\"key\": \"value\"}";
        JsonObjectReader reader = new JsonObjectReader(json);

        assertTrue(reader.hasNextKey(), "HasNextKey returns false.");
    }

    @Test
    void hasNextKeyReturnsFalseWhenNoMoreKeysExists() {
        String json = "{}";
        JsonObjectReader reader = new JsonObjectReader(json);

        assertFalse(reader.hasNextKey(), "HasNextKey returns true.");
    }

    @Test
    void getNextKeyReturnsNextKey() {
        String json = "{\"key\": \"value\"}";
        JsonObjectReader reader = new JsonObjectReader(json);

        assertEquals("\"key\"", reader.getNextKey(), "Unexpected key was returned.");
    }

    @Test
    void getNextKeyReturnsNewKeyOnEachCall() {
        String json = "{\"one\": \"value-1\", \"two\": \"value-2\"}";
        JsonObjectReader reader = new JsonObjectReader(json);

        String first = reader.getNextKey();
        String second = reader.getNextKey();

        assertNotNull(first, "First key is null.");
        assertNotNull(second, "Second key is null.");
        assertEquals("\"one\"", first);
        assertEquals("\"two\"", second);
    }

    @Test
    void getNextKeyReturnsNullWhenAllKeysHasBeenReturned() {
        fail();
    }

}
