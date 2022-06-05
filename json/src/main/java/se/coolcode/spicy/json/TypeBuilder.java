package se.coolcode.spicy.json;

interface TypeBuilder {
    boolean isAppendable();
    void append(char c);
    JsonElement build();
}
