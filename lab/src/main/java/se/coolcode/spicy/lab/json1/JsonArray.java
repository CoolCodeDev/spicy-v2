package se.coolcode.spicy.lab.json1;

import java.util.ArrayList;
import java.util.List;

class JsonArray implements JsonElement {

    private List<JsonElement> values = new ArrayList<>();

    @Override
    public void append(char data) {
        //TODO: similar impl as JsonObject value parsing
        //check type of element, create valueBuilder, append until endChar
        //add valueBuilder to values-list and clear valueBuilder
    }
}
