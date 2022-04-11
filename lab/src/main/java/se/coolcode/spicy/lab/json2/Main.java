package se.coolcode.spicy.lab.json2;

public class Main {

    public static final String JSON_STRINGS =
            """
            {
                "firstKey": "first value",
                "secondKey": "second value",
                "thirdKey": "third value"
            }
            """;

    public static final String JSON_ARRAY =
            """
            {
                "firstKey": ["val1", "val2"],
                "secondKey": ["val3", "val4"]
            }    
            """;

    public static final String JSON_MIX =
            """
            {
                "firstKey": ["val1", "val2"],
                "secondKey": "second value",
            }
            """;

    public static void main(String[] args) {
        System.out.println(Json.fromJson(JSON_STRINGS).get("firstKey"));
        System.out.println("-------------------------");
        System.out.println(Json.fromJson(JSON_ARRAY).get("firstKey"));
        System.out.println("-------------------------");
        System.out.println(Json.fromJson(JSON_MIX).get("firstKey"));
        System.out.println(Json.fromJson(JSON_MIX).get("secondKey"));
    }
}
