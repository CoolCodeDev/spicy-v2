package se.coolcode.spicy.json;

public interface TypeBuilder {
    boolean isAppendable();
    void append(char c);
    JsonElement build();
}
