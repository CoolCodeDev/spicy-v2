package se.coolcode.spicy.json;

public class Json {

    public static JsonElement fromJson(String data) {
        return JsonParser.fromJson(data);
    }
}
