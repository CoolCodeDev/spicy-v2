package se.coolcode.spicy.lab.json1;

class JsonBoolean implements JsonElement {

    private StringBuilder valueBuilder = new StringBuilder();
    private Boolean value;

    @Override
    public void append(char data) {
        if (value == null) {
            valueBuilder.append(data);
            String currentValue = valueBuilder.toString();
            if (isTrueOrFalse(currentValue)) {
                value = Boolean.valueOf(currentValue);
                valueBuilder = null;
            }
        }
    }

    private boolean isTrueOrFalse(String currentValue) {
        return currentValue.equalsIgnoreCase("true") || currentValue.equalsIgnoreCase("false");
    }
}
